package news.hub.monolithic

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import mocks.domains.NewsMockBuilder
import spock.lang.Specification

class NewsServiceSpec extends Specification implements ServiceUnitTest<NewsService>, DataTest {

    Class<?>[] getDomainClassesToMock(){
        return [News] as Class[]
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
        final News oldNews = new NewsMockBuilder().setUrl("www.news-foo.com").build().save()
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
        final News news = new NewsMockBuilder().setUrl("www.news-foo.com").build().save()

        when:
        final List<News> newsFounded = newsService.listAll()

        then:
        1 * newsService.fetchAndSave() >> {}
        newsFounded.size() == 1
        newsFounded[0].url == news.url
    }
}
