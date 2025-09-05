/**
 * @file veeport_configuration.h
 * 
 * @brief Gathers VEE Port specific configurations and LLAPI configurations.
 * 
 * This file aims at gathering the configuration of all abstraction layer configurations
 * in this VEE Port. Indeed, this file is the place to override the default configuration
 * for the LLAPIs imported with C modules and VEE Port specific configurations.
 * 
 * The advantages are the following:
 * - Having all specific configurations of the VEE Port centralized in one file
 * - Allows to not modify C module sources only for the VEE Port configuration
 * - Standardized C module configuration system
 * 
 * 
 * ***** HOW TO USE THIS FILE? *****
 * 
 * 1. Add the VEE Port configurations you need in this file. For example, a macro to enable
 * the Coremark benchmark execution can be added.
 * 
 * 2. Add configuration separators to sort all the configurations, for example:
 * 
 * // ############################################################################
 * //                           Core Configuration
 * // ############################################################################
 *
 * 3. When creating a new configuration, favor to check its value (i.e. 0 or 1) instead
 * of comment/uncomment its definition.
 * 
 * 4. Remove the unused configurations defined in this file. This file will store the
 * possible configurations of different LLAPIs once the related modules are compliant.
 * 
 * 
 * ***** WHEN AN IMPORTED C MODULE DOES NOT USE THIS FILE YET *****
 * 
 * Whenever you import a C module that has an "_configuration.h" file not up to date with
 * the veeport_configuration.h file, please update it to make it compliant. To do that, follow
 * these steps:
 * 
 * 1. Add the include directive of the file veeport_configuration.h in the "_configuration.h"
 * file, you can add the code snippet below:
 * 
 * #if !defined(__CC_ARM)
 * // cppcheck-suppress [misra-c2012-20.9]: The macro '__has_include' is defined by the toolchain.
 *    #if __has_include("veeport_configuration.h")
 * 	  #include "veeport_configuration.h"
 *    #else
 * 	  #warning "'veeport_configuration.h' not found, default configuration used for all parameters."
 *    #endif // __has_include("veeport_configuration.h")
 * #else
 *    #warning "This C module needs a 'veeport_configuration.h' in your ARMCC project. "
 *    #include "veeport_configuration.h"
 * #endif // !defined ( __CC_ARM)
 *
 * 2. Encapsulates the configurations in the "_configuration.h" file with #ifndef - #endif
 * brackets as follows:
 * 
 * #ifndef UI_FEATURE_BRS
 * #define UI_FEATURE_BRS (UI_FEATURE_BRS_PREDRAW)
 * #endif
 * 
 * 3. If you are a MicroEJ developer, create an MR of the C module with the updated configuration file.
 * Then, create a MR in the github repository Tool-Project-Template-VEEPort and add the configurations
 * with their default value here.
 * 
 */

#ifndef VEEPORT_CONFIGURATION_H
#define VEEPORT_CONFIGURATION_H

#ifdef __cplusplus
    extern "C" {
#endif

// ----------------------------------------------------------------------------
// Includes
// ----------------------------------------------------------------------------


// ############################################################################
//                           CORE Configuration
// ############################################################################

// Example of a Core configuration,
//
// /**
//  * @brief Sets this define to 1 to run the memory and Coremark benchmarks, disabled by default.
//  */
// #define MICROEJ_CORE_VALIDATION (0)


// ############################################################################
//                        Kernel Feature Configuration
// ############################################################################
   

// ############################################################################
//                        Event Queue Configuration
// ############################################################################


// ############################################################################
//                        FS Configuration
// ############################################################################
 

// ############################################################################
//                        NET Configuration
// ############################################################################   


// ############################################################################
//                        UI Configuration
// ############################################################################


// ############################################################################
//                        VG Configuration
// ############################################################################


// ----------------------------------------------------------------------------
// End
// ----------------------------------------------------------------------------

#ifdef __cplusplus
    }
#endif

#endif // VEEPORT_CONFIGURATION_H
