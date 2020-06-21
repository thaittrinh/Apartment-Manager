let sweetalertError = (error) => {
	
	let statusCode = error.status;
	let message = error.responseJSON.message;
	
    switch (statusCode) { 
        case 400:
            Swal.fire({
                title :'Error',
                text: message ,
                icon: 'error'
            })
            break;
        case 404:
            Swal.fire({
                title : 'Error',
                text :  message,
                icon: 'error'
            })
            break;
        case 403:
            Swal.fire({
                title: 'Warning',
                text : 'Truy cập bị hạn chế',
                icon: 'warning'
            })
            break;           
        case 409:
            Swal.fire({
                title: 'Warning',
                text :message,
                icon: 'warning'
            })
            break;    
        case 500:
            Swal.fire({
                title: 'Error',
                text: message,
                icon: 'error'
            })
            break;
        default:

    }
}


let sweetalertSuccess = (message) => {
	
            Swal.fire({
                title: 'Success',
                text : message,
                icon: 'success'
            })
           
}

