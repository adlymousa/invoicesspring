var searchParams = "?";

$(document).ready(function () {

    $('#searchButton').click(function () {

        if($('#search-title').val() != '') searchParams += "&title=" + $('#search-title').val();
        if($('#search-user').val() != '') searchParams += "&user=" + $('#search-user').val();
        if($('#search-amount').val() != '') searchParams += "&amount=" + $('#search-amount').val();
        if($('#search-currency').val() != '') searchParams += "&currency=" + $('#search-currency').val();
        if($('#search-date').val() != '') searchParams += "&date=" + $('#search-date').val();
        if($('#search-created').val() != '') searchParams += "&created=" + $('#search-created').val();
        if($('#search-description').val() != '') searchParams += "&description=" + $('#search-description').val();
        if($('#search-attachment-name').val() != '') searchParams += "&attachment=" + $('#search-attachment-name').val();

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/invoices" + searchParams,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            dataSrc: 'content'
        }).done(function(response){
            //var json = $.parseJSON(response);
            console.log(response);
            table.clear().rows.add(response.content).draw();

        }).fail(function(response) {
            console.log(response);
        });
        searchParams = "?";
        return false;

    });

});