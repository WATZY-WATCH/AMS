let scheduleBtn = document.querySelectorAll(".group-schedule");
scheduleBtn.forEach(el=> {
    let scheduleLink = el.querySelector("a").href;
    el.onclick = function() {
        location.href = scheduleLink;
    }
})