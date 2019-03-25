$(document).ready(function() {
    $('#audit-invoices-dt').DataTable({
        "processing": true,
        "ajax": {
            "url": "http://localhost:8080/api/audit/invoices",
            type: "GET",
            contentType: "application/json",
            dataType: 'json',
            dataSrc: ''
        },
        "columns": [{
            "data": "id"
        }, {
            "data": "title"
        }, {
            "data": "invoiceId"
        }, {
            "data": "userId"
        }, {
            "data": "date"
        }, {
            "data": "updated"
        }, {
            "data": "totalAmount"
        }, {
            "data": "currencyId"
        }, {
            "data": "description"
        }, {
            "data": "attachmentId"
        }, {
            "data": "action"
        }]
    });
});