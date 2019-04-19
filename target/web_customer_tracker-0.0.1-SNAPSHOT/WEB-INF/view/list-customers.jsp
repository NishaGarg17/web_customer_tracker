<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>
<title>List Customers</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">
			<input type="button" value="Add Customer"
				onClick="window.location.href='showFormForAdd';return false;"
				class="add-button">
			<!--  add our html table here -->

			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>

				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customerList}">
					<!-- Construct an update link using customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
					 <c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							<!--  Show Update Link -->
							<a href="${updateLink}">Update</a>
						</td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>








