package news.hub.monolithic

import enums.HttpStatus
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class HealthChecksControllerSpec extends Specification implements ControllerUnitTest<HealthChecksController> {

    void "test ping"() {
        given:
        request.method = 'GET'

        when:
        controller.ping()

        then:
        response.status == HttpStatus.OK.code
        response.text == "pong!"
    }
}
