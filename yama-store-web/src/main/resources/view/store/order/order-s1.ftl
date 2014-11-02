<html>
	<head>
		<title><@s.text name="page.store.catalog.title" /></title>
	</head>
	<div class="container">
		<div class="span12 fuelux">
		<h3 class="title"><span>Order</span></h3>
			<div class="well">
				<div id="report-wizard" class="report-wizard wizard hidden-phone">
					<ul class="steps">
						<li data-target="#s1" class="active"><p><span class="badge badge-info">1</span> Confirm Order</p><span class="chevron"></span></li>
						<li data-target="#s2"><p><span class="badge">2</span> Payment</p><span class="chevron"></span></li>
						<li data-target="#s3"><p><span class="badge">3</span> Done</p><span class="chevron"></span></li>
					</ul>
				</div>
				<div class="visible-phone">
					<div id="report-wizard-mobile" class="report-wizard">
						<ul class="steps">
							<li data-target="#sm1" class="active"><p><span class="badge badge-info">1</span> Confirm Order</p><span class="chevron"></span></li>
							<li data-target="#sm2"><p><span class="badge">2</span> Payment</p><span class="chevron"></span></li>
							<li data-target="#sm3"><p><span class="badge">3</span> Done</p><span class="chevron"></span></li>
						</ul>
					</div>
					<div class="step-content" style="margin-top: 10px;"></div>
					<hr>
				</div>
				<br>
				<h4>Please Confirm Your Order (${itemCount!} items)</h4>
				<table class="table table-bordered table-striped">
				  <thead>
					  <tr>
						<th>Image</th>
						<th>Item Name</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Total</th>
					  </tr>
					</thead>
					<tbody>
						<#assign grandTotal = 0 />
						<#list orders! as c>	
						  <tr>
								<td class="cntr" style="text-align: center;"><a href="<@s.url value="/p/${c.product.id}/detail?c=${c.product.category.name}" />"><img src="<@s.url value="/i/${c.image.image.id}/image" />" alt="${c.product.name}" width="80"></a></td>
								<td>${c.product.name!}</td>
								<td>${c.quantity!}</td>
								<#assign subTotal = (c.quantity!0) * (c.price!0) />
								<td style="text-align: right;">${(c.price!0)} IDR</td>
								<td style="text-align: right;">${subTotal} IDR</td>
							
						  </tr>
						  <#assign grandTotal = grandTotal + subTotal />			  
						</#list>
						<tr>
							<td colspan="4">&nbsp;</td>
							<td style="text-align: right;"><strong>${grandTotal!} IDR</strong></td>
						  </tr>
					</tbody>
					</table>
			</div>
		</div>
	</div>
</html>