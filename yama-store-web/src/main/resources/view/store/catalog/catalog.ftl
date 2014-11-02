<html>
	<head>
		<title><@s.text name="page.store.catalog.title" /></title>
	</head>
	<div class="container">
		<section id="featuredProduct">
			<h3 class="title">
				<span><@s.text name="label.catalog.our.product" /></span>
			</h3>
	
			<div class="row">
				<#list products.content as p>
				<div class="span3">
					<div class="well well-small" style="padding: 5px;">
						<#assign images = p.images /> 
						<#if (images?size > 0)> 
							<#assign url = "i/"+images.get(0).image.id+"/image" /> 
						<#else> 
							<#assign url = "images/no-profile.jpg" /> 
						</#if> 
						<a class="displayStyle" href="<@s.url value=" ${url}" />"> 
							<img src="<@s.url value=" ${url}" />" alt="${p.name!}" style="height: 258px;"/> 
						</a>
						<div class="row-fluid">
							<div class="span12">
								<h5>
									${p.name!} <small>(${p.category.name!})</small>
								</h5>
							</div>
						</div>
						<p>
							<span class="price">${p.price!} <@s.text name="label.catalog.curency" /></span>
						</p>
						<p>
							<a class="btn" href="<@s.url value="/p/${p.id!}/detail?c=${p.category.name!}" />">
								<@s.text name="label.catalog.view.details" />
							</a>
						</p>
	
					</div>
				</div>
				</#list>
			</div>
	
		</section>
		<hr class="soften" />
	</div>
</html>

