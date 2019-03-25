$(document).ready(function () {

    $('#add-invoice-btn').click(function(){

        //console.log("param = " + csrfParameter + " / value = " + csrfToken);
        var formData = new FormData();

        var data = {};
        data.title = $('#invoice-title').val();
        data.date = $('#invoice-date').val();
        data.totalAmount = $('#invoice-amount').val();
        data.currency = $('#invoice-currency').val();
        data.description = $('#invoice-description').val();

        formData.append('title', data.title);
        if(data.date != '') formData.append('date', data.date);
        if(data.totalAmount != '') formData.append('totalAmount', data.totalAmount);
        if(data.currency != '') formData.append('currency', data.currency);
        if(data.description != '') formData.append('description', data.description);
        formData.append('file', $('#invoice-attachment').prop('files')[0]);

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/invoices",
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            beforeSend: function(request){
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                request.setRequestHeader(header, token);
            },
            success: function (response) {
                console.log("success");
            },
            error: function (response) {
                console.log("fail");
            }
        }).done(function(response){
            // If you want to simulate someone clicking on a link, use  location.href
            //
            // If you want to simulate an HTTP redirect, use location.replace
            window.location.href = "http://localhost:8080/";
        }).fail(function(response){
            console.log("fail");
        });
        return false;
    });

});