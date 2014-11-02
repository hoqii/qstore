<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>${title!} - <@s.text name="page.main.title" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<!-- styles -->
		<link rel="stylesheet/less" type="text/css" href="<@s.url value="/themes/less/bootstrap.less" />">
		<script src="<@s.url value="/themes/js/less/less.js" />" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/adminlte/fuelux.min.css" />">
		<link rel="stylesheet" type="text/css" href="<@s.url value="/styles/adminlte/fuelux-responsive.min.css" />">
		
		
		
		<link rel="stylesheet" type="text/css" href="<@s.url value="/themes/font-awesome/css/font-awesome.min.css" />">

		<!-- favicon-icons -->
		<link rel="shortcut icon" href="<@s.url value="/themes/images/favicon.ico" />">
		<style type="text/css">
			.imgTum{
				width: 70px;
				float: left;
			}
			
			.imgactive{
				border: 2px solid #f89406;
			}
			
			.sku-attr li {
				position: relative;
				background: #fff;
				display: inline-block;
				margin: 0 7px 7px 0;
				zoom: 1;
			}
			
			.sku-attr a:hover {
				color: #f90;
				border: 2px solid #ff6908;
			}

			.sku-attr .attr-checkbox {
				height: 50px;
				line-height: 15px;
				margin: 0px;
			}
			.sku-attr a {
				float: left;
				border: 1px solid #CCC;
				text-decoration: none;
				color: #000;
				cursor: pointer;
				height: 15px;
				zoom: 1;
				overflow: hidden;
				border: 2px solid transparent;
			}
			
			.chart-active{
				border: 2px solid #ff6908;
			}
			
			.sku-checkbox i {
				position: absolute;
				bottom: 1px;
				right: 1px;
				height: 9px;
				overflow: hidden;
				text-indent: -9999em;
				width: 9px;
				background: url(http://style.aliunicorn.com/wimg/buyer/sprite/ws-buyer-detail-v130514.png?t=4583613b_0) no-repeat 0 -124px;
			}			
			.no-margin{
				margin: 0px;
			}
		</style>
		<script src="<@s.url value="/themes/js/jquery-1.8.3.min.js" />"></script>
		${head!}
	</head>
	<body>
		<header class="header">
			<div class="container">
				<div class="row">
					<div class="offset6 span6 right-align loginArea">
						<a href="<@s.url value="/dashboard" />"><span class="btn btn-mini btn-success"><@s.text name="button.main.dashboard" /></span></a> 
						<#--<a href="<@s.url value="/news" />"><span class="btn btn-mini btn-success">Berita</span></a>-->
						<a data-toggle="modal" href="<@s.url value="/shopping/cart" />"><span class="btn btn-mini"><i class="icon-shopping-cart"></i> [${(carts.numberOfElements!0)}]</span></a>
						<#if currentUser??>
							<a data-toggle="modal" href="/logout"><span class="btn btn-mini btn-danger"><@s.text name="button.main.logout" /></span></a>
						<#else>
							<a data-toggle="modal" href="/login"><span class="btn btn-mini btn-info"><@s.text name="button.main.login" /></span></a>
						</#if>
					</div>
				</div>
				<div class="navbar">
					<div class="navbar-inner">
						<a class="brand" href="<@s.url value="/" />">
							<h2><@s.text name="page.main.title" /> <small><@s.text name="page.catalog.header.description" /></small></h2>
						</a>
						<div class="nav-collapse">
							<ul id="topMenu" class="nav pull-right">
								 <li class="">
									  <form class="form-inline navbar-search" style="padding-top:5px;" method="GET" action="<@s.url value="${request.servletPath}" />">
											<select name="c" class="span3" style="padding:11px 4px; height:auto">
												<option value=""><@s.text name="label.catalog.category.all" /></option>
												<#list categories.content as c>
													<option value="${c.name!}" >${c.name!}</option>
												</#list>
											</select> 
											<input name="q" value="${q!}" class="span4" type="text" style="padding:11px 4px;">
											<button type="submit" class="btn btn-warning btn-large" style="margin-top:0"> <@s.text name="button.main.go" /> </button>
										</form>
								</li>
							</ul>
						</div>
						<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
				</div>
			</div>
		</header>
		${body}
		<!-- Footer ================================================== -->
		<footer class="footer">
			<div class="container">
				<div class="footerMenu">
					<br>
					<a href="register.php"> <@s.text name="label.catalog.about.us" /></a>
					<p class="pull-right">&copy; Copyright - <@s.text name="page.main.title" />. </p>
				</div>
			</div>
		</footer>
		<span id="toTop" style="display: none;"><span><i class="icon-angle-up"></i></span></span>

		<!--	javascript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		
		<script src="<@s.url value="/themes/bootstrap/js/bootstrap.min.js" />"></script>
		<script src="<@s.url value="/themes/js/smart.js" />"></script>
		<script src="<@s.url value="/scripts/adminlte/loader.min.js" />"></script>
		${page.getProperty('page.script')!}
	</body>
</html>