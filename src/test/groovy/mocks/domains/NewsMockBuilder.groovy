package mocks.domains

import enums.NewsChannel
import news.hub.monolithic.News
import news.hub.monolithic.User
import responses.NewsResponse

import java.time.Instant

class NewsMockBuilder {
    News news = new News('Foo news', 'www.foo.com/image', NewsChannel.TN, 'www.foo.com', Instant.now())

    NewsMockBuilder setId(String id) {
        this.news.setId(id)
        return this
    }

    NewsMockBuilder setTitle(String title) {
        this.news.setTitle(title)
        return this
    }

    NewsMockBuilder setImageUrl(String imageUrl) {
        this.news.setImageUrl(imageUrl)
        return this
    }

    NewsMockBuilder setChannel(NewsChannel channel) {
        this.news.setChannel(channel)
        return this
    }

    NewsMockBuilder setUrl(String url) {
        this.news.setUrl(url)
        return this
    }

    NewsMockBuilder setPublishedAt(Instant publishedAt) {
        this.news.setPublishedAt(publishedAt)
        return this
    }

    NewsMockBuilder addReader(User user) {
        this.news.readers = [user]
        return this
    }

    NewsMockBuilder clear() {
        this.news = new News()
        return this
    }

    NewsMockBuilder save() {
        this.news.save(flush: true, failOnError: true)
        return this
    }

    News build() {
        return this.news
    }
}
