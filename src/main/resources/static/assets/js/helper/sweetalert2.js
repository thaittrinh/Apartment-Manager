let sweetalert = (statusCode, title, text) => {
    switch (statusCode) {
        case 200:
            Swal.fire({
                title: title,
                text : text,
                icon: 'success'
            })
            break;
        case 400:
            Swal.fire({
                title :'Error',
                text: ' Dữ liệu nhập vào không đúng, vui lòng kiểm tra lại',
                icon: 'error'
            })
            break;
        case 404:
            Swal.fire({
                title : 'Error',
                text :  'Không tìm thấy tài nguyên',
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
        case 500:
            Swal.fire({
                title: 'Error',
                text: 'Lỗi server, vui lòng thử lại sau',
                icon: 'error'
            })
            break;
        default:

    }
}