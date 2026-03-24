function openTab(evt, tabName) {

    let i, tabcontent, tabbuttons;

    // hide all content
    tabcontent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // remove active class
    tabbuttons = document.getElementsByClassName("tab-btn");
    for (i = 0; i < tabbuttons.length; i++) {
        tabbuttons[i].classList.remove("active");
    }

    // show selected
    document.getElementById(tabName).style.display = "block";

    // active button
    evt.currentTarget.classList.add("active");
}
