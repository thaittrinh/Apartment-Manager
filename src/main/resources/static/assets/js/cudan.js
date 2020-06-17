$(document).ready(function () {
    // < ----------------------- load data to table  ------------------------------->
    $('#table-resident').DataTable({
        "paging": true,
        "serverSize": true,
        "lengthMenu": [[5, 25, 50, -1], [5, 25, 50, "All"]],
        "responsive": true,
        "scroller": true,
        "autoWidth": true,
        "processing": true,
        "scrollY": "250px",
        "sAjaxSource": URL + 'api/resident',
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "fullname"},
            {"mRender": function (data, type, full) {
                return full.gender ? "Female" : "Male"}
            },
            {"mData": "birthday"},
            {"mData": "job"},
            {"mData": "phone"},
            {"mData": "apartment.id"},
            {"mRender": function (data, type, full) {
                return `<i  class="material-icons icon-table icon-update" onclick='showFormUpdate(${full.id},this)' type="button">edit</i>`
            }
            },
            {"mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-delete " onclick='deleteResident(${full.id},this)' type="button">delete</i>`
                }
            }
        ]
    });
});


// <---------------------------------- Delete --------------------------->
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
                    $('#table-resident').DataTable().row($(e).parents('tr')).remove().draw();
                    sweetalert(200, 'Success!', 'Đã xóa cư dân ')
                },
                error: function (error) {
                    sweetalert(error.status)
                }
            })
        }
    })
}

 // ------------------- change title ------------------------
let changetitle = () => {
    document.querySelector('#form-label').innerHTML =
        "<i class='fas fa-address-card mr-3'></i>" + 'Thêm Cư Dân'
}

// < ----------------- show form update -------------------->
var index = -1;
let showFormUpdate = (id, e) => {
    index = $('#table-resident').DataTable().row($(e).parents('tr')).index();
    $('#form-resident').modal('show')
    document.querySelector('.modal-title').innerHTML = "<i class='fas fa-address-card mr-3'></i>" + "Cập Nhật Thông Tin Cư Dân";
    $.ajax({
        url: URL + `api/resident/${id}`,
        type: 'GET',
        dataType: 'json',
        success: function (result) {
            fillToFrom(result)
        },
        error: function (error) {
            sweetalert(error.status)
        }
    })
}

// < ------------------------- insert or update -------------------->
document.querySelector('#saveResident').addEventListener('click', () => {
    let resident = getValueForm();
    if (validate(resident)) {
        if (resident.id) {
            // < ------------------- update ---------------------->
            $.ajax({
                type: 'PUT',
                url: URL + `api/resident/${resident.id}`,
                contentType: 'application/json',
                dataType: 'json',
                cache: false,
                data: JSON.stringify(resident),
                success: function (result) {
                    result.birthday = formatDate(result.birthday);  // Convert date to yy-MM-dd
                    $('#table-resident').DataTable().row(index).data(result).draw();  //update the row in dataTable
                    $('#form-resident').modal('hide');     // close modal
                    sweetalert(200, 'Success!', 'Đã cập nhật thông tin cư dân ');
                },
                error: function (error) {
                    if (error.status === 409) {
                        Swal.fire({
                            title: 'Error!',
                            text: 'Số chứng minh thư đã tồn tại, vui lòng kiểm tra lại ',
                            icon: 'error'
                        })
                    }
                    sweetalert(error.status)
                }
            })
        } else {
            // < ----------------------- create ----------------->
            $.ajax({
                type: 'POST',
                url: URL + `api/resident`,
                contentType: 'application/json',
                dataType: 'json',
                cache: false,
                data: JSON.stringify(resident),
                success: function (result) {
                    result.birthday = formatDate(result.birthday)
                    $('#table-resident').DataTable().row.add(result).draw().node();
                    sweetalert(200, 'Success!', 'Đã thêm cư dân mới ')
                    cleanFrom();
                },
                error: function (error) {
                    if (error.status === 409) {
                        swal.fire({
                            title: 'Error',
                            text: 'Số chứng minh nhân dân đã tồn tại, vui lòng kiểm tra lại!',
                            icon: 'error'
                        })
                    }
                    sweetalert(error.status)
                }
            })
        }
    }
})


//< ---------------- clean form when modal close ---------->
$("#form-resident").on("hidden.bs.modal", function () {
    cleanFrom();
});


// < -------------------- clean form ----- ---------------->
let cleanFrom = () => {
    document.querySelector("#id").value = "",
        document.querySelector('#fullname').value = "",
        document.querySelector('#birthday').value = "",
        $('input[name="gender"]').prop('checked', false),
        document.querySelector("#hometown").value = "",
        document.querySelector('#job').value = "",
        document.querySelector('#phone').value = "",
        document.querySelector('#email').value = "",
        document.querySelector('#identitycard').value = "" ,
        document.querySelector('#idapartment').value = ""
}

// < -------------- clean form when click button clean ------------>
document.querySelector('#clean-form-resident').addEventListener('click', cleanFrom);


// < ------------------- get value form ------------------------>
let getValueForm = () => {
    return {
        'id': document.querySelector("#id").value.trim(),
        'fullname': document.querySelector('#fullname').value.trim(),
        'birthday': document.querySelector('#birthday').value.trim(),
        'gender': $("input[name='gender']:checked").val() == 'female' ? true : false,
        'hometown': document.querySelector("#hometown").value.trim(),
        'job': document.querySelector('#job').value.trim(),
        'phone': document.querySelector('#phone').value.trim(),
        'email': document.querySelector('#email').value.trim(),
        'identitycard': document.querySelector('#identitycard').value.trim(),
        'apartment': {"id": document.querySelector('#idapartment').value.trim()}
    }


}
// < ------------------ fill to form -------------------------->
let fillToFrom = (resident) => {
    document.querySelector("#id").value = resident.id,
        document.querySelector('#fullname').value = resident.fullname,
        document.querySelector('#birthday').value = resident.birthday,
        $(resident.gender ? "#female" : "#male").prop('checked', true),
        document.querySelector("#hometown").value = resident.hometown,
        document.querySelector('#job').value = resident.job ,
        document.querySelector('#phone').value = resident.phone,
        document.querySelector('#email').value = resident.email,
        document.querySelector('#identitycard').value = resident.identitycard,
        document.querySelector('#idapartment').value = resident.apartment.id
}

let validate = (data) => {
    if (data.fullname === '') {
        toastrError("Họ tên không được để trống!");
        document.querySelector('#fullname').focus();
        return false;
    }
    if (data.birthday === '') {
        toastrError("Ngày sinh không được để trống");
        document.querySelector('#birthday').focus();
        return false
    }
    if (!$('input[name=gender]:checked').val()) {
        toastrError("Chưa chọn giới tính");
        return false
    }
    if (data.hometown === '') {
        toastrError("Quê quán không được để trống");
        document.querySelector('#hometown').focus();
        return false
    }
    if (data.apartment.id === '') {
        toastrError("Mã căn hộ không được để trống");
        document.querySelector('#idapartment')
        return false

    }
    return true;
}

