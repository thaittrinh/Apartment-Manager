$(document).ready( function () {
	 // < ----------------------- load data to table  ------------------------------->
    $('#table-phiquanly').DataTable(
    		{
    	        "responsive": true,
    	        "scroller": {loadingIndicator: true},
    	        "autoWidth": false,
    	        "processing": true,
    	        "autoWidth": false,
    	        "scrollY": "300px",
    	        "scrollCollapse": true,
    	        "sAjaxSource": URL + 'api/price-management',
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
} );

var index = -1;
//< -------------------------- show form update --------------------->
let showFormUpdate = (id, e) => {
 index = $('#table-phiquanly').DataTable().row($(e).parents('tr')).index();
 $('#form-building').modal('show')
 document.querySelector('.modal-title').innerHTML = "Cập Nhật Phí Quản Lý";
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


////< ------------------- get value form --------------------------->
//let getValueForm = () => {
//    return {
//        "id": document.querySelector('#id').value,
//        "price": document.querySelector('#price').value,
//        "date": document.querySelector('#date').value,
//        "employee": {
//            "id": 1   // set mặc định là nv id = 1  sau lm phần đăng nhập rồi get id sau
//        },
//        "note": document.querySelector('#note').value
//    }
//}

let fillToForm = (management) => {
    document.querySelector('#id').value = management.id;
    document.querySelector('#price').value = management.price;
    document.querySelector('#date').value = management.date;
    document.querySelector('#note').value = management.note;
}