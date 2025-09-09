#!/bin/bash

# 'build.sh' implementation for GCC toolchain

# 'build.sh' is responsible for producing the executable file 
# then copying this executable file to the current directory where it has been executed to a file named 'application.out'

# Force to stop execution on error
set -euo pipefail

source $(dirname $(realpath $0))/"set_project_env.sh" || exit 1

export CURRENT_DIRECTORY=$(pwd)

cd $ARMGCC_PROJECT_MAKEFILE_DIR || exit 2

${ARMGCC_PROJECT_BUILD_CMD} ${ARMGCC_PROJECT_BUILD_CMD_OPTS} || exit 3

echo "Copy binary from $ARMGCC_PROJECT_BINARY_DIR to $CURRENT_DIRECTORY"

cp $ARMGCC_PROJECT_BINARY_DIR/$ARMGCC_PROJECT_BINARY_NAME.elf $CURRENT_DIRECTORY/application.out || exit 4
cp $ARMGCC_PROJECT_BINARY_DIR/$ARMGCC_PROJECT_BINARY_NAME.elf $CURRENT_DIRECTORY/application.elf || exit 4
cp $ARMGCC_PROJECT_BINARY_DIR/$ARMGCC_PROJECT_BINARY_NAME.hex $CURRENT_DIRECTORY/application.hex || exit 4
cp $ARMGCC_PROJECT_BINARY_DIR/$ARMGCC_PROJECT_BINARY_NAME.bin $CURRENT_DIRECTORY/application.bin || exit 4

cd $CURRENT_DIRECTORY || exit 5
