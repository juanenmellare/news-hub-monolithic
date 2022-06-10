package news.hub.monolithic

import grails.testing.web.controllers.ControllerUnitTest
import mocks.domains.NewsMockBuilder
import spock.lang.Specification

class NewsControllerSpec extends Specification implements ControllerUnitTest<NewsController> {

    void "test index"() {
        given:
        request.method = 'GET'
        final NewsService newsServiceMock = Mock(NewsService)
        final List<News> newsExpected = [new NewsMockBuilder().build()]
        newsServiceMock.listAll() >> newsExpected
        controller.newsService = newsServiceMock

        when:
        controller.index()

        then:
        view == '/news/index'
        model == [newsList: newsExpected]
    }
}
