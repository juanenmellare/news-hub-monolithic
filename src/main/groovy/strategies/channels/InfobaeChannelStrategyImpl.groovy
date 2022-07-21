package strategies.channels

import com.gargoylesoftware.htmlunit.html.*
import enums.NewsChannel
import news.hub.monolithic.News

import java.time.Instant


class InfobaeChannelStrategyImpl extends ChannelStrategyImpl {

    InfobaeChannelStrategyImpl() {
        super('https://www.infobae.com', '/ultimas-noticias/')
    }

    @Override
    protected void setupWebClient() {
        this.getWebClient().getOptions().setJavaScriptEnabled(false)
    }

    @Override
    protected NewsChannel getChannel() {
        return NewsChannel.INFOBAE
    }

    @Override
    List<News> getLastNews() {
        final List<News> newsList = new ArrayList<News>()

        final String baseUrl = this.getBaseUrl()
        final HtmlPage lastNewsPage = this.getWebClient().getPage(this.getLastNewsUrl())
        final List<HtmlAnchor> anchors = lastNewsPage.getByXPath("//a[@class='nd-feed-list-card']")
        anchors.each {anchor ->
            final String url = baseUrl + anchor.getHrefAttribute()
            final HtmlImage image = anchor.getFirstByXPath(".//img[@class='feed-list-image']")
            final HtmlHeading2 h2 = anchor.getFirstByXPath(".//h2[@class='nd-feed-list-card-headline-lean']")
            final String title = h2.getTextContent()
            final Instant publishedAt = Instant.now()
            final News news = new News(title, image?.getSrc(), this.getChannel(), url, publishedAt)

            newsList.push(news)
        }

        return newsList
    }
}
