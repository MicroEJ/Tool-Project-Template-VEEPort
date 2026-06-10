rootProject.name = "my-vee-port"
include("vee-port", "vee-port:front-panel", "vee-port:mock", "vee-port:image-generator", "app")

project(":vee-port:front-panel").projectDir = file("vee-port/extensions/front-panel")
project(":vee-port:mock").projectDir = file("vee-port/mock")
project(":vee-port:image-generator").projectDir = file("vee-port/extensions/image-generator")

// Only 3 packs are installed by default: UI, FS and NET.
// Comment/uncomment the packs and their related testsuite depending on your needs.
//include("vee-port:validation:ai")
//include("vee-port:validation:audio")
include("vee-port:validation:core")
include("vee-port:validation:ecom-wifi")
//include("vee-port:validation:event-queue")
//include("vee-port:validation:ext-res-loader")
include("vee-port:validation:fs")
//include("vee-port:validation:gnss")
include("vee-port:validation:net")
include("vee-port:validation:security")
include("vee-port:validation:ssl")
include("vee-port:validation:ui")
//include("vee-port:validation:vg")
//include("vee-port:validation:watchdog-timer")