<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home</title>
    <asset:stylesheet src="news.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
<div class="row container-card-news" data-masonry='{"percentPosition": true }'>
    <g:each in="${newsList}">
        <div class="col-xs-6 col-sm-4 col-md-4 col-lg-3 card-news">
            <div class="card border-danger">
                <a href="${it.url}" target="_blank">
                    <a onClick="openNewsInTab('${it.url}')"
                       href=<g:createLink controller="news" action="read" params="[id: it.id]"/>>
                        <g:if test="${it.isRead}">
                            <i class="card-news-icon-read bi bi-eyeglasses"></i>
                        </g:if>
                        <img class="card-img-top" src="${it.imageUrl}" alt="${it.title}">
                        <div class="card-header card-header-news">${it.title}</div>
                        <div class="card-body card-body-news">
                            <asset:image src="/channels/${it.channel.name()}.png" height="12" class="card-channel-image"/>
                            <p class="card-news-datetime">
                                <g:formatDate class="card-date" format="dd/MM/yyyy HH:mm" date="${it.publishedAt}"/>
                            </p>
                        </div>
                    </a>
                </a>
            </div>
        </div>
    </g:each>
</div>
<asset:javascript src="news.js"/>
</body>
</html>
