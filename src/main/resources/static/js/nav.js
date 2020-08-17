const navLinkList = document.getElementsByClassName("nav-link");

function init() {
    const presentPathName = getFirstPathname(window.location.pathname);
    for(let item of navLinkList){
        const navPathname = getFirstPathname(new URL(item.href).pathname);
        if(presentPathName === navPathname){
            item.parentElement.classList.add("active");
        } else {
            item.parentElement.classList.remove("active");
        }
    }
}

function getFirstPathname(pathname){
    return pathname.split('/')[1];
}

init();