

/*  --------------------------------- upload image ---------------------------*/
$("#send-email").on("submit", function (e) {
    e.preventDefault();  
    let mail = getValueForm();
    $.ajax({
        url: URL + `api/email?to=${mail.to}&&subject=${mail.subject}&&body=${mail.body}`,
        type: "POST",
      //  data: new FormData(this),
    //    enctype: 'multipart/form-data',
    //      processData: false,
    //     contentType: false,
        contentType: 'application/json',
        dataType: 'json',
        cache: false,
        success: function (result) {
        console.log(1)
        	sweetalertSuccess("Gửi email thành công")
        },
        error: function (error) {
        	if(error.status === 200){
        		sweetalertSuccess("Gửi email thành công")
        	}else{
        		sweetalertError2("Gửi mail thất bại!")
        	}
        	
        }
    });
      
})

let getValueForm = () => {
	return {
		"to": document.getElementById('to').value.trim(),
		"subject": document.getElementById('subject').value.trim(),
		"body": document.getElementById('body').value.trim(),
	}
}

