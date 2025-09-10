/**
 * @file veeport_configuration.h
 * 
 * @brief Configures the C modules integrated for this port of MICROEJ VEE.
 * 
 * ***** HOW DOES THIS CONFIGURATION FILE WORKS *****
 * 
 * This 'veeport_configuration.h' file contains all the custom c modules configurations of the VEE Port.
 * 
 * The configuration file of each C module (i.e. '[C MODULE NAME]_configuration.h') should include this
 * 'veeport_configuration.h' file as followed:
 * 
 *  // Include VEE Port User configuration file
 *  #if defined __has_include
 *  	#if __has_include("veeport_configuration.h")
 *  		#include "veeport_configuration.h"
 *  	#endif // __has_include("veeport_configuration.h")
 *  #else
 *  // Ensure 'veeport_configuration.h' exists in your project for custom configurations.
 *  	#include "veeport_configuration.h"
 *  #endif // defined __has_include
 *
 * Then, in the '[C MODULE NAME]_configuration.h' file, default values for each parameter should be provided
 * as followed:
 * 
 * #ifndef MY_CONFIGURATION
 * #define MY_CONFIGURATION (MY_CONFIGURATION_DEFAULT_VALUE)
 * #endif
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
//                        Core Engine Configuration
// ############################################################################

// Example of a Core Engine configuration,
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
