function submitClear() {
	$("#name").val("");
	$("#categoryId").val("0");
}

//click button search
$("#btn_seach").click(function(e) {

	$('#messageContainer').html('');
	e.preventDefault();
	var postData = $(this).closest('form').serializeArray();

	$.ajax({
		url : "/SpringSecurity/managementBook/search",
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
                $('#dataTables-result').DataTable({
                    "bProcessing" : true,
                    "aaData" : data,
                    "createdRow" : function(row, data, dataIndex) {
                        $(row).addClass('gradeX');
                    },
                    "aoColumns" : [
                        {
                            "mDataProp" : "bookCode",
                            "mRender" : function(data, type, row) {
                                return "<a target='_blank' href='/SpringSecurity/managementBook/detail/" + row.bookId + "'>"
                                        + data+"</a>";
                            },
                        }, { "mDataProp" : "name"
                        }, { "mDataProp" : "categoriesName"
                        }, { "mDataProp" : "publishersName"
                        }, { "mDataProp" : "statusBook"
                        }, { "mDataProp" : "numberBook"
                        }, { "mDataProp" : "numberBorrowed"
                        
                        }],
                    responsive : true
                });
                
                $('#resultSearch').removeClass('hidden_elem');
            	$('#resultSearch').addClass('clearfix body manageUser');
            } else {
            	$('#resultSearch').addClass('hidden_elem');
            	 $('#messageContainer').html($("#mgsNoResult").text());
            }
        },
        error : function(xhr, status, error) {
        	 $('#resultSearch').addClass('hidden_elem');
        	 $('#messageContainer').html($("#mgsNoResult").text());
        }
    });
});