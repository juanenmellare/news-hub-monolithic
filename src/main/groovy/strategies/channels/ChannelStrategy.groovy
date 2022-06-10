package strategies.channels

import news.hub.monolithic.News


interface ChannelStrategy {
    List<News> getLastNews()
}
