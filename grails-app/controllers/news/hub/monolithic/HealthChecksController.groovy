package news.hub.monolithic

import enums.HttpStatus

class HealthChecksController {

    def ping() {
        render status: HttpStatus.OK.code, text: 'pong!'
    }
}
