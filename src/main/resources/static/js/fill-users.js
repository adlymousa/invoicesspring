$(document).ready(function() {
    $('#users-dt').DataTable({
        "processing": true,
        "ajax": {
            "url": "http://localhost:8080/api/users?size=50&page=0", //" + $('#users-dt_length > label > select > option').attr("value") + "
            type: "GET",
            contentType: "application/json",
            dataType: 'json',
            dataSrc: 'content'
        },
        "columns": [{
            "data": "id"
        }, {
            "data": "username"
        }, {
            "data": "firstName"
        }, {
            "data": "lastName"
        }, {
            "data": "email"
        }, {
            "data": "mobileNumber"
        }, {
            "data": "role"
        }, {
            "data": "active"
        }]
    });

    // $('#users-dt_length')
    //     //     .val($('#users-dt_length').val())
    //     //     .trigger('change');
    //     //
    //     //
    //     //
    //     // $("#users-dt_length").change(function() {
    //     //     $('#users-dt').DataTable({
    //     //         "processing": true,
    //     //         "ajax": {
    //     //             "url": "http://localhost:8080/api/users?size=" + $('#users-dt_length > label > select').val() + "&page=0",
    //     //             type: "GET",
    //     //             contentType: "application/json",
    //     //             dataType: 'json',
    //     //             dataSrc: ''
    //     //         },
    //     //         "columns": [{
    //     //             "data": "content.id"
    //     //         }, {
    //     //             "data": "content.username"
    //     //         }, {
    //     //             "data": "content.firstName"
    //     //         }, {
    //     //             "data": "content.lastName"
    //     //         }, {
    //     //             "data": "content.email"
    //     //         }, {
    //     //             "data": "content.mobileNumber"
    //     //         }, {
    //     //             "data": "content.role"
    //     //         }, {
    //     //             "data": "content.active"
    //     //         }]
    //     //     });
    //     //
    //     //     //console.log(data);
    //     //
    //     //     // $.ajax({
    //     //     //     type: "GET",
    //     //     //     url: "http://localhost:8080/api/users?size=" + $('#users-dt_length > label > select').val() + "&page=0",
    //     //     //     dataType: 'json',
    //     //     //     contentType: "application/json",
    //     //     //     dataSrc: 'content',
    //     //     //     success: function (response) {
    //     //     //         console.log(response);
    //     //     //     }
    //     //     // }).done(function(response){
    //     //     //     console.log(response);
    //     //     //
    //     //     // }).fail(function(response){
    //     //     //     console.log(response);
    //     //     // });
    //     //     // return false;
    //     //
    //     //
    //     // });



});