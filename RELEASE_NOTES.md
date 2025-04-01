# VEE Port Release Notes for <BOARD_MANUFACTURER> <BOARD_NAME>

## Description

This is the release notes of the VEE Port for <BOARD_NAME>.

## Versions

### VEE Port

<VEEPORT_VER>

### Dependencies

This VEE Port contains the following dependencies:

| Dependency Name                       | Version                           |
| ------------------------------------- | --------------------------------- |
| Architecture (<Architecture name>)    | <MicroEJ Architecture version>    |
| <MicroEJ Pack Name>                   | <MicroEJ Pack Version>            |
| ...                                   | ...                               |


Please refer to the VEE Port [module description file](./vee-port/build.gradle.kts)
and the [version catalogs file](./gradle/libs.versions.toml) for more details.

### Board Support Package

- BSP provider and name: <BSP provider and name>
- BSP version: <BSP version>

Please refer to the <BSP provider and name> git repository
available [here](<bsp_provider_git_repository_url>).

### Third Party Software

Third party software used in BSP can be found [here](<third_party_software_url>). Here
is a list of the most important ones:

| RTOS  | <RTOS name>   | <RTOS version>   |
| ----- | ------------- | ---------------- |
| ...   | ...           | ...              |

## Features

### Graphical User Interface

VEE Port features a graphical user interface. <It includes ...>

#### Display

<VEE Port features a XXX * XXX LCD display.  The pixel format
is XXX bits-per-pixel.  The display device is clocked at XXXHz and it is
connected to the MCU via a XXX link...>

MicroUI requires a RAM buffer to store the dynamic images data.  A
dynamic image is an image decoded at runtime (PNG image) or an image created
by the Application using the `Image.create(width, height)` API.
This buffer is located in external RAM.

#### Leds

<VEE Port features XXX Leds...>

### Network

<VEE Port features a network interface with XXX as an
underlying hardware media.  A limited number of XXX sockets could be
used for TCP connections, XXX for TCP listening (server) connections
and XXX for UDP connections. A DHCP client can be activated to retrieve
a dynamic IP address. All DNS requests are handled by the XXX TCP/IP stack.>

### SSL

<VEE Port features a network secure interface. Available
secured protocols are SSL 3.0, TLS 1.0, TLS 1.1, TLS 1., XXX. Supported
keys and certificates formats are PKCS#5 and PKCS#12, PEM or DER
encoded, XXX.>

### File System

<VEE Port features a file system interface. XXX is
used for the storage (previously formated to a XXX file system). Up
to XXX files can be opened simultaneously.>


## MISRA Compliance

This VEE Port has a list of components that are MISRA-compliant (MISRA C:2012) with some noted exception.
Below is the list of `folders that have been verified`:

- <microej/folder_1>
- <microej/folder_2>

Among the folders verified, below is the list of `files that have not been verified`:

- <microej/folder_1/file1>
- <microej/folder_1/file2>
- <microej/folder_2/file3>

It has been verified with Cppcheck v2.10. Here is the list of deviations from MISRA standard:

| Deviation  | Category   | Justification                       |
| ---------- | ---------- | ----------------------------------- |
| Rule x.y   | Required   | The justification of the deviation  |


## Known Issues/Limitations

Known issues:

- <Known issue 1>
- <Known issue 2>
- <Known issue n>

Limitations:

- <Limitation issue 1>
- <Limitation issue 2>
- <Limitation issue n>

## VEE Port Memory Layout

### Memory Sections

Each memory section is described in the XXX linker file available
[here](<veeport_bsp_linker_file_url>).

### Memory Layout

| Section Content  | Section Source   | Section Destination  | Memory Type |
| ---------------- | ---------------- | -------------------- | ----------- |
| MicroEJ Application static | `.bss.soar`   | `.bss`  | <Section Destination>  |
| MicroEJ Application threads stack blocks  | `.bss.vm.stacks.java`   | `.ext_ram.bss`   | <Section Destination>  |
| MicroEJ Core Engine internal heap  | `ICETEA_HEAP` | `.ext_ram.bss` | <Section Destination> |
| MicroEJ Application heap | `_java_heap` | `.ext_ram.bss` | <Section Destination> |
| MicroEJ Application Immortal Heap | `_java_immortals` | `.ext_ram.bss` | <Section Destination> |
| MicroEJ Application resources | `.rodata.resources` | `.rodata` | <Section Destination> |
| MicroEJ System Applications code and resources | `.rodata.soar.features` | `.rodata` | <Section Destination> |
| MicroEJ System Application statics | `.bss.features.installed` | `.ext_ram.bss` | <Section Destination> |
| MicroEJ Shielded Plug data | `.shieldedplug` | `.rodata` | <Section Destination> |
| MicroEJ Application and Library code | `.text.soar` | `.rodata` | <Section Destination> |
| MicroUI frame buffer | `-` | `.ext_ram.bss` | <Section Destination> |


<For the C heap, please refer to the <BOARD_MANUFACTURER> documentation
available [here](<c_heap_documentation_url>)>.

Information on MicroEJ memory sections can be found [here](<bsp_microej_memory_sections_description_file_url>).

Please also refer to the MicroEJ docs website page available [here](<https://docs.microej.com/en/latest/VEEPortingGuide/coreEngine.html#link>)
for more details.
