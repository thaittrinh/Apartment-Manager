

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
				return `<i class="fas fa-edit "></i>`
			  }
			},
			{"mRender": function(data, type, full) {		  
					return `<i  class="material-icons icon-table" type="button">delete</i>`
			 }
			}
		]
    } );
} );


//<i class="fa fa-pencil-square-o" aria-hidden="true"></i>