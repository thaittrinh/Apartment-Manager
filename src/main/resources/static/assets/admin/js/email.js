

/*  --------------------------------- upload image ---------------------------*/

$("#send-email").on("submit", function (e) {
    e.preventDefault();  
	 if(validate()){
		 $.ajax({
		        url: URL + `api/email`,
		        type: "POST",
		        data: new FormData(this),
		        enctype: 'multipart/form-data',
		        processData: false,
		        contentType: false,
		        dataType: 'json',
		        cache: false,
		        beforeSend: function() {
		        	Swal.fire({
		        		  title: 'Đang gửi . . .',
		        		  width: 600,
		        		  padding: '3em',
		        		  showConfirmButton: false,
		        		  background: '#fff url(https://sweetalert2.github.io/images/trees.png)',
		        		  backdrop: `
		        		    rgba(0,0,123,0.4)
		        		    url("https://drive.google.com/uc?id=1V2BMH7ux5noYtK3EU26ac8WB9IeNCwJb")
		        		    left top
		        		    no-repeat
		        		  `
		        		})	
		          /*  swal.fire({
		                html: '<h5>Đang gửi . . .</h5>',
		                showConfirmButton: false,
		                onRender: function() {
		                     // there will only ever be one sweet alert open.
		                    $('.swal2-content').prepend(
		                    		 '<div class="sweet_loader"><svg viewBox="0 0 140 140" width="140" height="140"><g class="outline"><path d="m 70 28 a 1 1 0 0 0 0 84 a 1 1 0 0 0 0 -84" stroke="rgba(0,0,0,0.1)" stroke-width="4" fill="none" stroke-linecap="round" stroke-linejoin="round"></path></g><g class="circle"><path d="m 70 28 a 1 1 0 0 0 0 84 a 1 1 0 0 0 0 -84" stroke="#71BBFF" stroke-width="4" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-dashoffset="200" stroke-dasharray="300"></path></g></svg></div>'	
		                    );
		                }
		            });*/
		        },
		        success: function (result) {
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
	 }   
});


let validate = () =>{
	let mail = getValueForm();
	if(mail.to === ''){
		toastrError("Chưa nhập người nhận!");
        document.querySelector('#to').focus();
        return false;
	}
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; 
	if(!filter.test(mail.to))
		{
		toastrError("Email sai dịnh dạng!");
        document.querySelector('#to').focus();
		return false;
	}
	if(mail.subject === ''){
		toastrError("Chưa nhập chủ đề!");
        document.querySelector('#subject').focus();
        return false;
	}
	if(mail.body === ''){
		toastrError("Chưa nhập nội dung!");
        document.querySelector('#body').focus();
        return false;
	}
	return true;
	
}

let getValueForm = () => {
	return {
		"to": document.getElementById('to').value.trim(),
		"subject": document.getElementById('subject').value.trim(),
		"body": document.getElementById('body').value.trim(),
	}
}

