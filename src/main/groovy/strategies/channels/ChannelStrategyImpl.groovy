package strategies.channels

import com.gargoylesoftware.htmlunit.WebClient
import enums.NewsChannel


abstract class ChannelStrategyImpl implements ChannelStrategy {
    private WebClient webClient = new WebClient()
    private String baseUrl
    private String lastNewsUrl

    ChannelStrategyImpl(String baseUrl, String lastNewsUrl) {
        this.baseUrl = baseUrl
        this.lastNewsUrl = lastNewsUrl
        this.setupWebClient()
    }

    abstract protected void setupWebClient()

    abstract protected NewsChannel getChannel()

    protected WebClient getWebClient() {
        return this.webClient
    }

    protected String getBaseUrl() {
        return this.baseUrl
    }

    protected String getLastNewsUrl() {
        return this.getBaseUrl() + this.lastNewsUrl
    }
}
