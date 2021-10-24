window.createAlert = (text, parentNode, status) => {
    parentNode.innerHTML = "";
    let newSpan = document.createElement("span");
    newSpan.appendChild(document.createTextNode(text));
    newSpan.style.color = status ? "green" : "red";
    newSpan.style.fontWeight = "600";
    parentNode.appendChild(newSpan);
}