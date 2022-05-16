package news.hub.monolithic

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")

        "/ping"(method: "get", controller: "HealthChecks", action: "ping")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
