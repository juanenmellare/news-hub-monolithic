package news.hub.monolithic

import enums.HttpStatus
import exceptions.BadRequestApiException

import responses.NewsListResponse
import utils.SessionUtils

class NewsController {
    NewsService newsService
    UsersService usersService

    def index() {
        final String userId = SessionUtils.getUserId(session)
        final User user = userId ? findUserOrThrowNotFound(userId) : null

        final List<News> news = this.newsService.listAll()

        final NewsListResponse newsListResponse = new NewsListResponse(news, user)

        render view: "/news/index", model: newsListResponse.toMap()
    }

    def read() {
        final String userId = SessionUtils.getUserId(session)
        if (!userId) {
            throw new BadRequestApiException()
        }

        final User user = this.findUserOrThrowNotFound(userId)

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
            throw new BadRequestApiException()
        }
        return user
    }
}
