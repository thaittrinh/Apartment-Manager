

/*  --------------------------------- upload image ---------------------------*/

$("#send-email").on("submit", function (e) {
    e.preventDefault();  
	 if(ValidateFomrSendEmail()){
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
					let timerInterval
					Swal.fire({
						allowOutsideClick: false,
						html: 'Đang gửi...',
						//timer: 5000,
						onBeforeOpen: () => {
							Swal.showLoading()
							timerInterval = setInterval(() => {
								const content = Swal.getContent()
								if (content) {
									const b = content.querySelector('b')
									if (b) {
										b.textContent = Swal.getTimerLeft()
									}
								}
							}, 100)
						},
						onClose: () => {
							clearInterval(timerInterval)
						}
					}).then((result) => {
						/* Read more about handling dismissals below */
						if (result.dismiss === Swal.DismissReason.timer) {
							console.log('I was closed by the timer')
						}
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

$("#file-mail").on("change", function (e) {
    let files = e.currentTarget.files; // puts all files into an array
    // call them as such; files[0].size will get you the file size of the 0th file
    let size = 0;
    for (let x in files) {
    	size += files[x].size;     
    	if(size > 10000000){
    		Swal.fire({
                title: 'File quá lớn!',
                text : 'Kích thước file phải nhỏ hơn 10M',
                icon: 'warning'
            });
            document.getElementById("file-mail").value='';
    		break;
    	}
    }
   
  

});







let ValidateFomrSendEmail= () =>{
	let mail = getValueFormSendEmail();
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

let getValueFormSendEmail = () => {
	return {
		"to": document.getElementById('to').value.trim(),
		"subject": document.getElementById('subject').value.trim(),
		"body": document.getElementById('body').value.trim(),
	}
}

