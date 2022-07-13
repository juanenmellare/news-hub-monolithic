package enums

import news.hub.monolithic.News
import strategies.channels.ChannelStrategy
import strategies.channels.InfobaeChannelStrategyImpl
import strategies.channels.TNChannelStrategyImpl

enum NewsChannel {
    TN(new TNChannelStrategyImpl()),
    INFOBAE(new InfobaeChannelStrategyImpl()),


    final private ChannelStrategy channelStrategy

    NewsChannel(ChannelStrategy channelStrategy) {
        this.channelStrategy = channelStrategy
    }

    List<News> getLastNews() {
        this.channelStrategy.getLastNews()
    }
}