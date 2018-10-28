<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>E-SHOP HTML Template</title>

<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Hind:400,700"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />

<!-- Slick -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/slick.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>
	<!-- HEADER -->
	<header>
		<!-- top Header -->
		<div id="top-header">
			<div class="container">
				<div class="pull-left">
					<span>Welcome to E-shop!</span>
				</div>
				<div class="pull-right">
					<ul class="header-top-links">
						<li><a href="#">Store</a></li>
						<li><a href="#">Newsletter</a></li>
						<li><a href="#">FAQ</a></li>
						<li class="dropdown default-dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="true">ENG <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="#">English (ENG)</a></li>
								<li><a href="#">Russian (Ru)</a></li>
								<li><a href="#">French (FR)</a></li>
								<li><a href="#">Spanish (Es)</a></li>
							</ul></li>
						<li class="dropdown default-dropdown"><a
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="true">USD <i class="fa fa-caret-down"></i></a>
							<ul class="custom-menu">
								<li><a href="#">USD ($)</a></li>
								<li><a href="#">EUR (€)</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- /top Header -->

		<!-- header -->
		<div id="header">
			<div class="container">
				<div class="pull-left">
					<!-- Logo -->
					<div class="header-logo">
						<a class="logo" href="#"> <img
							src="${pageContext.request.contextPath}/resources/img/logo.png"
							alt="">
						</a>
					</div>
					<!-- /Logo -->

					<!-- Search -->
					<div class="header-search">
						<form:form action="${pageContext.request.contextPath}/product/search"
							modelAttribute="category" method="get">
							<input class="input search-input" name="searchProducts"
								type="text" placeholder="Enter your keyword">

								<form:select path="categoryName">
								<form:option value="NONE"> --SELECT--</form:option>
									<c:forEach var="cat" items="${categories}">
									<form:option value="${cat.categoryName}" label="${cat.categoryName}" />
									</c:forEach>
								</form:select>

								<button type="submit" class="search-btn">
									<i class="fa fa-search"></i>
								</button>
							
						</form:form>
					</div>
					<!-- /Search -->
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown"
								aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<security:authorize access="isAuthenticated()">
									<strong class="text-uppercase">
										${loggedinUser.firstName} ${loggedinUser.lastName}<i
										class="fa fa-caret-down"></i>
									</strong>
								</security:authorize>
								<security:authorize access="!isAuthenticated()">
									<strong class="text-uppercase">My Account <i
										class="fa fa-caret-down"></i></strong>
								</security:authorize>

							</div> <security:authorize access="!isAuthenticated()">
								<a
									href="${pageContext.request.contextPath}/user/showMyLoginPage"
									class="text-uppercase">Login</a> / 
								<a
									href="${pageContext.request.contextPath}/customer/showRegistrationForm"
									class="text-uppercase">Join</a>
							</security:authorize> <security:authorize access="isAuthenticated()">
								<a href="${pageContext.request.contextPath}/user/logout"
									class="text-uppercase">Logout</a>
							</security:authorize>

							<ul class="custom-menu">
								<li><a href="#"><i class="fa fa-user-o"></i> My Account</a></li>
								<li><a href="#"><i class="fa fa-heart-o"></i> My
										Wishlist</a></li>
								<li><a href="#"><i class="fa fa-exchange"></i> Compare</a></li>
								<li><a href="#"><i class="fa fa-check"></i> Checkout</a></li>

							</ul>
						</li>
						<!-- /Account -->

						<!-- Cart -->
						<li class="header-cart dropdown default-dropdown"><a
							href="${pageContext.request.contextPath}/cart/showCart">
								<div class="header-btns-icon">
									<i class="fa fa-shopping-cart"></i> <span class="qty">3</span>
								</div> <strong class="text-uppercase">My Cart:</strong> <br>
						</a></li>
						<!-- /Cart -->

						<!-- Mobile nav toggle-->
						<li class="nav-toggle">
							<button class="nav-toggle-btn main-btn icon-btn">
								<i class="fa fa-bars"></i>
							</button>
						</li>
						<!-- / Mobile nav toggle -->
					</ul>
				</div>
			</div>
			<!-- header -->
		</div>
		<!-- container -->
	</header>
	<!-- /HEADER -->

	<!-- NAVIGATION -->
	<div id="navigation">
		<!-- container -->
		<div class="container">
			<div id="responsive-nav">
				<!-- category nav -->
				<div class="category-nav">
					<span class="category-header">Categories <i
						class="fa fa-list"></i></span>
					<ul class="category-list">
						<c:forEach var="category" items="${categories}">
							<li class="dropdown side-dropdown"><a
								class="dropdown-toggle" data-toggle="dropdown"
								aria-expanded="true"> ${category.categoryName} <i
									class="fa fa-angle-right"></i></a>
								<div class="custom-menu">
									<div class="row">
										<c:forEach items="${category.subCategories}" var="subCat">
											<div class="col-md-4">
												<ul class="list-links">
													<li>
														<h3 class="list-links-title">${subCat.name}</h3>
													</li>
													<c:forEach var="product" items="${subCat.products}">
														<li><a href="#">${product.name}</a></li>
													</c:forEach>
												</ul>
												<hr class="hidden-md hidden-lg">
											</div>
										</c:forEach>
									</div>

									<div class="row hidden-sm hidden-xs">
										<div class="col-md-12">
											<hr>
											<a class="banner banner-1" href="#"> <img
												src="${pageContext.request.contextPath}/resources/img/banner05.jpg"
												alt="">
												<div class="banner-caption text-center">
													<h2 class="white-color">NEW COLLECTION</h2>
													<h3 class="white-color font-weak">HOT DEAL</h3>
												</div>
											</a>
										</div>
									</div>

								</div></li>
						</c:forEach>

						<li><a href="#">View All</a></li>
					</ul>
				</div>
				<!-- /category nav -->

				<!-- menu nav -->
				<div class="menu-nav">
					<span class="menu-header">Menu <i class="fa fa-bars"></i></span>
					<ul class="menu-list">
						<li><a href="#">Home</a></li>
						<li><a href="#">Shop</a></li>
						<li class="dropdown mega-dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" aria-expanded="true">Women <i
								class="fa fa-caret-down"></i></a>
							<div class="custom-menu">
								<div class="row">
									<div class="col-md-4">
										<ul class="list-links">
											<li>
												<h3 class="list-links-title">Categories</h3>
											</li>
											<li><a href="#">Women’s Clothing</a></li>
											<li><a href="#">Men’s Clothing</a></li>
											<li><a href="#">Phones & Accessories</a></li>
											<li><a href="#">Jewelry & Watches</a></li>
											<li><a href="#">Bags & Shoes</a></li>
										</ul>
										<hr class="hidden-md hidden-lg">
									</div>
									<div class="col-md-4">
										<ul class="list-links">
											<li>
												<h3 class="list-links-title">Categories</h3>
											</li>
											<li><a href="#">Women’s Clothing</a></li>
											<li><a href="#">Men’s Clothing</a></li>
											<li><a href="#">Phones & Accessories</a></li>
											<li><a href="#">Jewelry & Watches</a></li>
											<li><a href="#">Bags & Shoes</a></li>
										</ul>
										<hr class="hidden-md hidden-lg">
									</div>
									<div class="col-md-4">
										<ul class="list-links">
											<li>
												<h3 class="list-links-title">Categories</h3>
											</li>
											<li><a href="#">Women’s Clothing</a></li>
											<li><a href="#">Men’s Clothing</a></li>
											<li><a href="#">Phones & Accessories</a></li>
											<li><a href="#">Jewelry & Watches</a></li>
											<li><a href="#">Bags & Shoes</a></li>
										</ul>
									</div>
								</div>
								<div class="row hidden-sm hidden-xs">
									<div class="col-md-12">
										<hr>
										<a class="banner banner-1" href="#"> <img
											src="${pageContext.request.contextPath}/resources/img/banner05.jpg"
											alt="">
											<div class="banner-caption text-center">
												<h2 class="white-color">NEW COLLECTION</h2>
												<h3 class="white-color font-weak">HOT DEAL</h3>
											</div>
										</a>
									</div>
								</div>
							</div></li>
						<li class="dropdown mega-dropdown full-width"><a
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="true">Men <i class="fa fa-caret-down"></i></a>
							<div class="custom-menu">
								<div class="row">
									<div class="col-md-3">
										<div class="hidden-sm hidden-xs">
											<a class="banner banner-1" href="#"> <img
												src="${pageContext.request.contextPath}/resources/img/banner06.jpg"
												alt="">
												<div class="banner-caption text-center">
													<h3 class="white-color text-uppercase">Women’s</h3>
												</div>
											</a>
											<hr>
										</div>
										<ul class="list-links">
											<li>
												<h3 class="list-links-title">Categories</h3>
											</li>
											<li><a href="#">Women’s Clothing</a></li>
											<li><a href="#">Men’s Clothing</a></li>
											<li><a href="#">Phones & Accessories</a></li>
											<li><a href="#">Jewelry & Watches</a></li>
											<li><a href="#">Bags & Shoes</a></li>
										</ul>
									</div>
									<div class="col-md-3">
										<div class="hidden-sm hidden-xs">
											<a class="banner banner-1" href="#"> <img
												src="${pageContext.request.contextPath}/resources/img/banner07.jpg"
												alt="">
												<div class="banner-caption text-center">
													<h3 class="white-color text-uppercase">Men’s</h3>
												</div>
											</a>
										</div>
										<hr>
										<ul class="list-links">
											<li>
												<h3 class="list-links-title">Categories</h3>
											</li>
											<li><a href="#">Women’s Clothing</a></li>
											<li><a href="#">Men’s Clothing</a></li>
											<li><a href="#">Phones & Accessories</a></li>
											<li><a href="#">Jewelry & Watches</a></li>
											<li><a href="#">Bags & Shoes</a></li>
										</ul>
									</div>
									<div class="col-md-3">
										<div class="hidden-sm hidden-xs">
											<a class="banner banner-1" href="#"> <img
												src="${pageContext.request.contextPath}/resources/img/banner08.jpg"
												alt="">
												<div class="banner-caption text-center">
													<h3 class="white-color text-uppercase">Accessories</h3>
												</div>
											</a>
										</div>
										<hr>
										<ul class="list-links">
											<li>
												<h3 class="list-links-title">Categories</h3>
											</li>
											<li><a href="#">Women’s Clothing</a></li>
											<li><a href="#">Men’s Clothing</a></li>
											<li><a href="#">Phones & Accessories</a></li>
											<li><a href="#">Jewelry & Watches</a></li>
											<li><a href="#">Bags & Shoes</a></li>
										</ul>
									</div>
									<div class="col-md-3">
										<div class="hidden-sm hidden-xs">
											<a class="banner banner-1" href="#"> <img
												src="${pageContext.request.contextPath}/resources/img/banner09.jpg"
												alt="">
												<div class="banner-caption text-center">
													<h3 class="white-color text-uppercase">Bags</h3>
												</div>
											</a>
										</div>
										<hr>
									</div>
								</div>
							</div></li>
					</ul>
				</div>
				<!-- menu nav -->
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /NAVIGATION -->

	<!-- HOME -->
	<div id="home">
		<!-- container -->
		<div class="container">
			<!-- home wrap -->
			<div class="home-wrap">
				<!-- home slick -->
				<div id="home-slick">
					<!-- banner -->
					<div class="banner banner-1">
						<img
							src="${pageContext.request.contextPath}/resources/img/banner01.jpg"
							alt="">
						<div class="banner-caption text-center">
							<h1>Bags sale</h1>
							<h3 class="white-color font-weak">Up to 50% Discount</h3>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
					<!-- /banner -->

					<!-- banner -->
					<div class="banner banner-1">
						<img
							src="${pageContext.request.contextPath}/resources/img/banner02.jpg"
							alt="">
						<div class="banner-caption">
							<h1 class="primary-color">
								HOT DEAL<br> <span class="white-color font-weak">Up
									to 50% OFF</span>
							</h1>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
					<!-- /banner -->

					<!-- banner -->
					<div class="banner banner-1">
						<img
							src="${pageContext.request.contextPath}/resources/img/banner03.jpg"
							alt="">
						<div class="banner-caption">
							<h1 class="white-color">
								New Product <span>Collection</span>
							</h1>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
					<!-- /banner -->
				</div>
				<!-- /home slick -->
			</div>
			<!-- /home wrap -->
		</div>
		<!-- /container -->
	</div>
	<!-- /HOME -->

	<!-- section -->



	<!-- section -->
	<div class="section section-grey">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- banner -->
				<div class="col-md-8">
					<div class="banner banner-1">
						<img
							src="${pageContext.request.contextPath}/resources/img/banner13.jpg"
							alt="">
						<div class="banner-caption text-center">
							<h1 class="primary-color">
								HOT DEAL<br> <span class="white-color font-weak">Up
									to 50% OFF</span>
							</h1>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
				</div>
				<!-- /banner -->

				<!-- banner -->
				<div class="col-md-4 col-sm-6">
					<a class="banner banner-1" href="#"> <img
						src="${pageContext.request.contextPath}/resources/img/banner11.jpg"
						alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NEW COLLECTION</h2>
						</div>
					</a>
				</div>
				<!-- /banner -->

				<!-- banner -->
				<div class="col-md-4 col-sm-6">
					<a class="banner banner-1" href="#"> <img
						src="${pageContext.request.contextPath}/resources/img/banner12.jpg"
						alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NEW COLLECTION</h2>
						</div>
					</a>
				</div>
				<!-- /banner -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">

			<!-- row -->
			<div class="row">
				<!-- section title -->
				<div class="col-md-12">
					<div class="section-title">
						<h2 class="title">Browse Products</h2>
					</div>
				</div>
				<!-- section title -->

				<!-- Product Single -->
						<c:forEach var="product" items="${products}">
							<div class="col-md-3 col-sm-6 col-xs-6">
								<div class="product product-single">
									<div class="product-thumb">
										<button class="main-btn quick-view">
											<i class="fa fa-search-plus"></i> Quick view
										</button>
										<img
											src="${pageContext.request.contextPath}/resources/img/${product.url}"
											height="200px" width="300px" alt="">
									</div>
									<div class="product-body">
										<h3 class="product-price">$${product.price}</h3>
										<div class="product-rating">
											<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> <i
												class="fa fa-star-o empty"></i>
										</div>
										<h2 class="product-name">
											<a href="#">${product.name}</a>
										</h2>
										<i class="product-name"> <a href="#">${product.description}</a>
										</i>
										<div class="product-btns">
											<button class="main-btn icon-btn">
												<i class="fa fa-heart"></i>
											</button>
											<button class="main-btn icon-btn">
												<i class="fa fa-exchange"></i>
											</button>
											<a href="${pageContext.request.contextPath}/cart/addProduct/${product.id}/" >
											<button class="primary-btn add-to-cart" >
												<i class="fa fa-shopping-cart"></i> Add to Cart
											</button>
											</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
				<!-- /Product Single -->


			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- FOOTER -->
	<footer id="footer" class="section section-grey">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- footer widget -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="footer">
						<!-- footer logo -->
						<div class="footer-logo">
							<a class="logo" href="#"> <img
								src="${pageContext.request.contextPath}/resources/img/logo.png"
								alt="">
							</a>
						</div>
						<!-- /footer logo -->

						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna</p>

						<!-- footer social -->
						<ul class="footer-social">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram"></i></a></li>
							<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
						</ul>
						<!-- /footer social -->
					</div>
				</div>
				<!-- /footer widget -->

				<!-- footer widget -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="footer">
						<h3 class="footer-header">My Account</h3>
						<ul class="list-links">
							<li><a href="#">My Account</a></li>
							<li><a href="#">My Wishlist</a></li>
							<li><a href="#">Compare</a></li>
							<li><a href="#">Checkout</a></li>
							<li><a href="#">Login</a></li>
						</ul>
					</div>
				</div>
				<!-- /footer widget -->

				<div class="clearfix visible-sm visible-xs"></div>

				<!-- footer widget -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="footer">
						<h3 class="footer-header">Customer Service</h3>
						<ul class="list-links">
							<li><a href="#">About Us</a></li>
							<li><a href="#">Shiping & Return</a></li>
							<li><a href="#">Shiping Guide</a></li>
							<li><a href="#">FAQ</a></li>
						</ul>
					</div>
				</div>
				<!-- /footer widget -->

				<!-- footer subscribe -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="footer">
						<h3 class="footer-header">Stay Connected</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor.</p>
						<form>
							<div class="form-group">
								<input class="input" placeholder="Enter Email Address">
							</div>
							<button class="primary-btn">Join Newslatter</button>
						</form>
					</div>
				</div>
				<!-- /footer subscribe -->
			</div>
			<!-- /row -->
			<hr>
			<!-- row -->
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<!-- footer copyright -->
					<div class="footer-copyright">
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						All rights reserved | This template is made with <i
							class="fa fa-heart-o" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</div>
					<!-- /footer copyright -->
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</footer>
	<!-- /FOOTER -->

	<!-- jQuery Plugins -->
	<!-- 	<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script> -->
	<!-- 	<script -->
	<!-- 		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.sticky/1.0.4/jquery.sticky.js"></script> -->

	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/slick.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/nouislider.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.zoom.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>

</html>
