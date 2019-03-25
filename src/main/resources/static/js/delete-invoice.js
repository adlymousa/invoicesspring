$(document).ready(function () {

    $('#invoices-dt tbody').on( 'click', '#delete-invoice-btn', function () {
        rowData = table.row( $(this).parents('tr') ).data();
        $('#invoice-id-delete').val(rowData.id)
    } );

    $('#confirm-delete-invoice-btn').click(function(){

        //console.log($('#invoice-id-delete').val());

        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api/invoices/" + $('#invoice-id-delete').val(),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            cache: false,
            beforeSend: function(request){
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        }
        }).done(function(response){
            // If you want to simulate someone clicking on a link, use  location.href
            //
            // If you want to simulate an HTTP redirect, use location.replace
            //console.log("deleted successfully");
        }).fail(function(response){
            //console.log("failed to delete");
            window.location.href = "http://localhost:8080/index.html";
        });
        return false;
    });

});