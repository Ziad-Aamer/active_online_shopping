<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

</head>

<body>

	<div>

		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Register New User</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form
						action="${pageContext.request.contextPath}/customer/processRegistrationForm"
						modelAttribute="customer" class="form-horizontal">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-15">
								<div>

									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">

										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}</div>

									</c:if>

								</div>
							</div>
						</div>

						<!-- first name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>

							<form:input path="firstName" placeholder="firstName"
								class="form-control" />
						</div>
						<!-- Last name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>

							<form:input path="lastName" placeholder="lastname"
								class="form-control" />
						</div>
						<!-- Email -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>

							<form:input path="email" placeholder="email" class="form-control" />
						</div>

						<!-- Phone Number -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>

							<form:input type="text" path="phoneNumber"
								placeholder="phoneNumber" class="form-control" />
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>

							<form:password path="password" placeholder="password"
								class="form-control" />
						</div>

						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</div>
					</form:form>
<br>
        <button class="fb_login">Sign up with Facebook</button> 

				</div>

			</div>

		</div>

	</div>

        <script>
            window.fbAsyncInit = function() {
               FB.init({
                    appId      : '254163685248080',
                    status     : false, 
                    cookie     : true,
                    xfbml      : true,
                    oauth      : true
                });
                $(".fb_login").click(function() {
                   FB.login(Facebook_login);
                }); 
            };


            (function(d){
            var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
            js = d.createElement('script'); js.id = id; js.async = true;
            js.src = "//connect.facebook.net/en_US/all.js";
            d.getElementsByTagName('head')[0].appendChild(js);
            }(document));

          function Facebook_login () {
            FB.getLoginStatus(function(response) {
                if (response.status === 'connected') {
                	testAPI();
                }
            });
          }
          
          function testAPI() {
        	    console.log('Welcome!  Fetching your information.... ');
        	    FB.api('/me', { locale: 'en_US', fields: 'name, email,first_name,last_name'}, function(response) {
        	 // var customer = {email:response.email, firstName:response.first_name, lastName:response.last_name}
        	  
        	  window.location = '${pageContext.request.contextPath}/customer/FbUserProfile?email=' + response.email+'&'+'first_name='+response.first_name + '&'+'last_name='+response.last_name;
        	  //window.location = '${pageContext.request.contextPath}/customer/FbUserProfile?customer='+customer;
        	    });
        	  }
        </script>
</body>
</html>