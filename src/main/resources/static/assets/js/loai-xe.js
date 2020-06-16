
let fillTypeVehicel = () => {
	$.ajax({
        type: 'GET',
        url: URL + `api/type-vehicel`,
        contentType: "application/json",
        dataType: 'json',
        cache: false,
        success: function (result) {
        	let html = "";
        	for (let i = 0; i < result.length; i++) {      		
        	html +=   `<hr>		
				        <strong style="text-transform: uppercase;">${result[i].name}</strong>
				        <a style="float: right; margin:0px 10px; color:red;" href="#" onclick="deleteType(${result[i].id})">Delete</a>
				        <a style="float: right;" onclick="formUpdateType(${result[i].id})"  href="#">Update</a>       
				        <br>
				        ${result[i].note}`
        	}
        	
        	document.querySelector('.body-modalType').innerHTML = html;
        },
        error: function (error) {
            sweetalert(error.status)
        }
    });
	
}


fillTypeVehicel();

//<----------------------------------- Delete  --------------------------------------->
let deleteType = (id) => {
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
                url: URL + `api/type-vehicel/${id}`,
                contentType: "application/json",
                cache: false,
                success: function (result) {
                	//reload type
                	fillTypeVehicel()
	            	// message
	                sweetalert(200, 'Success!', 'Xóa thành công')
                },
                error: function (error) {
                   sweetalert(error.status) //message
                }
            });
        }
    })
    
}

//<----------------------------- Update  --------------------------->
let formUpdateType = (id) => {
	 $('#form-type-vehicel').modal('show')
	 document.querySelector('.modal-title').innerHTML = "Cập nhập giá";
	 $.ajax({
	     url: URL + `api/type-vehicel/${id}`,
	     type: 'GET',
	     dataType: 'json',
	     success: function (result) {
	    	 fillToFormType(result)
	     },
	     error: function (error) {
	         sweetalert(error.status)
	     }
	 });
	}

//<----------------------------- Insert or update  --------------------------->
document.querySelector('#save-type').addEventListener('click', () => {
	let type = getformType();

	if(validateType(type)){
	 if (type.id) {
	        $.ajax({
	            type: 'PUT',
	            url: URL + `api/type-vehicel/${type.id}`,
	            contentType: "application/json",
	            dataType: 'json',
	            cache: false,
	            data: JSON.stringify(type),
	            success: function (result) {
	            	// reload type
                	fillTypeVehicel();     
	                // close modal
	                $('#form-type-vehicel').modal('hide');   
	                // annount
	                sweetalert(200,'Success!' , ' Cập nhật thành công ')
	            },
	            error: function (error) {           
	            	if(error.status === 409){
	            		 Swal.fire({
	                         title : 'Error',
	                         text: 'Tên loại xe đã tồn tại!!!',
	                         icon:'error'
	                     })
	            	}
	            	sweetalert(error.status) 
	            }
	        });
	
	    } else {
	        $.ajax({
	            type: 'POST',
	            url: URL + `api/type-vehicel`,
	            contentType: "application/json",
	            dataType: 'json',
	            cache: false,
	            data: JSON.stringify(type),
	            success: function (result) {
	            	// reload type
                	fillTypeVehicel();
                	// clean form
                	cleanFormType();
	            	// annount        
	                sweetalert(200 ,'Success!' ,'Tạo mới thành công') 
	            },
	            error: function (error) {
	            	if(error.status === 409){
	            		 Swal.fire({
	                         title : 'Error',
	                         text: 'Tên loại xe đã tồn tại!!!',
	                         icon:'error'
	                     })
	            	}
	            	sweetalert(error.status) 
	            }
	        });
	    }
	}
	
});
//<------------- When close type vihecel ->reload  price parking  ----------->
$("#modal-vehicel").on("hidden.bs.modal", function () {
	 location.href = URL + 'ui/price/vehicle' ; 
});


//<------------- When modal form close -> clean form modal  ----------->
$("#form-type-vehicel").on("hidden.bs.modal", function () {
	cleanFormType();
});

// < ---------------------- Clean form ---------------------------->
let cleanFormType = () => {
	fillToFormType({
        "id": "",
        "name": "",     
        "note": ""
    });
}

// < -------------- ----------------clean form when click button clean ------------>
document.querySelector('#clean-formType').addEventListener('click', cleanFormType);


let fillToFormType = (type) => {
	document.querySelector('#id-type').value = type.id;
	document.querySelector('#name-type').value = type.name;
	document.querySelector('#note-type').value = type.note
}


//< ---------------------------------------Get value form ------------------------------------>
let getformType = () => {
	 return {
	        "id": document.querySelector('#id-type').value,
	        "name": document.querySelector('#name-type').value,   
	        "note": document.querySelector('#note-type').value
	    }
}

//< --------------------------------------- Validate form ------------------------------------>
let validateType = (data) =>  {
	if(data.name === ''){
		toastrError("Tên loại không được để trống!");
		document.querySelector('#name-type').focus();
		return false;
	}
	if(data.name.length > 20){
		toastrError("Tên loại không dài quá 20 ký tự!");
		document.querySelector('#name-type').focus();
		return false;
	}
	return true;
}
	