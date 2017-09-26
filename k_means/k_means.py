from math import sqrt

import numpy as np
import matplotlib.pyplot as plt
import random
# import seaborn as sns
# sns.set()


def get_points():
    data = np.array([[0, 1, 0, 1, 0, 0, 0, 0],
                     [0, 1, 1, 0, 1, 0, 0, 0],
                     [1, 0, 1, 0, 0, 0, 0, 0],
                     [0, 1, 0, 0, 0, 0, 1, 0],
                     [1, 0, 0, 0, 0, 0, 0, 0],
                     [0, 0, 0, 0, 1, 0, 1, 1],
                     [0, 0, 1, 0, 0, 1, 1, 0],
                     [0, 0, 0, 0, 1, 1, 0, 1]])

    # get the indices where data is 1
    return np.argwhere(data == 1)


def draw_clusters(centroids, clusters):
    # plt.figure(figsize=(10, 10))
    colors = ["blue", "green", "red", "cyan", "magenta",
              "yellow"]
    if len(clusters) > len(colors):
        raise Exception("Too many clusters - not enough colors")

    for i, points in enumerate(clusters):
        color = colors.pop()
        x, y = points.T
        plt.scatter(x, y, color=color)
        plt.scatter(*centroids[i], color="black")

    # for centroid in centroids:
    #     plt.scatter(*centroid, color="black")
    plt.show()


def distance(point, other):
    return sqrt(pow(point[0] - other[0], 2) +
                pow(point[1] - other[1], 2))


def assign_to_cluster(k, points, centroids):
    clusters = [[] for _ in xrange(k)]
    for x, y in points:
        closest = (0, centroids[0])
        for i, c in enumerate(centroids[1:], start=1):
            if distance((x, y), c) < distance((x, y), closest[1]):
                closest = (i, c)
        clusters[closest[0]].append([x, y])
    return np.array([np.array(c) for c in clusters])


def move_centroids(clusters):
    centroids = []
    for cluster in clusters:
        x = np.mean(cluster.T[0])
        y = np.mean(cluster.T[1])
        centroids.append((x, y))
    return centroids


def kmeans(k, points):
    x, y = points.T
    limits = (np.max(x), np.max(y))
    centroids = [(random.uniform(0, limits[0]),
                  random.uniform(0, limits[1])) for _ in xrange(k)]

    while True:
        print centroids
        clusters = assign_to_cluster(k, points, centroids)
        new_centroids = move_centroids(clusters)
        if new_centroids == centroids:
            centroids = new_centroids
            break
        centroids = new_centroids

    return centroids, clusters


if __name__ == "__main__":
    points = get_points()
    centroids, clusters = kmeans(2, points)
    draw_clusters(centroids, clusters)
