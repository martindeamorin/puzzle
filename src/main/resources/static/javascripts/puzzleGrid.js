
window.addEventListener("load", () => {
    
    window.galleryDivs = document.querySelectorAll(".gallery-div");
    window.galleryContainer = document.querySelector(".grid");
    window.draggedElement = undefined;
    window.changedElement = undefined;
    
    function updateGallery() {  
        galleryDivs.forEach((elem, index) => {
    
            elem.addEventListener("dragstart", (e) => {
                window.draggedElement = { elem, index };
            })
    
            elem.addEventListener("dragover", (e) => {
                e.preventDefault();
                if (elem !== window.draggedElement) {
                    elem.style.opacity = "0.5";
                }
            })
    
            elem.addEventListener("dragleave", (e) => {
                e.preventDefault();
                if (elem !== window.draggedElement) {
    
                    elem.style.opacity = "1";
                }
            })
    
            elem.addEventListener("drop", (e) => {
                e.preventDefault();
                window.galleryDivs = document.querySelectorAll(".gallery-div")
                window.changedElement = { elem, index };
                elem.style.opacity = "1";
    
                for (let i = 0; i < window.galleryDivs.length; i++) {
                    if (window.galleryDivs[i] === window.changedElement.elem) {
                        window.galleryContainer.appendChild(window.draggedElement.elem)
                    } else if (galleryDivs[i] === window.draggedElement.elem) {
                        window.galleryContainer.appendChild(window.changedElement.elem)
                    } else {
                        window.galleryContainer.appendChild(window.galleryDivs[i])
                    }
    
                }
    
            })
        })
    }


    window.updateGallery = updateGallery;

    
})