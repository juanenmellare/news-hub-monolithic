package news.hub.monolithic

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import mocks.domains.NewsMockBuilder
import mocks.domains.UserMockBuilder
import spock.lang.Specification

import java.time.Instant

class NewsServiceSpec extends Specification implements ServiceUnitTest<NewsService>, DataTest {

    Class<?>[] getDomainClassesToMock(){
        return [News, User] as Class[]
    }

    void "test findById, founded"() {
        given:
        final News newsExpected = new NewsMockBuilder().save().build()

        when:
        final News newsFounded = service.findById(newsExpected.getId())

        then:
        newsFounded == newsExpected
    }

    void "test findById, not founded"() {
        given:
        final News newsExpected = new NewsMockBuilder().build()

        when:
        final News newsFounded = service.findById(newsExpected.getId())

        then:
        newsFounded == null
    }

    void "test getLastNewsFromChannels"() {
        when:
        final List<News> news = service.getLastNewsFromChannels()

        then:
        news.size() > 0
    }

    void "test fetchAndSave"() {
        given:
        final NewsService newsService = Spy(NewsService)
        final News oldNews = new NewsMockBuilder().setUrl("www.news-foo.com").save().build()
        final List<News> newsExpected = [new NewsMockBuilder().build(), oldNews]

        when:
        newsService.fetchAndSave()
        final List<News> newsFounded = News.findAll()

        then:
        1 * newsService.getLastNewsFromChannels() >> newsExpected
        newsFounded.size() == 2
        newsFounded[0].url == oldNews.url
        newsFounded[1].url == newsExpected[0].url
    }

    void "test listAll"() {
        given:
        final NewsService newsService = Spy(NewsService)
        final Instant now = Instant.now()
        final News news = new NewsMockBuilder().setUrl("www.news-foo.com")
                .setPublishedAt(now.minusSeconds(5)).save().build()
        final News news2 = new NewsMockBuilder().setUrl("www.news-foo-2.com")
                .setPublishedAt(now.minusSeconds(1)).save().build()

        when:
        final List<News> newsFounded = newsService.listAll(1)

        then:
        newsFounded.size() == 2
        newsFounded[0].url == news2.url
        newsFounded[1].url == news.url
    }

    void "test getTotalPages"() {
        given:
        final NewsService newsService = Spy(NewsService)
        newsToCreate.times {
            new NewsMockBuilder().setUrl("www.news-foo${it}.com").save()
        }

        when:
        final int totalPages = newsService.getTotalPages()

        then:
        totalPages == totalPagesExpected

        where:
        newsToCreate | totalPagesExpected
        20           | 1
        21           | 2
    }

    void "test addReader"() {
        given:
        final NewsService newsService = Spy(NewsService)

        final News news = new NewsMockBuilder().save().build()
        final User user = new UserMockBuilder().save().build()
        final User user2 = new UserMockBuilder().save().build()

        when:
        newsService.addReader(news, user)
        newsService.addReader(news, user)
        newsService.addReader(news, user2)
        final News newsWithReaders = newsService.findById(news.id)

        then:
        newsWithReaders.readers.size() == 2
    }
}
