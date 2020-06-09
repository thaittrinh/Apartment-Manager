$(document).ready(function () {
    // < ----------------------- load data to table  ------------------------------->
    $('#my-table').DataTable({
        "responsive": true,
        "scroller": {loadingIndicator: true},
        "autoWidth": false,
        "processing": true,
        "autoWidth": false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "sAjaxSource": URL + 'api/price-water',
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "date"},
            {"mData": "price"},
            {"mData": "note"},
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-update" onclick='showFormUpdate(${full.id},this)' type="button">edit</i>`
                }
            },
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-delete " onclick='deletePrice(${full.id},this)' type="button">delete</i>`
                }
            }
        ]
    });
});


// < ----------------------------- Delete ---------------------------->
let deletePrice = (id, e) => {
    Swal.fire({
        title: 'Cảnh Báo',
        text: "Bạn có chắc  chắn muốn xóa không!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                type: 'DELETE',
                url: URL + `api/price-water/${id}`,
                contentType: "application/json",
                cache: false,
                success: function (result) {
                    $('#my-table').DataTable().row($(e).parents('tr'))
                        .remove()
                        .draw();
                    sweetalert(200, 'Success !', 'Đơn giá nước đã được xóa')
                },
                error: function (error) {
                    sweetalert(500, 'Opps...', 'Lỗi server, vui lòng thử lại sau!')
                }
            });
        }
    })
}

var index = -1;
// < -------------------------- show form update --------------------->
let showFormUpdate = (id, e) => {
    index = $('#my-table').DataTable().row($(e).parents('tr')).index();
    $('#form-building').modal('show')
    document.querySelector('.modal-title').innerHTML = "Cập nhập giá nước";
    $.ajax({
        url: URL + `api/price-water/${id}`,
        type: 'GET',
        dataType: 'json',
        success: function (result) {
            fillToForm(result)
        },
        error: function (error) {
            sweetalert(error.status)
        }
    });
}

// < ------------------------ insert or update  ---------------------->
document.querySelector('#save').addEventListener('click', () => {
    let water = getValueForm();
    if (water.id) {
        $.ajax({
            type: 'PUT',
            url: URL + `api/price-water/${water.id}`,
            contentType: "application/json",
            dataType: 'json',
            cache: false,
            data: JSON.stringify(water),
            success: function (result) {
                result.date = formatDate(result.date);  // Convert date to yy-MM-dd
                $('#my-table').DataTable().row(index).data(result).draw();  //update the row in dataTable
                $('#form-building').modal('hide');     // close modal
                sweetalert(200,'Success!' , ' Cập nhật đơn giá nước thành công')
            },
            error: function (error) {
                sweetalert(error.status)
            }
        });

    } else {
        $.ajax({
            type: 'POST',
            url: URL + `api/price-water`,
            contentType: "application/json",
            dataType: 'json',
            cache: false,
            data: JSON.stringify(water),
            success: function (result) {
                result.date = formatDate(result.date);  // Convert date to yy-MM-dd
                $('#my-table').DataTable()  // Add new data to DataTable
                    .row.add(result)
                    .draw()
                    .node();
                cleanForm(); // Clean form
                sweetalert(200 ,'Tạo đơn giá nước thành công') // message
            },
            error: function (error) {
                sweetalert(error.status)
            }
        });
    }

});

// <------------- When modal close -> clean form modal  ----------->
$("#form-building").on("hidden.bs.modal", function () {
    cleanForm();
});

// < ---------------------- Clean form ---------------------------->
let cleanForm = () => {
    fillToForm({
        "id": "",
        "price": "",
        "date": "",
        "note": ""
    });
}

// < -------------- clean form when click button clean ------------>
document.querySelector('#clean-form').addEventListener('click', cleanForm);

// < ------------------- get value form --------------------------->
let getValueForm = () => {
    return {
        "id": document.querySelector('#id').value,
        "price": document.querySelector('#price').value,
        "date": document.querySelector('#date').value,
        "employee": {
            "id": 1   // set mặc định là nv id = 1  sau lm phần đăng nhập rồi get id sau
        },
        "note": document.querySelector('#note').value
    }
}

// < ------------------- fill to form------------------------------>
let fillToForm = (water) => {
    document.querySelector('#id').value = water.id;
    document.querySelector('#price').value = water.price;
    document.querySelector('#date').value = water.date;
    document.querySelector('#note').value = water.note;
}



