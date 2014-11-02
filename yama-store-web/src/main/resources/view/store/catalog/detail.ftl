<html>
	<head>
		<title><@s.text name="page.store.catalog.title" /></title>
	</head>
	<div class="container" ng-controller="qstore-product-detail">
		<h3 class="title"><span><@s.text name="label.catalog.detail.product" /></span></h3>
		<section id="featuredProduct" ng-model="product">
		<@s.form theme="bootstrap" action="/shopping/cart/-/edit" enctype="multipart/form-data">
			<div class="row">
				<div class="span6">
					<#assign images = product.images />
					<#if (images?size > 0)>
						<#assign url = "/i/"+images.get(0).image.id+"/image" />
					<#else>
						<#assign url = "images/no-profile.jpg" />
					</#if>
						<img src="<@s.url value="${url}" />" width="100%" alt="${product.name!}">
				</div>
				<div class="span6">
					<div class="promoDetail">
						<h1>${product.name!} ${modal!} <small>${product.category.name!}</small></h1>
						<p><@s.text name="label.catalog.detail.description" /></p>
						${product.description!}
						<h3>Price : ${product.price!} <small><@s.text name="label.catalog.curency" /></small></h3>
					</div>
					<div class="span6 well no-margin">
						<h4 class="title"><span>Add to <i class="icon-shopping-cart"></i></span></h4>
						<#--<h4><span>Varian</span></h4>
						<ul id="sku-sku2" class="sku-attr sku-checkbox">
							<#list product.images as i>
								<li class="" id="${i.id!}">
									<a class="sku-value attr-checkbox" id="sku-2-100014066" href="javascript:check('${i.id}');">
										<span>
											<img class="imgTum" src="<@s.url value="/i/${i.image.id}/image" />" width="100%" alt="${product.name!}">
										</span>
									</a>
								</li>
							</#list>
						</ul>-->
						<div>
							<input type="hidden" class="span1" name="product" value="${product.id!}" style="margin-bottom: 0px;">
							<input type="hidden" class="span1" id="imade-id" name="image" value="<#if (images?size > 0)>${images.get(0).id}</#if>" style="margin-bottom: 0px;">
							<input type="number" class="" placeholder="Qty." name="shoping.quantity" min="1" value="1" style="margin-bottom: 0px;">
							<hr>
							<#--<button type="button" class="btn btn-warning">Buy Now</button>-->
							<button type="submit" class="btn btn-success">Add to <i class="icon-shopping-cart"></i></button>
						</div>
						<br>
					</div>
				</div>
			</div>
			</@s.form>
		</section>
		<hr class="soften"/>
	</div>
	<div class="container">
		<section id="featuredProduct">
			<h3 class="title">
				<span><@s.text name="label.catalog.relate.product" /></span>
			</h3>
			
			<div class="row">
			<#list products.content as p>
				<div class="span3">
					<div class="well well-small" style="padding:5px;">
						<#assign images = p.images />
						<#if (images?size > 0)>
							<#assign url = "/i/"+images.get(0).image.id+"/image" />
						<#else>
							<#assign url = "images/no-profile.jpg" />
						</#if>
						<a class="displayStyle" href="<@s.url value="${url}" />">
							<img src="<@s.url value="${url}" />" alt="#" style="height: 258px;"/>
						</a>
						<div class="row-fluid">
							<div class="span12">
								<h5>${p.name!} <small>(${p.category.name!})</small></h5>
							</div>
						</div>
						<p>
							<span class="price">${p.price!} <@s.text name="label.catalog.curency" /></span>
						</p>
						<p> 
							<a class="btn" href="<@s.url value="/p/${p.id!}/detail?c=${p.category.name!}" />"><@s.text name="label.catalog.view.details" /></a>
						</p>
						
					</div>
				</div>
				</#list>
			</div>
	
		</section>
		<hr class="soften"/>
		<script>
			function myFunction(a, b) {
			    return a * b;
			}	
		
			function check(id){
				$("#imade-id").val(id);
				$("#sku-sku2 li").removeClass("chart-active");
				$("#sku-sku2 li i").remove();
				$("#"+id).addClass("chart-active").append('<i></i>');
			}
			<#if modal == 1>
				$( document ).ready(function() {
					$('#myModal').modal("show");
				});
			</#if>
		</script>
	</div>
	
 
	<!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">Shopping Cart</h3>
		</div>
		<div class="modal-body">
			<h5>A new item has been added to your Shopping Cart.</h5>
			<p>You now have ${(carts.numberOfElements!0)} items in your Shopping Cart.</p>
		
			<a href="<@s.url value="/shopping/cart" />" class="btn btn-warning" >View Shopping Cart</a>
			<a href="<@s.url value="/" />" class="btn btn-warning" >Continue Shopping</a>
		</div>
	</div>
	
	
</html>