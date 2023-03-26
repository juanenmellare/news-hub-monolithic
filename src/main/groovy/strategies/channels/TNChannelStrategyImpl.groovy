package strategies.channels

import com.gargoylesoftware.htmlunit.html.*
import enums.NewsChannel
import news.hub.monolithic.News

import java.time.Instant


class TNChannelStrategyImpl extends ChannelStrategyImpl {

    TNChannelStrategyImpl() {
        super('https://tn.com.ar', '/ultimas-noticias/')
    }

    @Override
    protected void setupWebClient() {
        this.getWebClient().getOptions().setJavaScriptEnabled(false)
    }

    @Override
    protected NewsChannel getChannel() {
        return NewsChannel.TN
    }

    @Override
    List<News> getLastNews() {
        final List<News> newsList = new ArrayList<News>()

        final String baseUrl = this.getBaseUrl()
        final HtmlPage lastNewsPage = this.getWebClient().getPage(this.getLastNewsUrl())
        final List<HtmlArticle> articles = lastNewsPage.getByXPath("//article[@class='card__container card__horizontal']")
        articles.each {article ->
            final HtmlImage image = article.getFirstByXPath(".//img[@class='image image_placeholder']")

            final HtmlHeading2 h2 = article.getFirstByXPath(".//h2[@class='card__headline']")
            final HtmlAnchor anchor = h2.getFirstByXPath(".//a")
            final String title = anchor.getTextContent()
            final String url = baseUrl + anchor.getHrefAttribute()

            final HtmlPage articlePage = this.getWebClient().getPage(url)
            final HtmlTime time = articlePage.getFirstByXPath("//time[@class='time__container']")
            final Instant publishedAt = Instant.parse(time.getAttribute('datetime').replace("+00:00", "Z"))
            final News news = new News(title, image.getSrc(), this.getChannel(), url, publishedAt)

            newsList.add(news)
        }

        return newsList
    }
}
