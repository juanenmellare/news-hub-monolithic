package responses

import enums.NewsChannel
import news.hub.monolithic.News
import news.hub.monolithic.User

import java.time.Instant


class NewsListResponse implements GenericResponse {
    List<NewsResponse> newsList

    NewsListResponse(List<News> newsList, User user) {
        List<NewsResponse> newsResponseList = new ArrayList<NewsResponse>()
        newsList.each {news -> { newsResponseList.add(new NewsResponse(news, user))} }

        this.newsList = newsResponseList
    }

    Map toMap() {
        return [
                newsList: this.newsList,
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