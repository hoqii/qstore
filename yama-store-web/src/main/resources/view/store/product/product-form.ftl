<html>
	<head>
		<title><@s.text name="page.store.product.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.store.product.header" /></content>
		<content tag="script">
		<script type="text/javascript">
		</script>
		</content>

		<@s.actionerror theme="bootstrap"/>
		<@s.actionmessage theme="bootstrap"/>
		<div class="row">
			<@s.form theme="bootstrap" enctype="multipart/form-data">
			<div class="col-md-8">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.store.product.title" /></h3>
					</div>
					
					<div class="box-body">
						
							<@s.hidden name="product.id" />
							<@s.textfield key="label.store.product.name" name="product.name" value="${(product.name!'')}" />
							<@s.textfield key="label.store.product.price" name="product.price" value="${(product.price!0)?c}" />
							<div class="form-group <#if categoryEmty??>has-error has-feedback</#if>">
								<label class="control-label">
										<@s.text name="label.store.product.category" />
								</label>    
								<div class="controls">
									
										<select name="category" class="form-control">
											<option value="">-- <@s.text name="label.store.combo.category" /> --</option>
											<#list categories.content as c>
												<option value="${c.id!}" <#if product.category??><#if product.category.id == c.id>selected</#if></#if>>${c.name!}</option>
											</#list>
										</select>
										<#if categoryEmty??>
											<span class="glyphicon glyphicon-remove form-control-feedback"></span>
											<span class="help-block alert-danger">${categoryEmty!}</span>
										</#if>
								</div>
							</div>
							<div class="form-group <#if uomEmty??>has-error has-feedback</#if>">
								<label class="control-label">
										<@s.text name="label.store.product.uom" />
								</label>    
								<div class="controls">
										<select name="uom" class="form-control">
											<option value="">-- <@s.text name="label.store.combo.uom" /> --</option>
											<#list uoms.content as u>
												<option value="${u.id!}" <#if product.unitOfMeasure??><#if product.unitOfMeasure.id == u.id>selected</#if></#if>>${u.name!}</option>
											</#list>
										</select>
										<#if uomEmty??>
											<span class="glyphicon glyphicon-remove form-control-feedback"></span>
											<span class="help-block alert-danger">${uomEmty!}</span>
										</#if>
								</div>
							</div>
							
							<@s.textarea rows="2" key="label.store.product.description" name="product.description" />
							
							<div class="form-group ">
								<label class="  control-label" for="edit_product_description"></label>    
								<div class="  controls">
									<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
									<br><br>
								</div>
							</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.store.product.image.title" /></h3>
					</div>
					
					<div class="box-body">
						<div class="row">
							<#assign images = product.images />
							
							<#list images! as i>
								<img src="<@s.url value="/backend/product/${i.image.id!}/image" />" class="img-thumbnail col-md-3 col-md-offset-2 col-xs-offset-2 col-xs-8" alt="Product Image">
								<#if (i_index % 2) == 1>
									<div class="col-md-12">
									&nbsp;
									</div>
								</#if>
							</#list>
							
							</div>
							<@s.file key="button.main.browse" name="productImage" />
					</div>
				</div>
			</div>
			</@s.form>
		</div>
	</body>
</html>