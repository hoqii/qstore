<html>
	<head>
		<title><@s.text name="page.store.catalog.title" /></title>
	</head>
	<div class="container">
		<div class="span12">
		<h3 class="title"><span>Cart item details</span></h3>
			<div class="well">
			<table class="table table-bordered table-striped">
				  <thead>
					  <tr>
					  	<th>Order</th>
						<th>Image</th>
						<th>Item Name</th>
						<th>Quantity</th>
						<th>Unit Price</th>
						<th>Total</th>
						<th>Action</th>
					  </tr>
					</thead>
					<tbody>
					<#assign grandTotal = 0 />
					<@s.form theme="bootstrap" id="updateQtyForm" action="/order/s1">
					<#list carts.content as c>	
					  <tr>
					  	
					  	<td class="cntr" style="text-align: center;"><input type="checkbox" name="cid" value="${c.id}"></td>
					  	
							<td class="cntr" style="text-align: center;"><a href="<@s.url value="/p/${c.product.id}/detail?c=${c.product.category.name}" />"><img src="<@s.url value="/i/${c.image.image.id}/image" />" alt="${c.product.name}" width="80"></a></td>
							<td>${c.product.name!}</td>
							<td><input type="number" class="" placeholder="Qty." id="qty-${c.id}" min="1" value="${c.quantity!}" style="margin-bottom: 0px;"></td>
							<#assign subTotal = (c.quantity!0) * (c.price!0) />
							<td style="text-align: right;">${(c.price!0)} IDR</td>
							<td style="text-align: right;">${subTotal} IDR</td>
							<td>
								<button class="btn btn-success" onclick="changeUrl('${c.id!}',$('#qty-${c.id}').val());"><i class="icon-edit"></i></button>
								<a href="<@s.url value="/shopping/cart/${c.id}/delete" />" class="btn btn-danger"><i class="icon-trash"></i></a>
							</td>
						
					  </tr>
					  <#assign grandTotal = grandTotal + subTotal />			  
					</#list>
					  <tr>
						<td colspan="5">&nbsp;</td>
						<td style="text-align: right;"><strong>${grandTotal!} IDR</strong></td>
						<td>&nbsp;</td>
					  </tr>	
					  <tr>
						<td colspan="7">
							<button type="submit" class="btn btn-warning">Buy</button>
						</td>
					  </tr>
					   </@s.form>		  
					</tbody>
				  </table>
				  <@s.form theme="bootstrap" id="updateQtyForm" action="">
				  	<input type="hidden" id="tempQty" name="qty" value="0">
				  </@s.form>
				  <script>
				  
				  function changeUrl(id,qty){
					  $("#tempQty").val(qty);
					  $("#updateQtyForm").attr( "action",'<@s.url value="/shopping/cart/'+id+'/qty/update" />');
					  $("#updateQtyForm").submit();
					  
				  }
				  
				  </script>
			</div>
		</div>
	</div>
</html>