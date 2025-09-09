#!/bin/bash

# 'run.sh' implementation
# 'run.sh' is responsible for flashing the executable file on the target device

set -euo pipefail

source $(dirname $(realpath $0))/"set_project_env.sh" || exit 1

export CURRENT_DIRECTORY=$(pwd)

cd $ARMGCC_PROJECT_MAKEFILE_DIR || exit 2

${ARMGCC_PROJECT_BUILD_CMD} ${ARMGCC_PROJECT_BUILD_CMD_OPTS} ${ARMGCC_PROJECT_FLASH_CMD} || exit 3

cd $CURRENT_DIRECTORY || exit 4
