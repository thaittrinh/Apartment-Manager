

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
				return `  <i  class="material-icons icon-table" type="button">delete</i>`
			  }
			}
		]
    } );
} );


