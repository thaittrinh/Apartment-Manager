
(function(){
	 $.ajax({
	        url: URL + 'api/apartment-index',
	        type: 'GET',
	        dataType: 'json',
	        success: function (result) {
	        	table_bill(result.data)
	        },
	        error: function (error) {
	            sweetalert(error.status)
	        }
	    });
})()



let table_bill = (data) => {
    // < ----------------------- load data to table  ------------------------------->
    $('#my-table').DataTable({
        fixedColumns:   {leftColumns: 1, rightColumns: 1},
        "paging": true,
        "serverSize": true,
        "lengthMenu": [[5, 25, 50, -1], [5, 25, 50, "All"]],
        "responsive": true,
        "autoWidth": true,
        "processing": true,   
        "sAjaxDataProp": "",
        "aaData": data,
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "idApartment"},
            {"mData": "date"},
            {"mData": "totalPrice"},
            {"mData": "username"},
            {"mData": function(data){
            	return data.paid == true ? 'Đã thanh toán' : 'Chưa thanh toán' ;
            }
            },
          //  {"mData": "paid"},
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-update" onclick='showFormUpdate(${full.id})' type="button">edit</i>`
                }
            }
        ]
    });
}


let showFormUpdate = (id) => {
	 location.href= URL + `ui/bill/${id}`;  
}


//< ------------------------- insert or update -------------------->
document.querySelector('#insert').addEventListener('click', () => {
	let newBill = getValueFormInsert();
	if(validateFormInsert(newBill)){
		$.ajax({
            type: 'POST',
            url: URL + `api/apartment-index`,
            contentType: 'application/json',
            dataType: 'json',
            cache: false,
            data: JSON.stringify(newBill),
            success: function (result) {
                result.data.date = formatDate(result.data.date)
                $('#my-table').DataTable().row.add(result.data).draw().node();
                sweetalertSuccess(result.message)
                cleanFormInsert();
            },
            error: function (error) {
            	sweetalertError(error)
            }
        })
		
		
	}

});


document.querySelector('#clean-form-insert').addEventListener('click', () => {
	cleanFormInsert();
});


let getValueFormInsert = () => {
	return {
		"apartment" : {
			"id": document.querySelector('#id-apartment').value.trim()
		},
		"electricityNumber": document.querySelector('#electricity-insert').value.trim(),
		"warterNumber": document.querySelector('#water-insert').value.trim(),
		"date": document.querySelector('#date-insert').value.trim(),
		"employee": {
			"id": 1
		}
	}
}

let cleanFormInsert = () => {
	 document.querySelector('#id-apartment').value = '';
	 document.querySelector('#electricity-insert').value = '';
	 document.querySelector('#water-insert').value = '';
	document.querySelector('#date-insert').value = '';
}


let validateFormInsert = (data) => {
	console.log(data.apartment.id);
    if (data.apartment.id === '') {
        toastrError("Căn hộ không được để trống!");
        document.querySelector('#id-apartment').focus();
        return false
    }
    if( data.apartment.id.length > 8 ){
        toastrError("Id căn hộ không quá 8 ký tự!");
        document.querySelector('#id-apartment').focus();
        return false;
    }
    if (data.date === '') {
        toastrError("Ngày tạo không được để trống");
        document.querySelector('#date-insert').focus();
        return false
    }
    if (data.electricityNumber === '') {
        toastrError("Số điện không được để trống");
        document.querySelector('#electricity-insert').focus();
        return false
    }
    if (data.electricityNumber < 0) {
        toastrError("Số điện không được âm");
        document.querySelector('#electricity-insert').focus();
        return false
    }
    if( isNaN(data.electricityNumber) ){
		toastrError("Số điện phải là số!");
		document.querySelector('#electricity-insert').focus();
		return false;
	}
    if (data.warterNumber === '') {
        toastrError("Số nước không được để trống");
        document.querySelector('#water-insert').focus();
        return false
    }
    if (data.warterNumber < 0) {
        toastrError("Số nước không âm");
        document.querySelector('#water-insert').focus();
        return false
    }
    if( isNaN(data.warterNumber) ){
		toastrError("Số nước phải là số!");
		document.querySelector('#water-insert').focus();
		return false;
	}
 
    return true;
}



