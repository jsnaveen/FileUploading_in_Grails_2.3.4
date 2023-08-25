class UrlMappings {

    static mappings = {
        // Standard Grails controller/action mapping
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }
        
        // Custom mappings for FileUploadController
        "/upload"(controller: "fileUpload", action: "upload", method: "POST")
        "/getFile/$fileId"(controller: "fileUpload", action: "getFile", method: "GET")


        "/"(view:"/index")
        "500"(view:'/error')
    }
}
