package news.hub.monolithic

import enums.NewsChannel
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import groovyx.gpars.GParsPool


@Service
class NewsService {

    List<News> getLastNewsFromChannels() {
        final List<News> news = new ArrayList<News>()

        final List<NewsChannel> newsChannels = NewsChannel.values().toList()
        GParsPool.withPool {
            final List<News> newsCollected =
                    newsChannels.collectParallel{newsChannel -> newsChannel.getLastNews()}
                            .collect().flatten() as List<News>
            news.addAll(newsCollected)
        }

        return news
    }

    @Transactional
    void fetchAndSave() {
        final List<News> news = this.getLastNewsFromChannels()
        final List<News> newsAlreadySaved = News.findAllByUrlInList(news.url)
        final List<News> unsavedNews = news.findAll{candidateNews -> !(candidateNews.url in newsAlreadySaved.url)}

        News.saveAll(unsavedNews)
    }

    List<News> listAll() {
        this.fetchAndSave()
        final List<News> news = News.findAll().sort { a, b -> b.publishedAt <=> a.publishedAt }

        return news
    }
}
