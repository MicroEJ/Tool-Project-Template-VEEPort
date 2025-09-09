#!/bin/bash

# 'set_project_env.bat' implementation 

# 'set_project_env' is responsible for
# - checking the availability of required environment variables
# - setting project local variables for 'build.sh' and 'run.sh'

# Required Environment Variable
# ARMGCC toolchain installation directory (e.g.: /opt/gcc-arm-none-eabi/VERSION)
# ARMGCC_CHANGEIT_INSTALLATION_DIR

if [ "${ARMGCC_CHANGEIT_INSTALLATION_DIR}" == "" ]
then
    echo Please set the environment variable 'ARMGCC_CHANGEIT_INSTALLATION_DIR'
    exit 1
fi

export ARMGCC_INSTALLATION_DIR=$ARMGCC_CHANGEIT_INSTALLATION_DIR

# Directory where the Makefile is located
export ARMGCC_PROJECT_MAKEFILE_DIR=CHANGEIT

# Directory where the binary will be stored after the build step
export ARMGCC_PROJECT_BINARY_DIR=CHANGEIT

# Name of the binary
export ARMGCC_PROJECT_BINARY_NAME=CHANGEIT

# Build command
export ARMGCC_PROJECT_BUILD_CMD=make

# Build command options
export ARMGCC_PROJECT_BUILD_CMD_OPTS=CHANGEIT

# Flash command
export ARMGCC_PROJECT_FLASH_CMD=flash

echo "ARMGCC_INSTALLATION_DIR=$ARMGCC_INSTALLATION_DIR"
echo "ARMGCC_PROJECT_MAKEFILE_DIR=$ARMGCC_PROJECT_MAKEFILE_DIR"
echo "ARMGCC_PROJECT_BINARY_NAME=$ARMGCC_PROJECT_BINARY_NAME"
echo "ARMGCC_PROJECT_BINARY_DIR=$ARMGCC_PROJECT_BINARY_DIR"
echo "ARMGCC_PROJECT_BUILD_CMD=$ARMGCC_PROJECT_BUILD_CMD"
echo "ARMGCC_PROJECT_BUILD_CMD_OPTS=$ARMGCC_PROJECT_BUILD_CMD_OPTS"
echo "ARMGCC_PROJECT_FLASH_CMD=$ARMGCC_PROJECT_FLASH_CMD"

export PATH=$PATH:$ARMGCC_PROJECT_TOOLCHAIN_DIR
