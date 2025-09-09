/**
 * @file veeport_configuration.h
 * 
 * @brief Configures the C modules integrated for this port of MICROEJ VEE.
 *
 * C modules configured in this file include:
 * - Foundation Libraries Abstraction Layer Implementations (Core, FS, NET, ...)
 * - Intermediate Abstraction Layer Implementations (Time, OSAL, ...)
 * - Libraries used by Abstraction Layer Implementations (Logging, Async-Worker, ...)
 * 
 * 
 * ***** HOW TO USE THIS TEMPLATE? *****
 * 
 * This template provides the list of configurations for the MicroEJ public C components with some documentation.
 * The VEE Port configurations file should link to this template for reference.
 * Then, the VEE Port configurations file should set non-default values and remove advanced configurations with default value to improve readability.
 * Finally, the VEE Port should set `HAS_VEEPORT_CONFIGURATION_H` to `1` for the C modules compilation.
 *
 * 3. When creating a new configuration, favor to check its value (i.e. 0 or 1) instead
 * of comment/uncomment its definition.
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
//                        KF Configuration
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
