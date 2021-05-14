$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateProductForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidproductIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ProductAPI",
		type : t,
		data : $("#formProduct").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onProductSaveComplete(response.responseText, status);
			console.log(response);
		}
	});
}); 

function onProductSaveComplete(response, status){
	if(status == "success")
	{
		console.log(response +  " "+status);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
			console.log("dataaaaaa");
					
			$("#divProductGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	
	
	$("#hidproductIDSave").val("");
	$("#form")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidproductIDSave").val($(this).closest("tr").find('#hidproductIDUpdate').val());     
	$("#productrName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#productType").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#productDescription").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#productPrice").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#productQuantity").val($(this).closest("tr").find('td:eq(4)').text()); 

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ProductAPI",
		type : "DELETE",
		data : "productID=" + $(this).data("productid"),
		dataType : "text",
		complete : function(response, status)
		{
			onProductDeletedComplete(response.responseText, status);
		}
	});
});

function onProductDeletedComplete(response, status)
{
	if(status == "success")
	{
		console.log(response);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divProductGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateProductForm() {  
	// NAME  
	if ($("#productName").val().trim() == "")  {   
		return "Insert Name.";  
		
	} 
	
	 // Type 
	if ($("#productType").val().trim() == "")  {   
		return "Insert Type.";  
	} 
	
	
	// Description  
	if ($("#productDescription").val().trim() == "")  {   
		return "Insert Description."; 
		 
	}
	 
	// Price  
	if ($("#productPrice").val().trim() == "")  {   
		return "Insert Price."; 
		 
	}
	
	// Quantity  
	if ($("#productQuantity").val().trim() == "")  {   
		return "Insert Quantity."; 
		 
	}
	 
	
	 
	 return true; 
	 
}
