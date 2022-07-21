package news.hub.monolithic

import enums.HttpStatus
import exceptions.BadRequestApiException

import responses.NewsListResponse
import tokens.UserToken
import utils.SessionUtils

class NewsController {
    NewsService newsService
    UsersService usersService

    def index() {
        final String token = SessionUtils.getToken(session)
        final UserToken userToken = new UserToken()
        userToken.decode(token)

        final User user = userToken.isVerified() ? findUserOrThrowNotFound(userToken.getUserId()) : null

        def pageParam = params.page
        if (pageParam && !pageParam.toString().isInteger()) {
            throw new BadRequestApiException("page param should be a positive number")
        }
        int currentPage = 1
        if (pageParam) {
            pageParam = params.page as int
            if (pageParam > 0) {
                currentPage = pageParam
            }
        }

        final List<News> news = this.newsService.listAll(currentPage)
        final int totalPages = this.newsService.getTotalPages()

        final NewsListResponse newsListResponse = new NewsListResponse(user, news, totalPages, currentPage)

        render view: "/news/index", model: newsListResponse.toMap()
    }

    def read() {
        final String token = SessionUtils.getToken(session)
        final UserToken userToken = new UserToken()
        userToken.decode(token)
        if (!userToken.isVerified()) {
            throw new BadRequestApiException()
        }

        final User user = this.findUserOrThrowNotFound(userToken.getUserId())

        final String newsId = params.id
        final News news = this.newsService.findById(newsId)
        if (!news) {
            throw new BadRequestApiException()
        }

        this.newsService.addReader(news, user)

        final HttpStatus httpStatus = HttpStatus.ACCEPTED
        response.status = httpStatus.getCode()
        render(contentType: "application/json") {[
                'status': httpStatus.getStatus()
        ]}
    }

    private User findUserOrThrowNotFound(String userId) throws BadRequestApiException {
        final User user = this.usersService.findById(userId)
        if (!user) {
            session.invalidate()
            throw new BadRequestApiException()
        }
        return user
    }
}
