package news.hub.monolithic

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/ping"(method: "get", controller: "HealthChecks", action: "ping")

        "/"(method: "get", controller: "News", action: "index")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
