let fillToForm = (data) => {
    document.querySelector('#id').value = data.id;
    document.querySelector('#name').value = data.fullName;
    document.querySelector('#birthday').value = data.birthday;
    document.querySelector('#address').value = data.address;
    document.querySelector('#identitycard').value = data.identitycard;
    document.querySelector('#phone').value = data.phone;
    document.querySelector('#username').value =  data.username;
    document.querySelector('#email').value =  data.email;
    $(data.gender ? "#female" : "#male").prop('checked', true);



}
(function(){
	let username = document.getElementById("account").innerText;
	 $.ajax({
         type: 'GET',
         url: URL + `api/account/${username}`,    
         dataType: 'json',
         cache: false,   
         success: function (result) {
        	 fillToForm(result.data);   	
        	 if(result.data.image){
        	        document.querySelector('#imgs').src = URL + `assets/quanly/image/${result.data.image}`;
        	    }else{
        	        document.querySelector('#imgs').src = URL + `assets/quanly/image/someone.png`;
        	    }
         },
         error: function (error) {
        	 
        	 sweetalertError(error);	
         }
     });

})()


/* ------------------------------- Upload  File -----------------------------*/
document.querySelector('#imgs').addEventListener('click', () => {
    document.querySelector('#file').click();
});

/* -----------------------------  read url for image -------------------*/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imgs')
                .attr('src', e.target.result)
                .width(300)
                .height(auto);
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





