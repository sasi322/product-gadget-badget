<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/main.js"></script>

<meta charset="ISO-8859-1">
<title>Product Management</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Product Management</h1>        
				
				<form id="formProduct" name="formProduct" method="post" action="Product.jsp">  
					Product ID:  
					<input id="productID" name="productID" type="text" class="form-control form-control-sm">  
					
					<br> 
					Product Name:  
					<input id="productName" name="productName" type="text" class="form-control form-control-sm">  
					
					<br>
					 Product Type:  
					 <input id="productType" name="productType" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Product Description:  
					 <input id="productDescription" name="productDescription" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					Product Price:  
					<input id="productPrice" name="productPrice" type="text" class="form-control form-control-sm">  
					
					<br>
					 Product Quantity:  
					 <input id="productQuantity" name="productQuantity" type="text" class="form-control form-control-sm">
					
					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidproductIDSave" name="hidproductIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
				 <br>
                   <div id="divProductGrid">   
					<%    
						Product productObj = new Product();
						out.print(productObj.readProduct());   
					%>  
					
					<br>
					<br>
					 
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		    
 		
 
	
</body>
</html>