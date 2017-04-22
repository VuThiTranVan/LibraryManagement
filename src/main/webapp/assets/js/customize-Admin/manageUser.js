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
                                	var a = "aasa";
                                    return "<button onclick='clickBtnDel("+row.userId+",this)'>Del</button>"
                                    + "<span></span>"
                                    +"<a target='_blank' href='/SpringSecurity/managementUsers/detail/" + row.userId + "'>"
                                            + data+"</a>";
                                },
                            }, { "mDataProp" : "name"
                            }, { "mDataProp" : "birthDate"
                            }, { "mDataProp" : "address"
                            }, {"mDataProp" : "email"
                            }, { "mDataProp" : "sex"
                            }, { "mDataProp" : "phone"
                            }, { "mDataProp" : "permissionsName"
                            }],
                        responsive : true
                    });
                } else {
                    $('#resultSearch').addClass('hidden_elem');
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
// Del user on list
function clickBtnDel(idUser, el) {
	if (confirm("Are you sure delete record?") == true) {
		$('#messageContainer').html('');
		var formURL = "/SpringSecurity/managementUsers/delete/" + idUser;
		$.ajax({
		    url : formURL,
		    type : "GET",
		    data : false,
		    success : function(data) {

			    // remove datatable
			    var a = $('#dataTables-result').DataTable();
			    a.row($(el).parents('tr')).remove().draw();

			    // Message
			    if (0 == data) {
				    $('#messageContainer').html('Delete row success.');
			    } else {
				    $('#messageContainer').html('Delete row error.');
			    }
		    },
		    error : function(error) {
			    $('#messageContainer').html('Delete row error.');
		    }
		});
	}
};

// Detail user
function clickBtnEdit() {
	$(".editForm").removeClass('hidden_elem');
	$(".lableForm").addClass('hidden_elem');
}

function clickBtnCancel() {
	$(".lableForm").removeClass('hidden_elem');
	$(".editForm").addClass('hidden_elem');
}

// update user
$("#updateForm").submit(function(e) {
	alert("agg");
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				if (data == 'success')
					$('#messageContainer').html('Update user success.');
				else
					$('#messageContainer').html('Update user error.');

			},
			error : function(jqXHR, textStatus, errorThrown) {
				$('#messageContainer').html('Update user error.');
			}
		});
	e.preventDefault(); //STOP default action
});