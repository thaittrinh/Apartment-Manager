let ID = document.getElementById("id").value;

(function(){
	 $.ajax({
	        url: URL + `api/apartment-index/${ID}`,
	        type: 'GET',
	        dataType: 'json',
	        success: function (result) {
	        	document.getElementById("content-detail").innerHTML = viewDetail(result.data);	
	        	setValueForm(result.data.apartmentIndex)
	        	hiddenPayment(result.data.paid);
	        },
	        error: function (error) {
	            sweetalert(error.status)
	        }
	    });
})()


document.querySelector('#paid-false').addEventListener('click', () => {
	 $.ajax({
	        url: URL + `api/apartment-index/payment/${ID}?paid=false`,
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
	        url: URL + `api/apartment-index/payment/${ID}?paid=true`,
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

//< ------------------------ insert or update  ---------------------->
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



let hiddenPayment = (chk) => {
		document.getElementById("paid-false").hidden = 	!chk;
		document.getElementById("paid-true").hidden = chk;
	
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
	        "id" : 1
	    } 
	}

}

let setValueForm = (data) => {
		document.querySelector('#electricityNumber').value = data.newElectricityNumber;
	    document.querySelector('#waterNumber').value = data.newWaterNumber;
		document.querySelector('#date').value = data.date; // fortmat date khi update lên lại kiểu khác
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


