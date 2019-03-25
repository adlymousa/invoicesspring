$(document).ready(function () {

    // $('#edit-invoice-btn').click(function(){
    //
    //     $.ajax({
    //         type: "GET",
    //         url: "http://localhost:8080/api/invoices/",
    //         dataType: 'json',
    //         contentType: "application/json; charset=utf-8",
    //         dataSrc: ''
    //     }).done(function(response){
    //         console.log(response);
    //     }).fail(function(response){
    //         console.log(response);
    //     });
    //     return false;
    // });

    // $('#edit-invoice-btn').click(function(){
    //
    //     $.ajax({
    //         type: "GET",
    //         url: "http://localhost:8080/api/invoices/" + $('#invoice-id-edit').val(),
    //         dataType: 'json',
    //         contentType: "application/json; charset=utf-8",
    //         dataSrc: ''
    //     }).done(function(response){
    //         console.log(response);
    //         $('#invoice-title-edit').val(response.title);
    //         $('#invoice-date-edit').val(response.date);
    //         $('#invoice-amount-edit').val(response.totalAmount);
    //         $('#invoice-currency-edit').val(response.currency);
    //         $('#invoice-description-edit').val(response.description);
    //         $('#invoice-attachment').val("hi");
    //     }).fail(function(jqXHR, textStatus, errorThrown) {
    //         alert('An error occurred... Look at the console (F12 or Ctrl+Shift+I, Console tab) for more information!');
    //         console.log('jqXHR:');
    //         console.log(jqXHR);
    //         console.log('textStatus:');
    //         console.log(textStatus);
    //         console.log('errorThrown:');
    //         console.log(errorThrown);
    //     });
    //     return false;
    // });




    $('#invoices-dt tbody').on( 'click', '#edit-invoice-btn', function () {
        rowData = table.row( $(this).parents('tr') ).data();
        $('#invoice-id-edit').val(rowData.id)
        $('#invoice-title-edit').val(rowData.title);
        $('#invoice-date-edit').val(rowData.date);
        $('#invoice-amount-edit').val(rowData.totalAmount);
        $('#invoice-currency-edit').val(rowData.currency.name);
        $('#invoice-description-edit').val(rowData.description);
        //$('#invoice-attachment').val("hi");
    } );

    $('#update-invoice-btn').click(function(){

        var formData = new FormData();

        var data = {};
        data.title = $('#invoice-title-edit').val();
        data.date = $('#invoice-date-edit').val();
        data.totalAmount = $('#invoice-amount-edit').val();
        data.currency = $('#invoice-currency-edit').val();
        data.description = $('#invoice-description-edit').val();

        formData.append('title', data.title);
        if(data.date != '') formData.append('date', data.date);
        if(data.totalAmount != '') formData.append('totalAmount', data.totalAmount);
        if(data.currency != '') formData.append('currency', data.currency);
        if(data.description != '') formData.append('description', data.description);
        formData.append('file', $('#invoice-attachment-edit').prop('files')[0]);

        console.log(data);

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/invoices/" + $('#invoice-id-edit').val(),
            data: formData,
            //dataType: false,
            contentType: false,
            processData: false,
            cache: false,
            beforeSend: function(request){
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                request.setRequestHeader(header, token);
            },
            success: function (response) {
                console.log(response);
            },
            error: function (response) {
                console.log(response);
            }
        }).done(function(response){
            // If you want to simulate someone clicking on a link, use  location.href
            //
            // If you want to simulate an HTTP redirect, use location.replace
            window.location.href = "http://localhost:8080/index.html";
        }).fail(function(response){
            console.log(rowData.id);
        });
        return false;
    });

});