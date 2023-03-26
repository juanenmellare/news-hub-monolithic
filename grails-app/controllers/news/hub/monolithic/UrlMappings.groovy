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
        "/signIn"(method: "get", controller: "Users", action: "signIn")
        "/signup"(method: "get", controller: "Users", action: "signup")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
