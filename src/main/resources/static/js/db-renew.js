function check_all()
{
    let sws = document.getElementsByClassName("sw");
    for (sw of sws) {
        sw.checked = true;
    }

    let btn = document.getElementById("check-all-btn");
    btn.textContent = "Unselect all";
    btn.onclick = function() { uncheck_all(); };
}

function uncheck_all()
{
    let sws = document.getElementsByClassName("sw");
    for (sw of sws) {
        sw.checked = false;
    }

    let btn = document.getElementById("check-all-btn");
    btn.textContent = " Select all ";
    btn.onclick = function() { check_all(); };
}

let logoutBtn = document.getElementById("logout-btn");
logoutBtn.addEventListener("click", function(){
    document.location.href = '/db/logout';
});