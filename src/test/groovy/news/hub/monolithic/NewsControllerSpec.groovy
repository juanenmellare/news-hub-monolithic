package news.hub.monolithic

import enums.HttpStatus
import exceptions.BadRequestApiException
import grails.testing.web.controllers.ControllerUnitTest
import mocks.domains.NewsMockBuilder
import mocks.domains.UserMockBuilder
import responses.NewsListResponse
import responses.NewsResponse
import spock.lang.Specification
import tokens.UserToken
import utils.SessionUtils

class NewsControllerSpec extends Specification implements ControllerUnitTest<NewsController> {

    void "test index"() {
        given:
        request.method = 'GET'

        controller.newsService = Mock(NewsService)
        controller.params.page = currentPage

        final List<News> newsList = [new NewsMockBuilder().build()]

        final Map newsExpected = new NewsListResponse(null, newsList, totalPages, currentPage).toMap()

        when:
        controller.index()

        then:
        1 * controller.newsService.listAll(currentPageExpected) >> newsList
        1 * controller.newsService.getTotalPages() >> totalPages

        view == '/news/index'
        model.toPage == toPageExpected
        model.fromPage == fromPageExpected
        model.currentPage == currentPageExpected
        model.totalPages == totalPagesExpected
        model.offset == 1

        final NewsResponse modelNewsResponse = (model.newsList as List<NewsResponse>).get(0)
        final NewsResponse modelNewsResponseExpected = newsExpected.newsList.get(0)
        modelNewsResponse.id == modelNewsResponseExpected.id
        modelNewsResponse.isRead == modelNewsResponseExpected.isRead

        where:
        currentPage | totalPages | fromPageExpected | toPageExpected | currentPageExpected | totalPagesExpected
        -1          | 2          | 1                | 2              | 1                   | 2
        0           | 2          | 1                | 2              | 1                   | 2
        1           | 2          | 1                | 2              | 1                   | 2
        2           | 2          | 1                | 2              | 2                   | 2
        1           | 10         | 1                | 2              | 1                   | 10
        4           | 10         | 3                | 5              | 4                   | 10
        9           | 10         | 8                | 10             | 9                   | 10
    }

    void "test index, no page param"() {
        given:
        request.method = 'GET'

        controller.newsService = Mock(NewsService)

        final List<News> newsList = [new NewsMockBuilder().build()]
        final int currentPage = 1
        final int totalPages = 1
        final Map newsExpected = new NewsListResponse(null, newsList, currentPage, totalPages).toMap()

        when:
        controller.index()

        then:
        1 * controller.newsService.listAll(currentPage) >> newsList
        1 * controller.newsService.getTotalPages() >> totalPages

        view == '/news/index'
        model.toPage == totalPages
        model.fromPage == totalPages
        model.currentPage == currentPage
        model.totalPages == totalPages
        model.offset == 1

        final NewsResponse modelNewsResponse = (model.newsList as List<NewsResponse>).get(0)
        final NewsResponse modelNewsResponseExpected = newsExpected.newsList.get(0)
        modelNewsResponse.id == modelNewsResponseExpected.id
        modelNewsResponse.isRead == modelNewsResponseExpected.isRead
    }

    void "test index, bad request due to page query param"() {
        given:
        request.method = 'GET'

        controller.newsService = Mock(NewsService)
        controller.params.page = 'foo'

        when:
        controller.index()

        then:
        final BadRequestApiException badRequestApiException = thrown(BadRequestApiException)
        badRequestApiException.message == 'page param should be a positive number'
    }

    void "test index, with user logged in"() {
        given:
        request.method = 'GET'

        controller.usersService = Mock(UsersService)
        final User user = new UserMockBuilder().build()
        final String userId = user.id

        final String token = new UserToken(userId).encode()
        SessionUtils.setToken(session, token)

        controller.newsService = Mock(NewsService)
        final List<News> newsList = [new NewsMockBuilder().addReader(user).build()]
        final int totalPages = 10
        final int currentPage = 1

        final Map newsExpected = new NewsListResponse(user, newsList, totalPages, currentPage).toMap()

        when:
        controller.index()
        final NewsResponse modelNewsResponse = (model.newsList as List<NewsResponse>).get(0)
        final NewsResponse modelNewsResponseExpected = newsExpected.newsList.get(0)

        then:
        1 * controller.usersService.findById(userId) >> user
        1 * controller.newsService.listAll(currentPage) >> newsList
        1 * controller.newsService.getTotalPages() >> totalPages

        view == '/news/index'
        modelNewsResponse.id == modelNewsResponseExpected.id
        modelNewsResponse.isRead
        modelNewsResponse.isRead == modelNewsResponseExpected.isRead
    }

    void "test index, with not persisted user logged in"() {
        given:
        request.method = 'GET'

        controller.usersService = Mock(UsersService)
        final User user = new UserMockBuilder().build()
        final String userId = user.id

        final String token = new UserToken(userId).encode()
        SessionUtils.setToken(session, token)

        when:
        controller.index()

        then:
        1 * controller.usersService.findById(userId) >> null
        thrown(BadRequestApiException)
    }

    void "test read, without userId"() {
        given:
        request.method = 'PUT'

        when:
        controller.read()

        then:
        thrown(BadRequestApiException)
    }

    void "test read, with not persisted news"() {
        given:
        request.method = 'PUT'

        controller.usersService = Mock(UsersService)
        final User user = new UserMockBuilder().build()
        final String userId = user.id

        final String token = new UserToken(userId).encode()
        SessionUtils.setToken(session, token)

        controller.newsService = Mock(NewsService)
        final News news = new NewsMockBuilder().build()
        final String newsId = news.id
        controller.params.id = newsId

        when:
        controller.read()

        then:
        1 * controller.usersService.findById(userId) >> user
        1 * controller.newsService.findById(newsId) >> null
        thrown(BadRequestApiException)
    }

    void "test read"() {
        given:
        request.method = 'PUT'

        controller.usersService = Mock(UsersService)
        final User user = new UserMockBuilder().build()
        final String userId = user.id

        final String token = new UserToken(userId).encode()
        SessionUtils.setToken(session, token)

        controller.newsService = Mock(NewsService)
        final News news = new NewsMockBuilder().build()
        final String newsId = news.id
        controller.params.id = newsId

        when:
        controller.read()

        then:
        1 * controller.usersService.findById(userId) >> user
        1 * controller.newsService.findById(newsId) >> news
        response.status == HttpStatus.ACCEPTED.getCode()
    }
}
