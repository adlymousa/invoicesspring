$(document).ready(function() {
    $('#registerButton').click(function(){

        var data = {};
        data.username = $('#inputUsername').val();
        data.firstName = $('#inputFirstName').val();
        data.lastName = $('#inputLastName').val();
        data.email = $('#inputEmail').val();
        data.password = $('#inputPassword').val();
        //data.confirmPassword = $('#inputConfirmPassword').val()
        data.mobileNumber = $('#inputMobileNumber').val();
        data.role = $('#inputRole').val();
        data.active = "true";

        console.log(data);

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/users",
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            cache: false,
            success: function (response) {
                console.log(response);
            },
            fail: function (response) {
                console.log(response);
            }
        }).done(function(response){
            // If you want to simulate someone clicking on a link, use  location.href
            //
            // If you want to simulate an HTTP redirect, use location.replace

            window.location.href = "http://localhost:8080/index.html";
        }).fail(function(response){
            console.log(response);
        });
        return false;
    });
});