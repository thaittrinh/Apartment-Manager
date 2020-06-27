
let toastrError = (message) => {
	toastr.error(message,"", {
        progressBar: true,
        positionClass: "toast-top-right",
        preventDuplicates: true,
        showMethod: "show",
        hideMethod: "hide",
    })
}

