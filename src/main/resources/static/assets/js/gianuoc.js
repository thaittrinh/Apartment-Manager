
$(document).ready( function () {
    $('#my-table').DataTable({
    	"deferLoading": 12,
    	"responsive": true,
    	"scroller": {loadingIndicator: true},
    	"autoWidth": false,
      	"processing": true, 
    	"autoWidth": false, 
    	"scrollY": "300px",
        "scrollCollapse": true,
    	"sAjaxSource": URL + 'api/price-water',
		"sAjaxDataProp": "",
		"order": [[ 0, "asc" ]],
		"aoColumns": [
		    { "mData": "id"},
	        { "mData": "date" },
			{ "mData": "price" },
			{ "mData": "note" },
			{"mRender": function(data, type, full) {		   
				return `<i  class="material-icons icon-table icon-update" onclick='showFormUpdate(${full.id},this)' type="button">edit</i>`
			  }
			},
			{"mRender": function(data, type, full) {		  
				return `<i  class="material-icons icon-table icon-delete " onclick='deletePrice(${full.id},this)' type="button">delete</i>`
			 }
			}
		]
    } );
});


// Delete
let deletePrice = (id, e) => {		
	Swal.fire({
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
		         type: 'DELETE',
		         url: URL + `api/price-water/${id}`,
		         contentType: "application/json",
		         cache: false,
		         success: function (result) {    	 
		        	 $('#my-table').DataTable().row( $(e).parents('tr') )
		             .remove()
		             .draw();        
		         },
		         error: function (error) { 	 
		        	notification(error.status);
		         }
		     });
		}
	})		
}

var index = -1;

let showFormUpdate = (id, e) =>{
	index =  $('#my-table').DataTable().row( $(e).parents('tr')).index();		
	$('#form-building').modal('show')
	document.querySelector('.modal-title').innerHTML = "Cập nhập giá nước";
	 $.ajax({
		  url : URL + `api/price-water/${id}`,
		  type : 'GET',
		  dataType : 'json', 
	      success : function(result) {    	
	    	  fillToForm(result)  
		   },
	      error : function(error) {
	    	  notification(error.status);
		   } 
	});
	
}

// insert or update
document.querySelector('#save').addEventListener('click', () => {
	 let water = getValueForm();	  
	 if(water.id){    
	     $.ajax({
	         type: 'PUT',
	         url: URL + `api/price-water/${water.id}`,
	         contentType: "application/json",
	         dataType : 'json', 
	         cache: false,
	         data: JSON.stringify(water),
	         success: function (result) {  	  
        	 // Convert date to yy-MM-dd
        	 result.date = formatDate(result.date);
	        //update the row in dataTable
	        $('#my-table').DataTable().row(index).data( result ).draw();
	         // close modal
	         $('#form-building').modal('hide');        
	         },
	         error: function (error) {
	        	notification(error.status);
	         }
	     });
	     	
	 }else{
		 $.ajax({
	         type: 'POST',
	         url: URL + `api/price-water`,
	         contentType: "application/json",
	         dataType : 'json', 
	         cache: false,
	         data: JSON.stringify(water),
	         success: function (result) {
	        	 // Convert date to yy-MM-dd
	        	 result.date = formatDate(result.date);
	        	 // Add new data to DataTable
	        	 $('#my-table').DataTable()
	        	    .row.add( result )
	        	    .draw()
	        	    .node(); 
	        	 // Clean form
	        	 cleanForm();
	        	 // announce success
	        	 notification(200);
	         },
	         error: function (error) {
	        	notification(error.status);
	         }
	     });
	 }
	 
});

// When modal close -> clean form modal
$("#form-building").on("hidden.bs.modal", function () {
	cleanForm();
});


let cleanForm = () => {
	fillToForm({
		"id": "",
		"price": "",
		"date": "",
		"note": ""
	});
}

// clean form when click button clean
document.querySelector('#clean-form').addEventListener('click',cleanForm);


let getValueForm = () => {
	return {
		"id" : document.querySelector('#id').value,
	    "price" : document.querySelector('#price').value,
	    "date" : document.querySelector('#date').value,
	    "employee" : {
	    	"id": 1   // set mặc định là nv id = 1  sau lm phần đăng nhập rồi get id sau
	    },
	    "note" : document.querySelector('#note').value
	}
}


let fillToForm = (water) => {
	document.querySelector('#id').value = water.id;
	document.querySelector('#price').value = water.price;
	document.querySelector('#date').value = water.date;
	document.querySelector('#note').value = water.note;
}


// những hàm dưới này sẽ viết ra file js dùng chung cho các file sau này

let formatDate = (date) => {
	 var d = new Date(date),
     month = '' + (d.getMonth() + 1),
     day = '' + d.getDate(),
     year = d.getFullYear();

	 if (month.length < 2) 
	     month = '0' + month;
	 if (day.length < 2)  
	     day = '0' + day;
	
	 return [year, month, day].join('-');	
} 


let notification = (statusCode)=> {
	switch(statusCode) {
	 case 200:
		 Swal.fire(
		      'Success!',
		      '',
		      'success')		
     break;
    case 400:
    	Swal.fire(
			'Thất bại',
		  	'Dữ liệu đầu vào bị sai, hãy kiểm tra lại!',
		  	'error'
		)
      break;
    case 404:
    	Swal.fire(
			'Không tìm thấy tài nguyên',
		  	'',
		  	'question'
    	)
      break;
    case 403:
    	Swal.fire(
			'Truy cập bị hạn chế',
		  	'Vui lòng đăng nhập đúng tài khoản!',
		  	'warning'
    	)    
     break;
    case 409:
    	Swal.fire(
			'Thất bại',
		  	'Dữ liệu bị trùng lặp!',
		  	'error'
    	)
      break;
    case 500:
    	Swal.fire(
		    'Thất bại',
		    'Lỗi server!',
		    'error'
    	)
      break; 
    default:
    	
  }
}
