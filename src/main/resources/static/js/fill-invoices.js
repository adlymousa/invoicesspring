// $.ajax({
//     url: 'http://localhost:8080/api/invoices',
//     type: 'GET' // this is default, but worth pointing out
// }).done(function(data){
//     // you may use "data" to access the underlying data
//     console.log(data)
// });




//
// $(document).ready(function() {
//     var t = $('#invoices-table').DataTable();
//     var counter = 1;
//
//     $('#addRow').on( 'click', function () {
//         t.row.add( [
//             counter +'.1',
//             counter +'.2',
//             counter +'.3',
//             counter +'.4',
//             counter +'.5'
//         ] ).draw( false );
//
//         counter++;
//     } );
//
//     // Automatically add a first row of data
//     $('#addRow').click();
// } );


// $( document ).ready(function() {
//     var api_url = 'http://localhost:8080/api/invoices'
//
//         $.ajax({
//             url: api_url,
//             type: "GET",
//             contentType: "application/json",
//             dataType: 'json',
//             success: function(result){
//                 // $.each(result, function(index, element) {
//                 //     console.log(element.created);
//                 // });
//                 console.log(result);
//             },
//             error: function(result) {
//                 alert(result);
//             }
//         })
// });

var table;

var rowData;

$(document).ready(function() {


    table = $('#invoices-dt').DataTable({
        "processing": true,
        "ordering": false,
        "paging": false,
        "searching": false,
        //"serverSide": true,
        //"pageLength": 10,
        //"pagingType": "full",
        //"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "ajax": {
            url: "http://localhost:8080/api/invoices",
            type: "GET",
            contentType: "application/json",
            dataType: 'json',
            dataSrc: 'content'
        },
        "columns": [{
            "data": "id"
        }, {
            "data": "title"
        }, {
            "data": "user.username"
        }, {
            "data": "date"
        }, {
            "data": "created"
        }, {
            "data": "totalAmount"
        }, {
            mRender: function (data, type, row) {

                if(row.currency == null) return null;
                return row.currency.name;
            }
        }, {
            "data": "description"
        }, {
            mRender: function (data, type, row) {

                if(row.attachment == null) return null;
                var attachmentLink = row.attachment.title + '<br><button type="submit" id = "download-invoice-btn" class="btn btn-success btn-sm" href="#" style="width: 100px">Download</button>';
                return attachmentLink;
            }
        },{// this is Actions Column
            mRender: function (data, type, row) {
                var linkEdit = '<button type="submit" id = "edit-invoice-btn" class="btn btn-secondary btn-sm" href="#" data-toggle="modal" data-target="#editInvoiceModal" style="width: 100px"> Edit </button>';

                var linkDetails = '<a>Details</a>';

                var linkDelete = '<button type="submit" id = "delete-invoice-btn" class="btn btn-secondary btn-sm" href="#" data-toggle="modal" data-target="#deleteInvoiceModal" style="width: 100px">Delete</button>';

                return linkEdit + " <br> " + linkDelete;
            }
        }]
    });


    $('#next').on( 'click', function () {
        table.page( 'next' ).draw( 'page' );
    } );

    $('#previous').on( 'click', function () {
        table.page( 'previous' ).draw( 'page' );
    } );


    $('#invoices-dt tbody').on( 'click', '#download-invoice-btn', function () {
        rowData = table.row( $(this).parents('tr') ).data();
        console.log(rowData);
        $.ajax({
            type: "GET",
            url: rowData.attachment.attachmentPath,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            cache: false,
            error: function(response) {
                window.location = rowData.attachment.attachmentPath;
            }
        });
    } );

 });