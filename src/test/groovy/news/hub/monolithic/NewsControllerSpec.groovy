package news.hub.monolithic

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class NewsControllerSpec extends Specification implements ControllerUnitTest<NewsController> {

    void "test index"() {
        given:
        request.method = 'GET'

        when:
        controller.index()

        then:
        view == '/news/index'
    }
}
