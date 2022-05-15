$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});



// SAVE ============================================
$(document).on("click", "#btnSave", function() {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateUserForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	$("#formUser").submit();
});



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function() {
	$("#hidUserIDSave").val($(this).closest("tr").find('#hidUserIDUpdate').val());
	$("#nic").val($(this).closest("tr").find('td:eq(0)').text());
	$("#name").val($(this).closest("tr").find('td:eq(1)').text());
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#contact").val($(this).closest("tr").find('td:eq(3)').text());
});


// Delete============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "UsersAPI",
		type : "DELETE",
		data : "userid=" + $(this).data("userid"),
		dataType : "text",
		complete : function(response, status) {
			onUserDeleteComplete(response.responseText, status);
		}
	});
});



// CLIENT-MODEL================================================================
function validateUserForm() {
	// CODE
	if ($("#nic").val().trim() == "") {
		return "Insert User nic.";
	}
	// NAME
	if ($("#name").val().trim() == "") {
		return "Insert User Name.";
	}
	
	if ($("#address").val().trim() == "") {
		return "Insert User address.";
	}
	
	
	if ($("#contact").val().trim() == "") {
		return "Insert User contact.";
	}
	
	return true;
}

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateUserForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "UsersAPI",
			type: type,
			data: $("#formUser").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onUserSaveComplete(response.responseText, status);
			}
		});
});


function onUserSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divUservsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidUserIDSave").val("");
	$("#formUser")[0].reset();
}



function onUserDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divUsersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}