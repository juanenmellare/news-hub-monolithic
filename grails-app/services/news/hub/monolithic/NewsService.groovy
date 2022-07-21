package news.hub.monolithic

import enums.NewsChannel
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import groovyx.gpars.GParsPool


@Service
class NewsService {
    private final int newsPerPage = 20

    News findById(String id) {
        News.findById(id)
    }

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

    List<News> listAll(int page) {
        final int offset = (page - 1) * newsPerPage
        final String sortField = 'publishedAt'
        final String sortOrder = 'desc'

        return News.findAll([max: newsPerPage, offset: offset, sort: sortField, order: sortOrder])
    }

    int getTotalPages() {
        return Math.ceil(News.count().intValue() / newsPerPage).intValue()
    }

    @Transactional
    void addReader(News news, User user) {
        if (!(user in news.readers)) {
            news.addToReaders(user)
            news.save()
        }
    }


    @Transactional
    void fetchAndSave() {
        final List<News> news = this.getLastNewsFromChannels()
        final List<News> newsAlreadySaved = News.findAllByUrlInList(news.url)
        final List<News> unsavedNews = news.findAll{candidateNews -> !(candidateNews.url in newsAlreadySaved.url)}
        News.saveAll(unsavedNews)
    }
}
