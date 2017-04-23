function submitClear() {
	    $("#txtName").val("");
	    $('#txtPermission').val("0");
}
$("#btn_seach").click(function(e) {

    $('#messageContainer').html('');
    e.preventDefault();
    var postData = $(this).closest('form').serializeArray();

    $.ajax({
            url : "/SpringSecurity/managementUsers/search",
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
                                    + "<span class='hidden_elem' id='"+row.userId+"'>"+row.dateUpdate+"</span>"
                                    +"&nbsp;&nbsp;&nbsp;<a target='_blank' href='/SpringSecurity/managementUsers/detail/" + row.userId + "'>"
                                            + data+"</a>";
                                },
                            }, { "mDataProp" : "name"
                            }, { "mDataProp" : "birthDate"
                            }, { "mDataProp" : "address"
                            }, {"mDataProp" : "email"
                            }, { "mDataProp" : "sex"
                            }, { "mDataProp" : "phone"
                            }, { "mDataProp" : "permissions.permissionName"
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
    

});
//Detail user
$(".birthday_picker").datepicker({
	dateFormat : 'yy/mm/dd',
	minDate : new Date('1920/01/01'),
	maxDate : '-1d',
	onSelect: function(dateText) {
		if (dateText != $('#lblBirthDate').text()) {
			$("#savebtn").prop('disabled', false);
		}
	}
});

function clickBtnEdit() {
	$(".editForm").removeClass('hidden_elem');
	$(".lableForm").addClass('hidden_elem');
	$("#savebtn").prop('disabled', true);
}

function clickBtnCancel() {
	$(".lableForm").removeClass('hidden_elem');
	$(".editForm").addClass('hidden_elem');
	cancel();
}

// Del user on list
function clickBtnDel(idUser, el) {
	if (confirm("Are you sure delete record?") == true) {
		$('#messageContainer').html('');
		
		var dateUpd = $("#"+idUser).text();
		
		var formURL = "/SpringSecurity/managementUsers/delete/" + idUser;
		$.ajax({
			url : formURL,
			type : "GET",
			data : {
				dateUpd : dateUpd
			},
			dataType : 'json',
			success : function(data) {
				if (data == 1) {
					// remove datatable
					var a = $('#dataTables-result').DataTable();
					a.row($(el).parents('tr')).remove().draw();
					
					// Message
					$('#messageContainer').html('Delete row success.');
				}
				else {
					$('#messageContainer').html('Delete row error.');
				}
			},
			error : function(error) {
				$('#messageContainer').html('Delete row error.');
			}
		});
	}
};

// update user
$("#updateForm").submit(function(e) {
	if ($(this).valid()) {
		$('#messageContainer').html('');

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		$.ajax({
		    url : formURL,
		    type : "POST",
		    data : postData,
		    success : function(data, textStatus, jqXHR) {
			    
				    $('#messageContainer').html('Update user success.');
					updateInputToLbl();
				    $(".lableForm").removeClass('hidden_elem');
					$(".editForm").addClass('hidden_elem');
					$('#editbtn').hide();
					
					$("#lblDateUpdate").html(data.dateUpdate);
					$("#lblUserUpdate").html(data.userUpdate);
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
			    $('#messageContainer').html('Update user error.');
		    }
		});
		e.preventDefault(); // STOP default action
	}
});

jQuery.extend(jQuery.validator.messages, {
	required : "This is item required.",
	email: "This is invalid email.",
	number: "This is invalid number"
});

//Setup form validation on the #update-form element
var validator = $("#updateForm").validate({
	 errorElement: 'div',
	// Specify the validation rules
	rules : {
		name : "required",
		address : "required",
		birthDate : "required",
		phone: 
		{  
			required: true,
			number: true
		},
		email : {  
		required: true,
		email: true
	},
	},
	errorPlacement : function(error, element) {
		if (element.is(":radio")) {
			error.appendTo(element.parents('.form-group'));
		} else { // This is the default behavior 
			error.insertAfter(element);
		}
	}
});

$("#name").blur(function() {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

$("#birthDate").blur(function() {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

$("#address").blur(function() {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

$("#phone").blur(function() {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

$("#email").blur(function() {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

$( "select" ).change(function () {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});
$('input[type=radio][name=sex]').change(function () {
	if(checkItemChangeValue()) {
		$("#savebtn").prop('disabled', false);
	}
	else {
		$("#savebtn").prop('disabled', true);
	}
});

function checkItemChangeValue() {
	var name = $('#name').val();
	var lblName = $('#lblName').text();
	
	var permissionsName = $('#permissionsName option:selected').text();
	var lblPermissionsName = $('#lblPermissionsName').text();
	
	var birthDate = $('#birthDate').val();
	var lblBirthDate = $('#lblBirthDate').text();
	
	var address = $('#address').val();
	var lblAddress = $('#lblAddress').text();
	
	var phone = $('#phone').val();
	var lblPhone = $('#lblPhone').text();
	var sex = $('input[type=radio][name=sex]:checked').val();
	var lblSex = 1;
	if ("Fmale" == $('#lblSex').text()) {
		lblSex = 0;
	}
	
	var email = $('#email').val();
	var lblEmail = $('#lblEmail').text();
	if (name == lblName 
			&& permissionsName == lblPermissionsName
			&& birthDate == lblBirthDate
			&& address == lblAddress
			&& phone == lblPhone
			&& sex == lblSex
			&& email == lblEmail
			) {
		return false;
	}
	else{
		return true;
	}
}

// function update data lable when update success
function updateInputToLbl() {
	$('#lblName').html($('#name').val());
	$('#lblPermissionsName').html($('#permissionsName option:selected').text());
	$('#lblBirthDate').html($('#birthDate').val());
	$('#lblAddress').html($('#address').val());
	$('#lblPhone').html($('#phone').val());
	$('#lblSex').html("Male");
	if ("0" == $('input[type=radio][name=sex]:checked').val()) {
		$('#lblSex').html("Fmale");
	}
	$('#lblEmail').html($('#email').val());
}

// function revert data init
function cancel () {
	$('#name').val($('#lblName').text());
	$('#birthDate').val($('#lblBirthDate').text());	
	$('#address').val($('#lblAddress').text());
	$('#phone').val($('#lblPhone').text());
	$('#email').val($('#lblEmail').text());
	
	var lblPer = $('#lblPermissionsName').text();
	$("#permissionsName option").filter(function() {
		return this.text == lblPer;
	}).prop('selected', true);

	var sex = $("#lblSex").text();
	$('input[type=radio][name=sex]').filter('[value=1]').prop('checked', true);
	if (sex == "Fmale") {
		$('input[type=radio][name=sex]').filter('[value=0]').prop('checked',
		        true);
	}	
}

function getDateLocal () {
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var dateSys = d.getFullYear() + '/' +
	    ((''+month).length<2 ? '0' : '') + month + '/' +
	    ((''+day).length<2 ? '0' : '') + day;
	
	return dateSys;
}
$("#btn_download").click(function() {
	
	$("#btn_seach").click();
	$("#searchForm").attr("action", "/SpringSecurity/managementUsers/downloadCSV");
	$("#searchForm").submit();
	
});
	




