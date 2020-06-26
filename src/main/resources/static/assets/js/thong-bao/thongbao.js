(function(){
	 $.ajax({
	        url: URL + `api/notification`,
	        type: 'GET',
	        dataType: 'json',
	        success: function (result) {
	        	table(result.data)
	        },
	        error: function (error) {
	            sweetalert(error.status)
	        }
	    });
})()


let table = (data) => {
    // < ----------------------- load data to table  ------------------------------->
    $('#table-notification').DataTable({
    	 fixedColumns:   {leftColumns: 1, rightColumns: 1},
         fixedHeader: true,
         "scrollCollapse": true,
         "responsive": true,
         "serverSize": true,
         "lengthMenu": [[5, 25, 50, -1], [5, 25, 50, "All"]],
         "scroller": true,
         "autoWidth": true,
         "processing": true,
         "scrollY": "250px",
       // "sAjaxSource": URL + 'api/notification',
        "sAjaxDataProp": "",
        "aaData": data,
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "title"},
            {"mData": "date"},
          
            {"mData": "employee.username"},         
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-update" id="mybtn" onclick='showFormUpdate(${full.id})' type="button">edit</i>`
                }
            },
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-delete " onclick='deleteNotification(${full.id},this)' type="button">delete</i>`
                }
            }
        ]
    });
}



// sweetalertSuccess(result.message)
//sweetalertError(error)




//< -------------------------- show form update --------------------->
let showFormUpdate = (id) => {
   location.href =URL + `ui/notification/form/${id}`;
   
}


//< ----------------------------- Delete ---------------------------->
let deleteNotification = (id, e) => {
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
                url: URL + `api/notification/${id}`,
                contentType: "application/json",
                cache: false,
                success: function (result) {
                    $('#table-notification').DataTable().row($(e).parents('tr'))
                        .remove().draw();
                    sweetalertSuccess(result.message);
                },
                error: function (error) {
                	sweetalertError(error) //message
                }
            });
        }
    })
}

	
  