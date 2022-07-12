package news.hub.monolithic

import enums.HttpStatus
import exceptions.BadRequestApiException

import responses.NewsListResponse
import utils.SessionUtil

class NewsController {
    NewsService newsService
    UsersService usersService

    def index() {
        final String userId = SessionUtil.getUserId(session)
        final User user = userId ? findUserOrThrowNotFound(userId) : null

        final List<News> news = this.newsService.listAll()

        final NewsListResponse newsListResponse = new NewsListResponse(news, user)

        render view: "/news/index", model: newsListResponse.toMap()
    }

    def read() {
        final String userId = SessionUtil.getUserId(session)
        if (!userId) {
            render status: HttpStatus.ACCEPTED.code
        } else {
            final User user = findUserOrThrowNotFound(userId)

            final String newsId = params.id
            final News news = this.newsService.findById(newsId)
            if (!news) {
                throw new BadRequestApiException()
            }

            this.newsService.addReader(news, user)

            render status: HttpStatus.ACCEPTED.code
        }
    }

    private User findUserOrThrowNotFound(String userId) throws BadRequestApiException {
        final User user = this.usersService.findById(userId)
        if (!user) {
            throw new BadRequestApiException()
        }
        return user
    }
}
