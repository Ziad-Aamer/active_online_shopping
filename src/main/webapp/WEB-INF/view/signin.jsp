<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Sign In</title>
	</head>
	<body>
		<h2>This application requires you to sign in to Facebook</h2>
		
		<br>
<!-- 		 <!-- Social Sign In Buttons --> -->
<!--     <div class="panel panel-default"> -->
<!--         <div class="panel-body"> -->

<!--             <div class="row social-button-row"> -->
<!--                 <div class="col-lg-4"> -->
<!--                     Add Facebook sign in button -->
<%--                     <a href="${pageContext.request.contextPath}/signin/facebook">testtttttttt</a> --%>
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
			<form name='facebookSocialloginForm'
            		  action="<c:url value='../auth/facebook?scope=email,user_about_me,user_birthday' />" method='POST'>
							<img src="../images/facebook.png" alt="">
							<button type="submit">
								<i>Sign In with Facebook</i>
							</button>	
							<div class="clear"></div>
					</form>		
		<form action="<c:url value="../auth/facebook" />" method="POST">
		    <button type="submit">Sign in with Facebook</button>
		    <input type="hidden" name="scope" value="public_profile,email" />	    
		</form>
	</body>
</html>
