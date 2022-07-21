package responses

import enums.NewsChannel
import news.hub.monolithic.News
import news.hub.monolithic.User

import java.time.Instant


class NewsListResponse implements GenericResponse {
    List<NewsResponse> newsList
    int totalPages
    int currentPage

    NewsListResponse(User user, List<News> newsList, int totalPages, int currentPage) {
        List<NewsResponse> newsResponseList = new ArrayList<NewsResponse>()
        newsList.each {news -> { newsResponseList.add(new NewsResponse(news, user))} }

        this.newsList = newsResponseList
        this.totalPages = totalPages
        this.currentPage = currentPage
    }

    Map toMap() {
        final int offset = 1
        final int fromPage = this.currentPage > offset ? this.currentPage - offset : 1
        final int toPage = this.currentPage + offset > this.totalPages ? this.totalPages : this.currentPage + offset

        return [
                newsList: this.newsList,
                totalPages: this.totalPages,
                currentPage: this.currentPage,
                fromPage: fromPage,
                toPage: toPage,
                offset: offset,
        ]
    }
}

class NewsResponse {
    String id
    String title
    String imageUrl
    NewsChannel channel
    String url
    Instant publishedAt
    boolean isRead

    protected NewsResponse(News news, User user) {
        this.id = news.id
        this.title = news.title
        this.imageUrl = news.imageUrl
        this.channel = news.channel
        this.url = news.url
        this.publishedAt = news.publishedAt
        this.isRead = (user && user.id in news.readers.id)
    }
}