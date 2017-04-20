function submitClear() {
	    $("#txtName").val("");
	    $('#txtPermission').val("0");
}

$("#searchForm").submit(function(e) {

    $('#messageContainer').html('');

    var postData = $(this).serializeArray();
    var formURL = $(this).attr("action");

    $.ajax({
            url : formURL,
            type : "POST",
            data : postData,
            async : false,
            success : function(data, textStatus, jqXHR) {
                if ($.fn.DataTable
                        .isDataTable('#dataTables-result')) {
                    $('#dataTables-result').DataTable()
                            .destroy();
                }

                if (data != "") {
                    $('#resultSearch').removeClass(
                            'hidden_elem');
                    $('#resultSearch').addClass(
                            'clearfix body manageUser');

                    $('#dataTables-result').DataTable({
                        "bProcessing" : true,
                        "aaData" : data,
                        "createdRow" : function(row, data, dataIndex) {
                            $(row).addClass('gradeX');
                        },
                        "aoColumns" : [
                            {
                                "mDataProp" : "userName",
                                "mRender" : function(data, type, row) {
                                    return "<a target='_blank' href='/managementUser/detail/" + row.userId + "'>"
                                            + data
                                            + '</a>';
                                },
                            }, { "mDataProp" : "name"
                            }, { "mDataProp" : "birthDate"
                            }, { "mDataProp" : "address"
                            }, {"mDataProp" : "email"
                            }, { "mDataProp" : "sex"
                            }, { "mDataProp" : "phone"
                            }, { "mDataProp" : "permissionsName"
                            } ],
                        responsive : true
                    });
                } else {
                    $('#resultSearch').addClass( 'hidden_elem');
                    $('#messageContainer').html('<p>No search results found. Please input condition other</p>');
                }
            },
            error : function(xhr, status, error) {
                $('#resultSearch').addClass(
                        'hidden_elem');
                $('#messageContainer').html('<p>No search results found. Please input condition other</p>');
            }
        });
    e.preventDefault(); // STOP default action
});