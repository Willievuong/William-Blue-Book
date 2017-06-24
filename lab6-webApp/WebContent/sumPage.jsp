<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Automobile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SumPage</title>
</head>
<body>

	<%
		Automobile builtCar = (Automobile) request.getAttribute("theCar");
		int totalPrice = builtCar.getBasePrice();
		int index = 0;
		 
		for(int i = 0; i < builtCar.getChoice().size(); i++){
			totalPrice = totalPrice + builtCar.getChoicePrice(i);
		}
	%>

	<h1>Configure Result</h1>
	Here is What You Selected:
	<table border="1">
		<tbody>
			<tr>
				<td>
					<% builtCar.getModel(); %>
				</td>
				<td>BasePrice</td>
				<td>
				<%= builtCar.getBasePrice() %>
				</td>
			</tr>
			<tr>
				<td>Paint</td>
				<td><%= builtCar.getChoiceName(index) %></td>
				<td><%= builtCar.getChoicePrice(index) %></td>
			</tr>
				<%index++; %>
			<tr>
				<td>Transmission</td>
				<td><%= builtCar.getChoiceName(index) %></td>
				<td><%= builtCar.getChoicePrice(index) %></td>
			</tr>
			
				<%index++; %>
			<tr>
				<td>ABS/Traction Control</td>
				<td><%= builtCar.getChoiceName(index) %></td>
				<td><%= builtCar.getChoicePrice(index) %></td>
			</tr>
				<%index++; %>
			<tr>
				<td>Side Impact Airbag</td>
				<td><%= builtCar.getChoiceName(index) %></td>
				<td><%= builtCar.getChoicePrice(index) %></td>
			</tr>
				<%index++; %>
			<tr>
				<td>Power MoonRoof</td>
				<td><%= builtCar.getChoiceName(index) %></td>
				<td><%=  builtCar.getChoicePrice(index) %></td>
			</tr>
				<%index++; %>
			<tr>
				<td>Total Cost</td>
				<td><%= "$" + totalPrice%></td>
			</tr>
		</tbody>

	</table>


</body>
</html>