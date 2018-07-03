#! /usr/bin/env nix-shell
#! nix-shell -i sh -p python openssl_1_1_0 pythonPackages.absl-py pythonPackages.jsonschema pythonPackages.m2crypto

# Note: For more information check this out:
# http://www.certificate-transparency.org/known-logs
# https://github.com/openssl/openssl/blob/master/apps/ct_log_list.cnf
# CTLOG_STORE_NEW(3) https://www.openssl.org/docs/man1.1.0/crypto/CTLOG_STORE_new.html

# Get list, signature, pubkey and schema
wget https://www.gstatic.com/ct/log_list/log_list{.json,.sig,_pubkey.pem,_schema.json}

# Get tool
wget https://raw.githubusercontent.com/google/certificate-transparency/c5649b7459cf167ec89bfbc4ff589f4f9086393e/python/utilities/log_list/{print_log_list,{openssl,cpp,java}_generator}.py

# Convert logs to openssl format
chmod +x print_log_list.py
./print_log_list.py --log_list log_list.json \
  --signature log_list.sig --signer_key log_list_pubkey.pem --log_list_schema log_list_schema.json \
  --openssl_output yes --openssl_output openssl.log

# Try it!
CTLOG_FILE=openssl.log openssl s_client -connect google.com:443 -ct
