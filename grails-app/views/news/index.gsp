<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home</title>
    <asset:stylesheet src="news.css"/>
</head>
<body>
<div class="row container-card-news" data-masonry='{"percentPosition": true }'>
    <g:each in="${newsList}">
        <div class="col-xs-6 col-sm-4 col-md-4 col-lg-3 card-news">
            <div class="card border-danger">
                <a href="${it.url}" target="_blank">
                    <img class="card-img-top" src="${it.imageUrl}" alt="${it.title}">
                    <div class="card-header card-header-news">${it.title}</div>
                    <div class="card-body card-body-news">
                        <asset:image src="/channels/${it.channel.name()}.png" height="12" class="card-channel-image"/>
                        <p class="card-news-datetime">
                            <g:formatDate class="card-date" format="dd/MM/yyyy hh:mm" date="${it.publishedAt}"/>
                        </p>
                    </div>
                </a>
            </div>
        </div>
    </g:each>
</div>
</body>
</html>
