let ID = document.getElementById("id").value;
let update = null;
(function(){
	 $.ajax({
	        url: URL + `api/apartment-index/${ID}`,
	        type: 'GET',
	        dataType: 'json',
	        success: function (result) {
	        	document.getElementById("content-detail").innerHTML = viewDetail(result.data);	
	        	document.getElementById("title-detail").innerHTML =  `<i class="fas fa-address-card mr-3"></i> Thông tin chi tiết hóa đơn căn hộ
	        															${result.data.apartmentIndex.apartment.id}`;   		        	
	        	document.getElementById("date-detail").innerHTML = getMonthYear(result.data.apartmentIndex.date);
	        	document.getElementById("content-detail").innerHTML = viewDetail(result.data);	
	        	update = result.data.apartmentIndex;
	        	hiddenPayment(result.data.paid); 
	        },
	        error: function (error) {
	            sweetalert(error.status)
	        }
	    });
})()


document.querySelector('#paid-false').addEventListener('click', () => {
	 $.ajax({
	        url: URL + `api/apartment-index/payment/${ID}?paid=false&&id_nv=${ID_NV}`,
	        type: 'PUT',
	        dataType: 'json',
	        success: function (result) {   
	        	sweetalertSuccess(result.message);
	        	hiddenPayment(false);	        	
	        },
	        error: function (error) {
	            sweetalert(error.status)
	        }
	    });
	
})


document.querySelector('#paid-true').addEventListener('click', () => {
	 $.ajax({
	        url: URL + `api/apartment-index/payment/${ID}?paid=true&&id_nv=${ID_NV}`,
	        type: 'PUT',
	        dataType: 'json',
	        success: function (result) {   
	        	sweetalertSuccess(result.message);
	        	hiddenPayment(true);	        	
	        },
	        error: function (error) {
	            sweetalert(error.status)
	        }
	    });
	
})

//< ------------------------ update  ---------------------->
document.querySelector('#save').addEventListener('click', () => {	
    
	let input = getValueForm();  
    if(validate(input)){
	        $.ajax({
	            type: 'PUT',
	            url: URL + `api/apartment-index/${ID}`,
	            contentType: "application/json",
	            dataType: 'json',
	            cache: false,
	            data: JSON.stringify(input),
	            success: function (result) {
	            	document.getElementById("content-detail").innerHTML = viewDetail(result.data);	
	            	document.getElementById("date-detail").innerHTML = getMonthYear(result.data.apartmentIndex.date);
	            	update = result.data.apartmentIndex;
		        	setValueForm(result.data.apartmentIndex)
		        	hiddenPayment(result.data.paid);
	            	sweetalertSuccess(result.message);
	            	  $('#form-building').modal('hide');
	            },
	            error: function (error) {
	            	 sweetalertError(error);
	            }
	        });
    }
       
});

//<------------- When modal close -> clean form modal  ----------->
$("#form-building").on("hidden.bs.modal", function () {
    cleanForm();
});

document.querySelector('#update').addEventListener('click', () => {
	setValueForm(update);
	
});


//<------------------ clean form ---------------------------->
let cleanForm = () => {
	document.querySelector('#electricityNumber').value = '';
    document.querySelector('#waterNumber').value = '';
    document.querySelector('#bicycleNumber').value = '';	
    document.querySelector('#motocycleNumber').value = '';
	document.querySelector('#carNumber').value = '';
}

//< -------------- clean form when click button clean ------------>
document.querySelector('#clean-form').addEventListener('click', cleanForm);

let getMonthYear = (data) => {
	var date = new Date(data);
	var month = date.getMonth() + 1; 
	var year = date.getFullYear();
	 
	return  month + "/" + year;
}


let hiddenPayment = (chk) => {
		document.getElementById("paid-false").hidden = 	!chk;
		document.getElementById("paid-true").hidden = chk;
		document.getElementById("update").hidden = chk;
}

let getValueForm = () => {
	return {
		"electricityNumber": document.querySelector('#electricityNumber').value,
	    "warterNumber": document.querySelector('#waterNumber').value,
		"date" : document.querySelector('#date').value,
	    "bicycleNumber" : document.querySelector('#bicycleNumber').value,	
	    "motocycleNumber": document.querySelector('#motocycleNumber').value,
		"carNumber": document.querySelector('#carNumber').value,	
		"employee": {
	        "id" : ID_NV
	    } 
	}

}

let setValueForm = (data) => {
		document.querySelector('#electricityNumber').value = data.newElectricityNumber;
	    document.querySelector('#waterNumber').value = data.newWaterNumber;
		document.querySelector('#date').value =  formatDate(data.date);
	    document.querySelector('#bicycleNumber').value = data.bicycleNumber;	
	    document.querySelector('#motocycleNumber').value = data.motocycleNumber;
		document.querySelector('#carNumber').value = data.carNumber;
}

let validate = (data) => {
	console.log()
	    if (data.electricityNumber === '') {
	        toastrError("Số điện không được để trống");
	        document.querySelector('#electricityNumber').focus();
	        return false
	    }
	    if (data.electricityNumber < 0) {
	        toastrError("Số điện không được âm");
	        document.querySelector('#electricityNumber').focus();
	        return false
	    }   
	    if (data.date === '') {
	        toastrError("Ngày tạo không được để trống");
	        document.querySelector('#date').focus();
	        return false
	    }
	    if (data.warterNumber === '') {
	        toastrError("Số nước không được để trống");
	        document.querySelector('#waterNumber').focus();
	        return false
	    }
	    if (data.warterNumber < 0) {
	        toastrError("Số nước không âm");
	        document.querySelector('#waterNumber').focus();
	        return false
	    }
	    if( data.bicycleNumber < 0 ){
			toastrError("Số lượng xe không được âm!");
			document.querySelector('#waterNumber').focus();
			return false;
		}
	    if( data.motocycleNumber < 0 ){
			toastrError("Số lượng xe không được âm!");
			document.querySelector('#motocycleNumber').focus();
			return false;
		}
	    if( data.carNumber < 0 ){
			toastrError("Số lượng xe không được âm!");
			document.querySelector('#carNumber').focus();
			return false;
		}
	 
	    return true;
	
	
}



