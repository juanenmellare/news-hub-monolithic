package news.hub.monolithic

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification


class FetchAndSaveNewsJobServiceSpec extends Specification implements ServiceUnitTest<FetchAndSaveNewsJobService> {

    void "test execute"() {
        given:
        service.newsService = Mock(NewsService)

        when:
        service.execute()

        then:
        1 * service.newsService.fetchAndSave()
    }
}
