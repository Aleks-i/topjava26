var ctx;

function filter() {
    let form = $("#filter")
    $.ajax({
        type: "GET",
        url: ctx.ajaxUrl + "filter",
        data: form.serialize()
    }).done(updateTable);
}

function clearFilter() {
    $("#filter")[0].reset();
    ctx.updateTable();
}

$(function () {
    ctx = {
        ajaxUrl: "profile/meals/",
        datatableApi: $('#datatable').DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        }),
        updateTable: function () {
            $.get(this.ajaxUrl, filter)
        }
    };
    makeEditable();
});