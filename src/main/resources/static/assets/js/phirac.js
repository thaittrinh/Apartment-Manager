$(document).ready(function () {
    // <- ------------------------- load data to table ---------------------------->
    $('#table-garbage').DataTable({
        "responsive": true,
        "scroller": {loadingIndicator: true},
        "autoWidth": true,
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
            {"mData": "employee.fullName"},
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
                    sweetalert(200, 'Success!', 'Đã tạo phí rác ')
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
// <-------------------------------- insert or update ----------------------------->
document.querySelector('#save').addEventListener('click', () => {
    let garbage = getValueForm();
    // ------------------ update ----------------------->
    if (validate(garbage)) {
        if (garbage.id) {
            $.ajax({
                type: 'PUT',
                url: URL + `api/price-garbage/${garbage.id}`,
                contentType: "application/json",
                dataType: 'json',
                cache: false,
                data: JSON.stringify(garbage),
                success: function (result) {
                    result.date = formatDate(result.date);  // Convert date to yy-MM-dd
                    $('#table-garbage').DataTable().row(index).data(result).draw();  //update the row in dataTable
                    $('#form-building').modal('hide');     // close modal
                    sweetalert(200, 'Success!', ' Đã cập nhật phí rác')
                },
                error: function (error) {
                	if(error.status === 409){
	            		 Swal.fire({
	                         title : 'Error',
	                         text: 'Giá trong tháng đã tồn tại!!!',
	                         icon:'error'
	                     })
	            	}
	            	sweetalert(error.status) 
                }
            });
        }
        // <---------------------- insert ---------------------->
        else {
            $.ajax({
                type: 'POST',
                url: URL + 'api/price-garbage/',
                contentType: 'application/json',
                dataType: 'json',
                cache: false,
                data: JSON.stringify(garbage),
                success: function (result) {
                    result.date = formatDate(result.date); // format date
                    $('#table-garbage').DataTable().row.add(result).draw().node(); // add new data to table
                    cleanForm(); // clean form
                    sweetalert(200, 'Success!', 'Đã tạo phí rác') // message
                },
                error: function (error) {
                	if(error.status === 409){
	            		 Swal.fire({
	                         title : 'Error',
	                         text: 'Giá trong tháng đã tồn tại!!!',
	                         icon:'error'
	                     })
	            	}
	            	sweetalert(error.status) 
                }
            })
        }
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
// < ---------------------------- get value form ----------------------------------->
let getValueForm = () => {
    return {
        'id': document.querySelector('#id').value,
        'price': document.querySelector('#price').value,
        'date': document.querySelector('#date').value,
        'employee': {
            'id': 1
        },
        'note': document.querySelector('#note').value
    }
}
// < -------------------------------- fill data to form ------------------------------->
let fillToForm = (garbage) => {
    document.querySelector('#id').value = garbage.id;
    document.querySelector('#price').value = garbage.price;
    document.querySelector('#date').value = garbage.date;
    document.querySelector('#note').value = garbage.note;
}


let validate = (data) => {
    if (data.price === '') {
        toastrError("Giá không được để trống!");
        document.querySelector('#price').focus();
        return false;
    }
    if (data.price < 0 ){
        toastrError("Giá không được âm!");
        document.querySelector('#price').focus();
        return false
    }
    if (data.date === '') {
        toastrError("Ngày không được để trống!");
        document.querySelector('#date').focus();
        return false;
    }
    return true;
}

