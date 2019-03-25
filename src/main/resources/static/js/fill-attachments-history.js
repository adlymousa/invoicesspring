$(document).ready(function() {
    $('#audit-attachments-dt').DataTable({
        "processing": true,
        "ajax": {
            "url": "http://localhost:8080/api/audit/attachments",
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
            "data": "attachmentId"
        }, {
            "data": "userId"
        }, {
            "data": "fileType"
        }, {
            "data": "attachmentPath"
        }, {
            "data": "updated"
        }, {
            "data": "action"
        }]
    });
});