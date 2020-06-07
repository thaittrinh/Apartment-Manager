

$(document).ready( function () {
    $('#table-gianuoc').DataTable({
    	processing: true,      
    	"sAjaxSource": URL + 'api/price-water',
		"sAjaxDataProp": "",
		"order": [[ 0, "asc" ]],
		"aoColumns": [
		    { "mData": "id"},
	        { "mData": "date" },
			{ "mData": "price" },
			{ "mData": "note" },
			{"mRender": function(data, type, full) {
			   // return '<a class="btn btn-info btn-sm" href=' + full.id + '>' + 'Delete' + '</a>';
				//<button type="button" class="btn btn-warning"></button>
				return `<button type="button" class="btn btn-warning" value=${full.id} ><i class="fas fa-trash"></i></button>`
			  }
			}
		]
    } );
} );





/*
(function(){
    $.ajax({
         type: 'get',
         url: URL + 'api/price-water',
         //contentType: "application/json",
         dataType : 'json',
         cache: false,
         //data: JSON.stringify(data),	        
         success: function (result) {
            $('#table-gianuoc').DataTable({         
            	processing: false,
            	data: result,
            	columns: [
            		{data: "id" },           		
            		{data: "date" },
            		{data: "price" },
            		{data: "note" }     		
            	]    
     	
            });
         },
         error: function (error) {
            var errorMessage = error.status;
            console.log(errorMessage);   
         }
     });
})();

*/
