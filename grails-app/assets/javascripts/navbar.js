const navbarLinkElements = document.querySelectorAll(".nav-link.navbar__items__link");
const navbarLinkActiveClass = "active";

navbarLinkElements.forEach(({href, classList}) => {
    if (href === window.location.href) {
        classList.add(navbarLinkActiveClass);
    }
})

