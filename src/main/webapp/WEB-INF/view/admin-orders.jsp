<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Orders</title>
<style type="text/css">
#testpopUp {
	width: 100%;
	height: 100%;
	opacity: .95;
	top: 0;
	left: 0;
	display: none;
	position: fixed;
	background-color: #313131;
	overflow: auto
}

div#popupContact {
	position: absolute;
	left: 50%;
	top: 17%;
	margin-left: -202px;
	font-family: 'Raleway', sans-serif
}

form {
	max-width: 300px;
	min-width: 250px;
	padding: 10px 50px;
	font-family: raleway;
	background-color: #fff
}

button {
	width: 10%;
	height: 45px;
	border-radius: 3px;
	background-color: #cd853f;
	color: #fff;
	font-family: 'Raleway', sans-serif;
	font-size: 18px;
	cursor: pointer
}
</style>
</head>
<body>
	<h2>All Orders</h2>

	<form action="${pageContext.request.contextPath}/orders/search"
		method="get">
		<input type="text" name="orderStatusSearch"
			placeholder="Search By Order Status ..." /> <input type="submit"
			value="Search" />
	</form>


	<div id="wrapper">

		<div id="header">
			<h2>All Orders</h2>
		</div>

		<div id="container">

			<div id="content">
				<table>
					<tr>
						<th>Order id</th>
						<th>status</th>
						<th>total price</th>
						<th>Action</th>

					</tr>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.id}</td>
							<td>${order.status}</td>
							<td>${order.totalPrice }</td>
							<td><a href="#" onclick="div_show(${order.id})">Update</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
<script>

function div_show(id) {
	var url = "${pageContext.request.contextPath}/orders/showFormUpdate?orderId=" + id;
  //  var position = (left == undefined || top == undefined) ? 'centerscreen' : 'left=' + left + ', top=' + top;
// 	  window.open("${pageContext.request.contextPath}/orders/showFormUpdate?orderId=" + id,""
// 	 ,"height=250,width=400,status=no,location=no,toolbar=no,directories=no,menubar=no");
	 window.open
	 (url, 'popupModalWindow' + new Date().getTime(), 'width=500px, height=500px, chrome, dependent=1, dialog=1, modal=1, resizable=0, scrollbars=0, location=0, status=0, menubar=0, toolbar=0 ');
	}
	
</script>

</html>