package news.hub.monolithic

import enums.HttpStatus

class HealthChecksController {

    void ping() {
        render status: HttpStatus.OK.code, text: 'pong!'
    }
}
