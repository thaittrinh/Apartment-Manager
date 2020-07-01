(function(){
	let username = document.getElementById("username").innerText;	
	 $.ajax({
         type: 'GET',
         url: URL + `api/account/${username}`,    
         dataType: 'json',
         cache: false,   
         success: function (result) {
        	 fillToForm(result.data);   	
        	 if(result.data.image){
        	        document.querySelector('#imgs').src = URL + `assets/photo/${result.data.image}`;
        	    }else{
        	        document.querySelector('#imgs').src = URL + `assets/photo/someone.png`;
        	    }
         },
         error: function (error) {
        	 
        	 sweetalertError(error);	
         }
     });

})()


/* ------------------------------- Upload  File -----------------------------*/
document.querySelector('.img').addEventListener('click', () => {
    document.querySelector('#file').click();
});

/* -----------------------------  read url for image -------------------*/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imgs')
                .attr('src', e.target.result)
                .width(130)
                .height(130);
        };
        reader.readAsDataURL(input.files[0]);
        document.querySelector('#upload-now').click();
    }
}

/*  --------------------------------- upload image ---------------------------*/
$("#file-upload-form").on("submit", function (e) {
    e.preventDefault();
    let id = document.getElementById("id").value;
    
    $.ajax({
        url: URL + `api/account/upload-file/${id}`,
        type: "POST",
        data: new FormData(this),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (result) {
        	fillToForm(result.data);   
            sweetalertSuccess(result.message);
        },
        error: function (error) {
            sweetalertError(error);
        }
    });
})






let fillToForm = (data) => {
	document.querySelector('#id').value = data.id;
    document.querySelector('#name').value = data.fullName;
    document.querySelector('#birthday').value = data.birthday;
    document.querySelector('#address').value = data.address;
    document.querySelector('#identitycard').value = data.identitycard;
    document.querySelector('#phone').value = data.phone;
    document.querySelector('#username').value = "Username" + data.username;
    document.querySelector('#email').innerHTML = "Email: " +  data.email;
    document.querySelector('#gender').innerHTML =  "Giới tính: "  +   ( data.gender ?  "Nam" : "Nữ" ) ;
   
    

}