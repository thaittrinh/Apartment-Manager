$(document).ready(function () {
    // < ----------------------- load data to table  ------------------------------->
    $('#table-phiquanly').DataTable(
        {
            "responsive": true,
            "scroller": {loadingIndicator: true},
            "autoWidth": true,
            "processing": true,
            "scrollY": "300px",
            "scrollCollapse": true,
            "sAjaxSource": URL + 'api/price-management',
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

var index = -1;
//< -------------------------- show form update --------------------->
let showFormUpdate = (id, e) => {
    index = $('#table-phiquanly').DataTable().row($(e).parents('tr')).index();
    $('#form-building').modal('show')
    document.querySelector('.modal-title').innerHTML = "Cập nhật phí quản lý";
    $.ajax({
        url: URL + `api/price-management/${id}`,
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

//< ------------------------ insert or update  ---------------------->
document.querySelector('#save').addEventListener('click', () => {
    let management = getValueForm();
    if (validate(management)) {
        if (management.id) {
            $.ajax({
                type: 'PUT',
                url: URL + `api/price-management/${management.id}`,
                contentType: "application/json",
                dataType: 'json',
                cache: false,
                data: JSON.stringify(management),
                success: function (result) {
                    result.date = formatDate(result.date);  // Convert date to yy-MM-dd
                    $('#table-phiquanly').DataTable().row(index).data(result).draw();  //update the row in dataTable
                    $('#form-building').modal('hide');     // close modal
                    sweetalert(200, 'Success!', ' Đã cập nhật phí quản lý ')
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

        } else {
            $.ajax({
                type: 'POST',
                url: URL + `api/price-management`,
                contentType: "application/json",
                dataType: 'json',
                cache: false,
                data: JSON.stringify(management),
                success: function (result) {
                    // Convert date to yy-MM-dd
                    result.date = formatDate(result.date);
                    // Add new data to DataTable
                    $('#table-phiquanly').DataTable()
                        .row.add(result).draw().node();
                    // Clean form
                    cleanForm();
                    // message
                    sweetalert(200, 'Success!', 'Đã tạo phí quản lý')
                },
                error: function (error) {
                    if(error.status === 409){
	            		 Swal.fire({
	                         title : 'Error',
	                         text: 'Giá trong tháng đã tồn tại!!!',
	                         icon:'error'
	                     })
	            	}
	            	sweetalert(error.status);
                }
            });
        }
    }


});


//< ----------------------------- Delete ---------------------------->
let deletePrice = (id, e) => {
    Swal.fire({
        title: 'Warning',
        text: "Bạn có chắc chắn muốn xóa không!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                type: 'DELETE',
                url: URL + `api/price-management/${id}`,
                contentType: "application/json",
                cache: false,
                success: function (result) {
                    $('#table-phiquanly').DataTable().row($(e).parents('tr')) // format date
                        .remove().draw();
                    sweetalert(200, 'Success!', 'Xóa thành công') // message
                },
                error: function (error) {
                    sweetalert(error.status) //message
                }
            });
        }
    })
}

//< ---------------------- Clean form ---------------------------->
let cleanForm = () => {
    fillToForm({
        "id": "",
        "price": "",
        "date": "",
        "note": ""
    });
}

//<------------- When modal close -> clean form modal  ----------->
$("#form-building").on("hidden.bs.modal", function () {
    cleanForm();
});
// < -------------- clean form when click button clean ------------>
document.querySelector('#clean-form').addEventListener('click', cleanForm);


//< ------------------- get value form --------------------------->
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

let validate = (data) => {
    if (data.price === '') {
        toastrError("Giá không được để trống!");
        document.querySelector('#price').focus();
        return false;
    }
    if (data.date === '') {
        toastrError("Ngày không được để trống!");
        document.querySelector('#date').focus();
        return false;
    }
    return true;
}

let fillToForm = (management) => {
    document.querySelector('#id').value = management.id;
    document.querySelector('#price').value = management.price;
    document.querySelector('#date').value = management.date;
    document.querySelector('#note').value = management.note;
}


