$(document).ready(function () {
    // < ----------------------- load data to table  ------------------------------->
    $('#table-resident').DataTable({
        "responsive": true,
        "scroller": {loadingIndicator: true},
        "autoWidth": true,
        "processing": true,
        "scrollY": "270px",
        "scrollX": "100px",
        "scrollCollapse": true,
        "sAjaxSource": URL + 'api/resident',
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "fullname"},
            {"mRender": function (gender) {
                    return gender ? 'Male' : 'Female';}
            },
            {"mData": "birthday"},
            {"mData": "job"},
            {"mData": "phone"},
            {"mData": "apartment.id"},
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-update" onclick='showFormUpdate(${full.id},this)' type="button">edit</i>`
                }
            },
            {
                "mRender": function (data, type, full) {
                    return `<i  class="material-icons icon-table icon-delete " onclick='deletePrice(${full.id},this)' type="button">delete</i>`
                }
            }
        ]
    });

});
