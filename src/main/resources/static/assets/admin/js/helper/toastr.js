
let toastrError = (message) => {
	toastr.error(message,"", {
        progressBar: true,
        positionClass: "toast-top-right",
        preventDuplicates: true,
        showMethod: "show",
        hideMethod: "hide",
    })
}
let toastrWarning= (message) => {
        toastr.warning(message, "", {
                progressBar: true,
                positionClass: "toast-top-right",
                preventDuplicates: true,
                showMethod: "show",
                hideMethod: "hide",
        })
}

