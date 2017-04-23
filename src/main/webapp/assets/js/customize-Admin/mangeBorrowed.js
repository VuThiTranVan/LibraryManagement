var formatDate = 'yy/mm/dd';
var minDate = new Date('2010/01/01');
var maxDate = '-0d';

$("#txtIntenDateBor").datepicker({
	dateFormat : formatDate,
	minDate : minDate,
	maxDate : maxDate,
	onSelect: function(dateText) {
		if ($("#txtIntenDatePay").val() != "" ) {
			if (new Date(dateText) > new Date($("#txtIntenDatePay").val())) {
				$("#btn_seach").prop('disabled', true);
				$("#btn_download").prop('disabled', true);
			} else {
				$("#btn_seach").prop('disabled', false);
				$("#btn_download").prop('disabled', false);
			}
		}
	}
});

$("#txtIntenDatePay").datepicker({
	dateFormat : formatDate,
	minDate : minDate,
	onSelect: function(dateText) {
		if ($("#txtIntenDateBor").val() != "" ) {
			if (new Date(dateText) < new Date($("#txtIntenDateBor").val())) {
				$("#btn_seach").prop('disabled', true);
				$("#btn_download").prop('disabled', true);
			} else {
				$("#btn_seach").prop('disabled', false);
				$("#btn_download").prop('disabled', false);
			}
		}
	}
});

$("#txtDateBor").datepicker({
	dateFormat : formatDate,
	minDate : minDate,
	maxDate : maxDate,
	onSelect: function(dateText) {
		if ($("#txtDatePay").val() != "" ) {
			if (new Date(dateText) > new Date($("#txtDatePay").val())) {
				$("#btn_seach").prop('disabled', true);
				$("#btn_download").prop('disabled', true);
			} else {
				$("#btn_seach").prop('disabled', false);
				$("#btn_download").prop('disabled', false);
			}
		}
	}
});

$("#txtDatePay").datepicker({
	dateFormat : formatDate,
	minDate : minDate,
	maxDate : maxDate,
	onSelect: function(dateText) {
		if ($("#txtDateBor").val() != "" ) {
			if (new Date(dateText) < new Date($("#txtDateBor").val())) {
				$("#btn_seach").prop('disabled', true);
				$("#btn_download").prop('disabled', true);
			} else {
				$("#btn_seach").prop('disabled', false);
				$("#btn_download").prop('disabled', false);
			}
		}
	}
});

// click button search
$("#btn_seach").click(function(e) {

    $('#messageContainer').html('');
    e.preventDefault();
    var postData = $(this).closest('form').serializeArray();

    $.ajax({
        url : "/SpringSecurity/managementBorrowed/search",
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
                            "mDataProp" : "borrowedCode",
                            "mRender" : function(data, type, row) {
                            	var a = "aasa";
                                return "<a target='_blank' href='/SpringSecurity/managementBorrowed/detail/" + row.borrowedId + "'>"
                                        + data+"</a>";
                            },
                        }, { "mDataProp" : "userInfo.userName"
                        }, { "mDataProp" : "userInfo.email"
                        }, { "mDataProp" : "dIntendBorrowed"
                        }, { "mDataProp" : "dIntendArrived"
                        }, { "mDataProp" : "dateBorrrowed"
                        }, { "mDataProp" : "dateArrived"
                        }, { "mDataProp" : "status"
                        
                        }],
                    responsive : true
                });
                
                $('#resultSearch').removeClass('hidden_elem');
            	$('#resultSearch').addClass('clearfix body manageUser');
            } else {
            	$('#resultSearch').addClass('hidden_elem');
                $('#messageContainer').html('<p>No search results found. Please input condition other</p>');
            }
        },
        error : function(xhr, status, error) {
        	 $('#resultSearch').addClass('hidden_elem');
            $('#messageContainer').html('<p>No search results found. Please input condition other</p>');
        }
    });
});

function submitClear() {
    $("#txtBorrowedCode").val("");
    $("#txtStatus").val("0");
    $("#txtIntenDateBor").val("");
    $("#txtIntenDatePay").val("");
    $("#txtDateBor").val("");
    $("#txtDatePay").val("");
    $("#btn_seach").prop('disabled', false);
	$("#btn_download").prop('disabled', false);
}

// clear data of textbox date
$("#txtIntenDateBor").change(function() {
	if ($("#txtIntenDateBor").val() == '') {
		$("#btn_seach").prop('disabled', false);
		$("#btn_download").prop('disabled', false);
	}
});
$("#txtIntenDatePay").change(function() {
	if ($("#txtIntenDatePay").val() == '') {
		$("#btn_seach").prop('disabled', false);
		$("#btn_download").prop('disabled', false);
	}
});
$("#txtDateBor").change(function() {
	if ($("#txtDateBor").val() == '') {
		$("#btn_seach").prop('disabled', false);
		$("#btn_download").prop('disabled', false);
	}
});
$("#txtDatePay").change(function() {
	if ($("#txtDatePay").val() == '') {
		$("#btn_seach").prop('disabled', false);
		$("#btn_download").prop('disabled', false);
	}
});