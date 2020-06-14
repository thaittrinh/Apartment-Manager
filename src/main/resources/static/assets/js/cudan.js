$(document).ready(function () {
    // < ----------------------- load data to table  ------------------------------->
    $('#table-resident').DataTable({
        "responsive": true,
        "scroller": {loadingIndicator: true},
        "autoWidth": true,
        "processing": true,
        "scrollY": "270px",
        "scrollX": "100px",
        "scrollCollapse": true,
        "sAjaxSource": URL + 'api/resident',
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "fullname"},
            {
                "mRender": function (gender) {
                    return gender ? 'Male' : 'Female';
                }
            },
            {"mData": "birthday"},
            {"mData": "job"},
            {"mData": "phone"},
            {"mData": "apartment.id"},
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-update" onclick='showFormUpdate(${full.id},this)' type="button">edit</i>`
                }
            },
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-delete " onclick='deleteResident(${full.id},this)' type="button">delete</i>`
                }
            }
        ]
    });

});
// <--------------- Delete ---------------->
let deleteResident = (id, e) => {
    swal.fire({
        title: 'Cảnh Báo',
        text: "Bạn chắc chắn muốn xóa",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((reslut) => {
        if (reslut.value) {
            $.ajax({
                type: 'DELETE',
                url: URL + `api/resident/${id}`,
                contentType: 'application/json',
                cache: false,
                success: function (result) {
                    $('#table-resident').DataTable().row($(e).parents('tr'))
                    sweetalert(200, 'Success!', 'Đã xóa cư dân ')

                },
                error: function (error) {
                    sweetalert(error.status)

                }
            })
        }
    })
}
// < ----------------- show form update -------------------->
var index = -1;
let showFormUpdate = (id, e) => {
    index = $('#table-resident').DataTable().row($(e).parent('tr')).index
    $('#form-resident').modal('show')
    document.querySelector('.modal-title').innerHTML = "Cập Nhật Thông Tin Cư Dân"
    $.ajax({
        url: URL + `api/resident/${id}`,
        type: 'GET',
        dataType: 'json',
        success: function (result) {
         fillToFrom(result);
        },
        error: function (error) {
            sweetalert(error.stastus)
        }
    })
}
// < ------------------ fill to form -------------------------->
let fillToFrom = (resident, apartment) =>{
    document.querySelector('#id').value = resident.id;
    document.querySelector('#fullname').value = resident.fullname;
    document.querySelector('#birthday').value = resident.birthday;
    document.querySelector('#gender').checked === ? false : true;
    document.querySelector('#nationality').value = resident.nationality;
    document.querySelector('#hometown').value = resident.hometown;
    document.querySelector('#job').value = resident.job;
    document.querySelector('#phone').value = resident.phone;
    document.querySelector('#email').value = resident.email;
    document.querySelector('#identitycard').value = resident.identitycard;
    document.querySelector('#idapartment').value = apartment.id;
}