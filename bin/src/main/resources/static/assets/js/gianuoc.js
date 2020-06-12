
$(document).ready( function () {
    $('#table-gianuoc').DataTable({
    	"deferLoading": 12,
    	"responsive": true,
    	"scroller": {loadingIndicator: true},
    	"autoWidth": false,
      	"processing": true, 
    	"autoWidth": false, 
    	"scrollY": "250px",
        "scrollCollapse": true,
    	"sAjaxSource": URL + 'api/price-water',
		"sAjaxDataProp": "",
		"order": [[ 0, "asc" ]],
		"aoColumns": [
		    { "mData": "id"},
	        { "mData": "date" },
			{ "mData": "price" },
			{ "mData": "note" },
			{"mRender": function(data, type, full) {		   
				return `<i  class="material-icons icon-table icon-update" type="button">edit</i>`
			  }
			},
			{"mRender": function(data, type, full) {		  
					return `<i  class="material-icons icon-table icon-delete " type="button">delete</i>`
			 }
			}
		]
    } );
} );
