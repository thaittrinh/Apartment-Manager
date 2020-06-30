/* ------------------------------ Change Password ------------------------------ */
document.querySelector('#changepassword').addEventListener('click', () => {
    var changepassword = getValueFormPassword();
    if (validateFormChangePassword(changepassword)) {
        Swal.fire({
            title: 'Cảnh báo',
            text: "Bạn có chắc chắn muốn đổi mật khẩu không!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#28a745',
            cancelButtonColor: '#d33',
            cancelButtonText: 'Hủy Bỏ',
            confirmButtonText: 'Xác nhận '
        }).then((result) => {
            if (result.value) {
                $.ajax({
                    type: 'PUT',
                    url: URL + `api/employee/change-password`,
                    contentType: 'application/json',
                    dataType: 'json',
                    cache: false,
                    data: JSON.stringify(changepassword),
                    success: function (result) {
                        $('#form-change-password').modal('hide');
                        sweetalertSuccess(result.message)
                        LogoutAfterChangePassword();
                    },
                    error: function (error) {
                        sweetalertError(error)
                    }
                });
            }
        })
    }
});

//  auto logout after when change pasword success
function LogoutAfterChangePassword() {
    let timerInterval
    Swal.fire({
        text: "Đang đăng xuất tài khoản !",
        timer: 2000,
        onBeforeOpen: () => {
            Swal.showLoading()
            timerInterval = setInterval(() => {
                const content = Swal.getContent()
                if (content) {
                    const b = content.querySelector('b')
                    if (b) {
                        b.textContent = Swal.getTimerLeft()
                    }
                }
            }, 100)
        },
        onClose: () => {
            clearInterval(timerInterval)
        }
    }).then((result) => {
        document.getElementById("logout").click();
    })
}

/* ----------------------Toggle Password ------------------- */
function TogglePassword() {
    var password = document.getElementById('Password')
    var newPassword = document.getElementById('newPassword')
    var confirmPassword = document.getElementById('confirmPassword')
    if (confirmPassword.type, newPassword.type, password.type === "password") {
        password.type = "text";
        newPassword.type = "text"
        confirmPassword.type = "text"
    } else {
        password.type = "password";
        newPassword.type = "password"
        confirmPassword.type = "password"
    }
}

/* ---------------------  get valve form ---------------------------------  */
let getValueFormPassword = () => {
    return {
        'id': 1,
        'password': document.querySelector('#Password').value.trim(),
        'newpassword': document.querySelector('#newPassword').value.trim(),
    }
}


/* ------------------- clean form ------------------------- */
let cleanFormChangePassword = () => {
    document.getElementById('Password').value = "";
    document.getElementById('newPassword').value = "";
    document.getElementById('confirmPassword').value = ""
}

/* ---------------- clean form when modal close ---------- */
$("#form-change-password").on("hidden.bs.modal", function () {
    cleanFormChangePassword()
});

/*  ---------------------- clean form when click btn ------------------------- */
document.querySelector('#clean-form-change-password').addEventListener('click', cleanFormChangePassword);

/* --------------------- validate form  ---------------------------------  */
let validateFormChangePassword = (data) => {
    let password = document.querySelector('#Password').value
    let newPassword = document.querySelector('#newPassword').value
    let confirmPassword = document.querySelector('#confirmPassword').value

    if (data.password === '') {
        toastrError('Chưa nhập mật khẩu cũ')
        document.querySelector('#Password').focus();
        return false
    }
    if (data.password.length < 6 || data.password.length > 12) {
        toastrError('Mật khẩu tối thiểu 6 đến 12 ký tự');
        document.querySelector('#Password').focus();
        return false
    }
    if (password === newPassword) {
        toastrWarning('Bạn đã nhập mật khẩu cũ! hãy thử một mật khẩu khác ')
        document.querySelector('#newPassword').focus();
        return false
    }
    if (data.newpassword === '') {
        toastrError('Chưa nhập mật khẩu mới')
        document.querySelector('#newPassword').focus();
        return false
    }

    let special = data.newpassword.match((/[!@#$%^&*_]+/g));
    if (special != null) {
        toastrError('Mật khẩu không được chứa ký tự đặc biệt');
        document.querySelector('#newPassword').focus();
        return false
    }
    if (data.newpassword.search(/[a-z]/) < 0) {
        toastrError("Mật khẩu phải có ít nhất một chữ cái")
        document.querySelector('#newPassword').focus();
        return false
    }
    if (data.newpassword.search(/[A-Z]/) < 0) {
        toastrError("Mật khẩu phải có ít nhất một chữ cái viết hoa")
        document.querySelector('#newPassword').focus();
        return false
    }
    if (data.newpassword.search(/[0-9]/) < 0) {
        toastrError("Mật khẩu phải có ít nhất một chữ số")
        document.querySelector('#newPassword').focus();
        return false
    }
    if (data.newpassword.length < 8 || data.newpassword.length > 12) {
        toastrError('Mật khẩu tối thiểu 8 đến 12 ký tự');
        document.querySelector('#newPassword').focus();
        return false
    }

    if (document.querySelector('#confirmPassword').value === '') {
        toastrError('Chưa nhập xác nhận mật khẩu');
        document.querySelector('#confirmPassword').focus();
        return false
    }

    if (newPassword != confirmPassword) {
        toastrError('Xác Nhận mật khẩu không đúng');
        return false
    }
    return true
}