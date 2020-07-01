const inputs = document.querySelectorAll(".input")


function addcl() {
    let parent = this.parentNode.parentNode;
    parent.classList.add("focus");
}

function remcl() {
    let parent = this.parentNode.parentNode;
    if (this.value === "") {
        parent.classList.remove("focus");
    }
}


inputs.forEach(input => {
    input.addEventListener("focus", addcl);
    input.addEventListener("blur", remcl);
});

function validateForm() {
    var username = document.forms["formlogin"]["username"].value;
    var password = document.forms["formlogin"]["password"].value;

    if (username , password === "") {
        document.getElementById('message').innerHTML = 'vui lòng nhập tên đăng nhập và mật khẩu ';
        return false;
    }
}
