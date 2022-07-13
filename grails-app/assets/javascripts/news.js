window.addEventListener("load", _ => {
    const images = document.querySelectorAll('.card-news-image');
    const areImagesLoaded = Array.from(images).every(image => image.complete);
    if (areImagesLoaded) {
        const masonry = new Masonry('.container-card-news');
        masonry.layout();
    }
});

const openNewsInTab = (url) => {
    window.open(url, '_blank').focus();
    return true;
}