
$(document).ready( function () {
    $('#table-gianuoc').DataTable({
    	"deferLoading": 12,
    	"responsive": true,
    	"scroller": {loadingIndicator: true},
    	"autoWidth": false,
      	"processing": true, 
    	"autoWidth": false, 
    	"scrollY": "250px",
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
		  title: 'Bạn chắc chắn muốn xóa?',
		  text: "Các hóa đơn có thể bị ảnh hưởng!",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Agree'
	}).then((result) => {	
		if (result.value) {
			$.ajax({
		         type: 'DELETE',
		         url: URL + `api/price-water/${id}`,
		         contentType: "application/json",
		        // dataType : 'json'
		         cache: false,
		         success: function (result) {    	 
		        	 $('#table-gianuoc').DataTable().row( $(e).parents('tr') )
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


let showFormUpdate = (id, e) =>{
	document.querySelector('#show-form').click();
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
	        	 console.log(result)         
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
	        	 console.log(result)         
	         },
	         error: function (error) {
	        	notification(error.status);
	         }
	     });
	 }
	 
});


// clean form
document.querySelector('#clean-form').addEventListener('click',() => {
	fillToForm({
		"id": "",
		"price": "",
		"date": "",
		"note": ""
	});
})


let getValueForm = () => {
	return {
		"id" : document.querySelector('#id').value,
	    "price" : document.querySelector('#price').value,
	    "date" : document.querySelector('#date').value,
	    "note" : document.querySelector('#note').value
	}
}


let fillToForm = (water) => {
	document.querySelector('#id').value = water.id;
	document.querySelector('#price').value = water.price;
	document.querySelector('#date').value = water.date;
	document.querySelector('#note').value = water.note;
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
