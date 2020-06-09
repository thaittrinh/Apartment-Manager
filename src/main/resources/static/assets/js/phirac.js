$(document).ready(function () {
    // <- ------------------------- load data to table ---------------------------->
    $('#table-garbage').DataTable({
        "responsive": true,
        "scroller": {loadingIndicator: true},
        "autoWidth": false,
        "processing": true,
        "scrollY": "250px",
        "scrollCollapse": true,
        "sAjaxSource": URL + "api/price-garbage",
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

// < ------------------------------- delete -------------------------------------> 
let deletePrice = (id, e) => {
    swal.fire({
        title: 'Cảnh Báo',
        text: "Bạn chắc chắn muốn xóa",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                type: `DELETE`,
                url: URL + `api/price-garbage/${id}`,
                contentType: "application/json",
                cache: false,
                success: function (result) {
                    $('#table-garbage').DataTable().row($(e).parents('tr'))
                        .remove()
                        .draw();
                    toastrmessage(200)
                },
                error: function (error) {
                    toastrmessage(error.status)
                }
            });
        }
    })
}
// < ----------------- show form update ------------------------->
var index = -1;
let showFormUpdate = (id, e) => {
    index = $('#table-garbage').DataTable().row($(e).parents('tr')).index();
    $('#form-building').modal('show')
    document.querySelector('.modal-title').innerHTML = "Cập nhập phí rác ";
    $.ajax({
        url: URL + `api/price-garbage/${id}`,
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
let fillToForm = (garbage) => {
    document.querySelector('#id').value = garbage.id;
    document.querySelector('#price').value = garbage.price;
    document.querySelector('#date').value = garbage.date;
    document.querySelector('#note').value = garbage.note;
}
