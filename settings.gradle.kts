rootProject.name = "my-vee-port"
include("vee-port", "vee-port:front-panel")

project(":vee-port:front-panel").projectDir = file("vee-port/extensions/front-panel")