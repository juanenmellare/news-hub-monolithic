package news.hub.monolithic

import grails.testing.gorm.DomainUnitTest
import mocks.domains.NewsMockBuilder
import spock.lang.Specification

class NewSpec extends Specification implements DomainUnitTest<News> {

    void "test new"() {
        when:
        final News news = new NewsMockBuilder().build()

        then:
        news.id != null
        news.title != null
        news.imageUrl != null
        news.channel != null
        news.url != null
        news.publishedAt != null
    }
}
