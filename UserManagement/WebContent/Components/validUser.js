$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateUserForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "userManageAPI",  
			type : type,  
			data : $("#formUser").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onUserSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onUserSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#user").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidUserIDSave").val("");  
	$("#formUser")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidUserIDSave").val($(this).closest("tr").find('#hidUserIDUpdate').val());     
	$("#userName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#userAddress").val($(this).closest("tr").find('td:eq(1)').text());
	$("#userNIC").val($(this).closest("tr").find('td:eq(2)').text());
	$("#userEmail").val($(this).closest("tr").find('td:eq(3)').text());
	$("#userPNO").val($(this).closest("tr").find('td:eq(4)').text());
	    
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "userManageAPI",   
		type : "DELETE",   
		data : "uID=" + $(this).data("uid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onUserDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onUserDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#user").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateUserForm() 
{  
	// UserName  
	if ($("#userName").val().trim() == "")  
	{   
		return "Insert userName.";  
	}

	// UserAddress------------------------  
	if ($("#userAddress").val().trim() == "")  
	{   
		return "Insert userAddress.";  
	} 
	
	// UserNIC------------------------  
	if ($("#userNIC").val().trim() == "")  
	{   
		return "Insert userNIC.";  
	}
	
	// UserEmail------------------------  
	if ($("#userEmail").val().trim() == "")  
	{   
		return "Insert userEmail.";  
	}
	
	// UserPNO------------------------  
	if ($("#userPNO").val().trim() == "")  
	{   
		return "Insert userPNO.";  
	}
	

	return true; 
}

