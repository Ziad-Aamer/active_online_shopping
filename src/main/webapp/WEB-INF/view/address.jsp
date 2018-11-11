<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
#success_message {
	display: none;
}
</style>
<script type="text/javascript">
function CloseAndRefresh(){
	console.log("inside colse and refresh function ");
	window.opener.location.href="/{!$CurrentPage.parameters.id}";
	      window.top.close();
	      
	      window.opener.location.href = "/{!SObiect.ID}";
	      window.top.close();
	 
	  }
	$("#submitForm").click(function() {
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/customer/addAddress",
			data : $('#myform').serialize(),
			success : function(msg) {
				console.log('inside success func');
				$("#myModal").modal('close');
			},
			error : function() {
				$("#error").show();
			}
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
		
				<legend>Add New Address</legend>

				<br>
				<form:form
					action="${pageContext.request.contextPath}/customer/addAddress"
					modelAttribute="address" class="form-horizontal" id="myform">

					<!-- Place for messages: error, alert etc ... -->
					<div class="form-group">
						<div class="col-xs-15">
							<div>

								<!-- Check for registration error -->
								<c:if test="${FieldsRequired != null}">

									<div class="alert alert-danger col-xs-offset-1 col-xs-10">
										${FieldsRequired}</div>

								</c:if>

							</div>
						</div>
					</div>

					<!-- city -->
					<div style="margin-bottom: 25px" class="input-group">
						<span>city: </span>
						<form:input path="city" placeholder="city" class="form-control" />
					</div>
					<!-- street -->
					<div style="margin-bottom: 25px" class="input-group">
						<span>street: </span>
						<form:input path="street" placeholder="street"
							class="form-control" />
					</div>
					<!-- building -->
					<div style="margin-bottom: 25px" class="input-group">
						<span>building: </span>
						<form:input path="building" placeholder="building"
							class="form-control" />
					</div>

					<!-- floor -->
					<div style="margin-bottom: 25px" class="input-group">
						<span>floor: </span>
						<form:input type="text" path="floor" placeholder="floor"
							class="form-control" />
					</div>

					<!-- flatNumber -->
					<div style="margin-bottom: 25px" class="input-group">
						<span>flat No: </span>
						<form:input type="text" path="flatNumber" placeholder="flatNumber"
							class="form-control" />
					</div>

					<!-- landmark -->
					<div style="margin-bottom: 25px" class="input-group">
						<span>landmark: </span>
						<form:textarea type="text" path="landmark" placeholder="landmark"
							class="form-control" />
					</div>

					<!-- Register Button -->
					<div style="margin-top: 10px" class="form-group">
						<div class="col-sm-6 controls">
							<button type="submit" class="btn btn-primary" id="submitForm" onsubmit="CloseAndRefresh();">Add</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- /.container -->
</body>
</html>