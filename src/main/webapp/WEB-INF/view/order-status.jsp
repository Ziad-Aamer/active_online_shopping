<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
</head>
<body>
	<h2>order status</h2>
<!-- //onsubmit="return closeSelf(this)" -->
	<form:form action="updateOrderStatus" name="certform" modelAttribute="order" >
	
	<label>order id: </label> <label>${order.id}</label>
	 <form:input type="hidden" path="id"/>
	 	 <form:input type="hidden" path="totalPrice"/>
		<form:select path="status">
				<c:forEach var="orderItemStatus" items="${ordersStatus}">
					<c:choose>
						<c:when test="${order.status == orderItemStatus}">
							<form:option value="${orderItemStatus}" selected="true"
								label="${orderItemStatus}" />
						</c:when>
						<c:otherwise>
							<form:option value="${orderItemStatus}"
								label="${orderItemStatus}" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
		<td>${order.totalPrice}</td>
	<form:label path="totalPrice"/>
	<input type="submit" value="Update" />

	</form:form>
<!-- 	<script type="text/javascript"> -->
<!-- // 	function closeSelf(f){ -->
<!-- // 	  //  document.forms['certform'].submit(); -->
<!-- // 	    document.forms.submit(); -->
<!-- // 		//f.submit(); -->
<!-- // 		console.log('inside close self'); -->
<!-- // 		window.close(); -->
<!-- // 	   // self.close(); -->
<!-- // 	    return true; -->
<!-- // 	} -->
<!-- 	</script> -->
</body>
</html>