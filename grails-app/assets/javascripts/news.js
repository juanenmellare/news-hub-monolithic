window.addEventListener("load", _ => {
    const images = document.querySelectorAll('.card-news-image');
    const areImagesLoaded = Array.from(images).every(image => image.complete);
    if (areImagesLoaded) {
        const masonry = new Masonry('.container-card-news');
        masonry.layout();
    }
});

const readNews = (isRead, id, url) => {
    window.open(url, '_blank').focus();
    if (isLogged && !isRead) {
        const xmlHttp = new XMLHttpRequest();
        xmlHttp.open("PUT", `/news/read/${id}`, false);
        xmlHttp.send(null);
        document.location.reload();
    }
}