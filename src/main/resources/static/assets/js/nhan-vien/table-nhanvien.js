 /* --------------------- request get  data  -------------- */
(function () {
    $.ajax({
        type: 'GET',
        url: URL + `api/employee`,
        dataType: 'json',
        success: function (result) {
            table(result.data)
        },
        error: function (error) {
            sweetalert(error)
        }
    })
})()

let table = (data) => {
    // < ----------------------- load data to table  ------------------------------->
    $('#table-employee').DataTable({
        fixedColumns: {leftColumns: 1, rightColumns: 1},
        fixedHeader: true,
        "scrollCollapse": true,
        "responsive": true,
        "serverSize": true,
        "lengthMenu": [[5, 25, 50, -1], [5, 25, 50, "All"]],
        "scroller": true,
        "autoWidth": true,
        "processing": true,
        "scrollY": "250px",
        // "sAjaxSource": URL + 'api/resident',
        "sAjaxDataProp": "",
        "aaData": data,
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "fullName"},
            {
                "mRender": function (data, type, full) {
                    return full.gender ? "Nữ" : "Nam"
                }
            },
            {"mData": "username"},
            {
                "mData": function (data) {
                    let role_name = "";
                    let roles = data.roles;
                    for (i = 0; i < roles.length; i++) {
                        role_name += "" + roles[i].name + ",";
                    }
                    return role_name;
                }
            },
            {"mData": "phone"},
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-update" onclick='showFormUpdate(${full.id},this)' type="button">edit</i>`
                }
            },
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-delete " onclick='deleteEmployee(${full.id},this)' type="button">delete</i>`
                }
            }
        ]
    });
}
// < ------------------------------ Delete --------------------------------->
let deleteEmployee = (id, e) => {
    swal.fire({
        title: 'Cảnh Báo',
        text: 'Bạn có chắc chắn muốn xóa ',
        icon: 'warning', showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                type: 'DELETE',
                url: URL + `api/employee/${id}`,
                contentType: 'application/json',
                cache: false,
                success: function (result) {
                    $('#table-employee').DataTable().row($(e).parents('tr')).remove().draw();
                    sweetalertSuccess(result.message)
                },
                error: function (error) {
                    sweetalertError(error)
                }
            })
        }
    })
}

/*  -------------------  save employee ------------------------ */
document.querySelector('#save').addEventListener('click', () => {
    var employee = getValueForm();
    if(validate(employee)) {
        $.ajax({
            type: 'POST',
            url: URL + `api/employee`,
            contentType: 'application/json',
            dataType: 'json',
            cache: false,
            data: JSON.stringify(employee),
            success: function (result) {
                result.birthday = formatDate(result.birthday);
                $('#table-employee').DataTable().row.add(result.data).draw().node(); /* add new row in table */
                sweetalertSuccess(result.message); /* message */
                cleanForm() // cleanform
            },
            error: function (error) {
                sweetalertError(error)
            }
        });
    }
});


/*  ----------------- show form update ----------------*/
let showFormUpdate = (id) => {
    location.href = URL + `ui/employee/form/${id}`;
}

//< ---------------- clean form when modal close ---------->
$("#form-employee").on("hidden.bs.modal", function () {
    cleanForm();
});


/* -------------------------- clean form ------------------------------ */
let cleanForm = () => {
    document.querySelector("#id").value = "",
        document.querySelector('#fullName').value = "",
        document.querySelector('#birthday').value = "",
        $('input[name="gender"]').prop('checked', false),
        document.querySelector("#address").value = "",
        document.querySelector('#identitycard').value = "",
        document.querySelector('#phone').value = "",
        document.querySelector('#username').value = "",
        document.querySelector('#password').value = "" ,
        document.querySelector('#email').value = "",
        document.querySelector('#role-admin').checked = false,
        document.querySelector('#role-moderator').checked = false,
        document.querySelector('#role-user').checked = false

}
/* ---------------------  get valve form employee ---------------------------------  */
let getValueForm = () => {
    return {
        'id': document.querySelector('#id').value.trim(),
        'fullName': document.querySelector('#fullName').value.trim(),
        'birthday': document.querySelector('#birthday').value.trim(),
        'gender': $("input[name='gender']:checked").val() === 'female',
        'address': document.querySelector('#address').value.trim(),
        'identitycard': document.querySelector('#identitycard').value.trim(),
        'phone': document.querySelector('#phone').value.trim(),
        'username': document.querySelector('#username').value.trim(),
        'password': document.querySelector('#password').value.trim(),
        'email': document.querySelector('#email').value.trim(),
        'roles':$('input[type=checkbox]:checked').map(function(_, role) {
            return $(role).val();
        }).get()
    }
}


/*  ---------------------- clean form when modal close ------------------------- */
document.querySelector('#clean-form').addEventListener('click', cleanForm);

/* ------------------------      Validate form      --------------------------*/
let validate = (data) =>{

    if(data.fullName === ''){
        toastrError("Họ tên không được để trống!");
        document.querySelector('#fullName').focus();
        return false;
    }
    if(data.birthday === ''){
        toastrError("Ngày sinh không được để trống!");
        document.querySelector('#birthday').focus();
        return false;
    }
    if (!$('input[name=gender]:checked').val()) {
        toastrError("Chưa chọn giới tính");
        return false
    }
    if(data.address === ''){
        toastrError("Địa chỉ không được để trống!");
        document.querySelector('#address').focus();
        return false;
    }
    if(data.identitycard === ''){
        toastrError("Số chứng minh - căn cước công dân không được để trống!");
        document.querySelector('#identitycard').focus();
        return false;
    }
    if(isNaN(data.identitycard)){
        toastrError("Số chứng minh - căn cước công dân phải là số!");
        document.querySelector('#identitycard').focus();
        return false;
    }
    if(data.identitycard.length <9 || data.identitycard.length > 12 ){
        toastrError("Số chứng minh - căn cước công dân phải từ 9 đến 12 chữ số!");
        document.querySelector('#identitycard').focus();
        return false;
    }
    if(data.phone === ''){
        toastrError("Số điện thoại không được để trống!");
        document.querySelector('#phone').focus();
        return false;
    }
    if(isNaN(data.phone)){
        toastrError("Số điện thoại phải là số!");
        document.querySelector('#phone').focus();
        return false;
    }
    if(data.phone.length <9 || data.phone.length > 11 ){
        toastrError("Số điện thoại phải từ 9 đến 11 chữ số!");
        document.querySelector('#phone').focus();
        return false;
    }
    if(data.username === ''){
        toastrError("Tên đăng nhập không được để trống!");
        document.querySelector('#username').focus();
        return false;
    }
    if(data.password === ''){
        toastrError("Mật khẩu không được để trống");
        document.querySelector('#password').focus();
        return false;
    }
    if(data.password.length <8 || data.password.length > 12 ){
        toastrError("Mật khẩu phải từ 8 đến 12 ký tự");
        document.querySelector('#password').focus();
        return false;
    }
    if( !$('input[type=checkbox]:checked').val()){
        toastrError("chưa phân quyền cho tài khoản");
        return false;
    }
    return true;

}

