#!/bin/bash
#
# BASH
#

# 'run.sh' implementation stub.

# 'run.sh' is responsible for flashing the executable file on the target device 
# then resetting target device

if [ -z "$1" ]; then
    APPLICATION_FILE="$(pwd)/application.out"
else
    APPLICATION_FILE="$(cd $(dirname $1) ; pwd)/$(basename $1)"
fi

if [ ! -e "${APPLICATION_FILE}" ]; then
    echo "FAILED - file '${APPLICATION_FILE}' does not exist"
    exit -1
fi

echo PASSED
