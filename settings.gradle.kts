rootProject.name = "my-vee-port"
include("vee-port", "vee-port:front-panel", "vee-port:mock", "vee-port:image-generator", "app")

project(":vee-port:front-panel").projectDir = file("vee-port/extensions/front-panel")
project(":vee-port:mock").projectDir = file("vee-port/mock")
project(":vee-port:image-generator").projectDir = file("vee-port/extensions/image-generator")