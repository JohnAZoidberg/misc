import qualified Data.Map as Map
import qualified Data.List as L
import qualified Data.Matrix as M

grid = [[0, 1, 0, 1, 0, 0, 0, 0],
        [0, 1, 1, 0, 1, 0, 0, 0],
        [1, 0, 1, 0, 0, 0, 0, 0],
        [0, 1, 0, 0, 0, 0, 1, 0],
        [1, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 1, 0, 1, 1],
        [0, 0, 1, 0, 0, 1, 1, 0],
        [0, 0, 0, 0, 1, 1, 0, 1]]

gridToPoints grid = concat $ zipWith (\y l -> convertRow y 1 l) [1..] grid

convertRow _ _ [] = []
convertRow y x (z:zs)
    | z == 1    = (x, y) : (convertRow y (x+1) zs)
    | otherwise = convertRow y (x+1) zs

convertClusters = addGrids size . concat . zipWith pointGrids [1..]
    where size = 8
          pointGrids = (\s pt -> L.map (\p -> gridFromPoint s p size) pt)


gridFromPoint symbol (x, y) size = M.setElem symbol (truncate x, truncate y) $ M.zero size size

addGrids size = foldl (M.elementwise (+)) (M.zero size size)

newCentroids clusters = concatMap ((:[]) . mean) clusters

mean pointList = mean' 0 0 0 pointList
    where mean' xAcc yAcc tot [] = (xAcc / tot, yAcc / tot)
          mean' xAcc yAcc tot ((x, y):xs) = mean' (xAcc+x) (yAcc+y) (tot+1) xs

distance (x1, y1) (x2, y2) = sqrt $ (x1-x2)^2 + (y1-y2)^2

k_means grid = cluster centroids points
    where points = gridToPoints grid
          centroids = [(maximum [x | (x, y) <- points],
                        maximum [y | (x, y) <- points]),
                       (0, 0)]

cluster centroids points
    | centroids == centroids' = Map.elems clustered
    | otherwise               = cluster centroids' points
    where clustered = clusterPoints (Map.fromList [(c, []) | c <- centroids]) points
          centroids' = (Prelude.map mean  (Map.elems clustered))

clusterPoints centroids [] = centroids
clusterPoints centroids (point:ps) = clusterPoints centroids' ps
    where centroids' = combineCentroids centroids $ clusterPoint centroids point
          combineCentroids left right = Map.mapWithKey (combine right) left
          combine right = (\k v -> maybeUnion v (Map.lookup k right))
          maybeUnion left (Just right) = L.union left right

clusterPoint centroids point = Map.insertWith (++) closest pts centroids
    where (_, (closest, pts)) = Map.findMin $ Map.fromList distances
          distances = Map.foldWithKey (\c v acc -> (distance c point, (c, [point])):acc) [] centroids
