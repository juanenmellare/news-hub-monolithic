package news.hub.monolithic

class NewsController {
    NewsService newsService

    def index() {
        final List<News> newsList = this.newsService.listAll()

        render view: "/news/index", model: [newsList: newsList]
    }
}
