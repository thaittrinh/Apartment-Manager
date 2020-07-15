var ID_NV , USERNAME;
$( document ).ready(function() {
	let username = document.getElementById("username_sidenavbar").innerText;	
	USERNAME = username; 
	document.getElementById("username-header").innerText = USERNAME;
	 $.ajax({
         type: 'GET',
         url: URL + `api/account/${username}`,    
         dataType: 'json',
         cache: false,   
         success: function (result) {   	
        	 ID_NV = result.data.id;
         },
         error: function (error) {
        	 console.log("File authentication fail load id_nv");
        	 sweetalertError(error);	
         }
     });

});