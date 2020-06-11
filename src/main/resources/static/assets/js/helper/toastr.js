
let toastrError = (message) => {
	toastr.error(message,"Error", {
        progressBar: true,
        positionClass: "toast-top-right",
        preventDuplicates: true,
        showMethod: "show",
        hideMethod: "hide",
    })
}




/*
let toastrmessage= (statuscode) => {
    switch (statuscode) {
        case 200:
            toastr.success("Thành công", "Success",{
                progressBar: true,
                positionClass: "toast-top-right",
                preventDuplicates: true,
                showMethod: "show",
                hideMethod: "hide"
            })
            break;
        case 400:
            toastr.warning("Dữ liệu nhập không đúng" ,"Warning", {
                progressBar: true,
                showMethod: "show",
                preventDuplicates: true,
                hideMethod: "show",
                positionClass: "toast-top-right"
            })
            break;
        case 404:
            toastr.error("không tìm thấy tài nguyên","Error", {
                progressBar: true,
                positionClass: "toast-top-right",
                preventDuplicates: true,
                showMethod: "show",
                hideMethod: "hide"
            })
            break;
        case 403:
            toastr.warning("Truy cập bị hạn chế ", {
                progressBar: true,
                positionClass: "toast-top-right",
                preventDuplicates: true,
                showMethod: "show",
                hideMethod: "hide"
            })
            break;
        case 409:
            toastr.error("Dữ liệu bị trùng lặp ", {
                progressBar: true,
                positionClass: "toast-top-right",
                preventDuplicates: true,
                showMethod: "show",
                hideMethod: "hide"
            })
            break;
        case 500:
            toastr.error("Lỗi Server", {
                progressBar: true,
                positionClass: "toast-top-right",
                preventDuplicates: true,
                showMethod: "show",
                hideMethod: "hide"
            })
            break;
        default:
            break;
    }
}
*/