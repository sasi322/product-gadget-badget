package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	//DB connection
			private Connection connect() {
				Connection con = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product", "root", "");

					System.out.print("Successfully connected");

				} catch (Exception e) {
					e.printStackTrace();
				}

				return con;
			}

			public String readProduct() {
				
				
				
				String output = "";
				

				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}

					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th>" + "<th>Product Type</th><th>Product Description</th>"
							+ "<th>Product Price</th><th>Product Quantity</th>" + "<th>Update</th><th>Remove</th></tr>";

					String query = "select * from product";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					while (rs.next()) {

						String productID = Integer.toString(rs.getInt("productID"));
						String productName = rs.getString("productName");
						String productType = rs.getString("productType");
						String productDescription = rs.getString("productDescription");
						String productPrice = Double.toString(rs.getDouble("productPrice"));
						String productQuantity = Integer.toString(rs.getInt("productQuantity"));

						// Add into the html table

						output += "<tr><td><input id='hidproductIDUpdate' name='hidproductIDUpdate' type='hidden' value='"
								+ productID + "'>" + productName + "</td>";

						output += "<td>" + productType + "</td>";
						output += "<td>" + productDescription + "</td>";
						output += "<td>" + productPrice + "</td>";
						output += "<td>" + productQuantity + "</td>";

						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-productID='"
								+ productID + "'>" + "</td></tr>";

					}

					con.close();

					// Complete the html table
					
					output += "</table>";
					
				} catch (Exception e) {
					output = "Error while reading the Appointment.";
					System.err.println(e.getMessage());
				}
				
				

				return output;
				
				
			}

			
			public String insertProduct(String productName, String productType, String productDescription, String productPrice, String productQuantity) {
				
				
				String output = "";
				
				

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database";
					}

					// create a prepared statement
					String query = " insert into product (`productID`,`productName`,`productType`,`productDescription`,`productPrice`,`productQuantity`)"
							+ " values (?, ?, ?, ?, ?, ?)";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, productName);
					preparedStmt.setString(3, productType);
					preparedStmt.setString(4, productDescription);
					preparedStmt.setDouble(5, Double.parseDouble(productPrice));
					preparedStmt.setInt(5, Integer.parseInt(productQuantity));

					// execute the statement
					preparedStmt.execute();
					con.close();

					// Create JSON Object to show successful msg.
					String newProduct = readProduct();
					output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";
				} catch (Exception e) {
					// Create JSON Object to show Error msg.
					output = "{\"status\":\"error\", \"data\": \"Error while Inserting Productr.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}

			// Update updateProduct
			public String updateProduct(String productID, String productName, String productType, String productDescription, String productPrice, String productQuantity) {
				
				
				String output = "";

				
				
				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating.";
					}

					// create a prepared statement
					String query = "update product SET productName=?,productType=?,productDescription=?,productPrice=?,productQuantity=? WHERE productID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, productName);
					preparedStmt.setString(2, productType);
					preparedStmt.setString(3, productDescription);
					preparedStmt.setDouble(4, Double.parseDouble(productPrice));
					preparedStmt.setInt(5, Integer.parseInt(productQuantity));
					preparedStmt.setInt(6, Integer.parseInt(productID));

					// execute prepared statement
					preparedStmt.execute();
					con.close();

					// create JSON object to show successful msg
					String newProduct = readProduct();
					output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while Updating Product Details.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}

			
			
			public String deleteProduct(String productID) {
				
				
				String output = "";
				
				

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from product where productID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					
					preparedStmt.setInt(1, Integer.parseInt(productID));
					// execute the statement
					preparedStmt.execute();
					con.close();

					
					String newProduct = readProduct();
					output = "{\"status\":\"success\", \"data\": \"" + newProduct+ "\"}";
					
				} catch (Exception e) {
					
					
					output = "{\"status\":\"error\", \"data\": \"Error while Deleting Product.\"}";
					System.err.println(e.getMessage());

				}

				return output;
				
				
			}
}
