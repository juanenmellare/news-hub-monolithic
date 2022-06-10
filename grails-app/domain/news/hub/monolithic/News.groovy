package news.hub.monolithic

import enums.NewsChannel

import java.time.Instant

class News {
    String id = UUID.randomUUID().toString()
    String title
    String imageUrl
    NewsChannel channel
    String url
    Instant publishedAt

    static constraints = {
        id generator:'assigned'
    }

    News(String title, String imageUrl, NewsChannel channel, String url, Instant publishedAt) {
        this.title = title
        this.imageUrl = imageUrl
        this.channel = channel
        this.url = url
        this.publishedAt = publishedAt
    }
}
