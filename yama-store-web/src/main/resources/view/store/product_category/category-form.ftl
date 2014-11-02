<html>
	<head>
		<title><@s.text name="page.store.product.category.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.store.product.category.header" /></content>
		<content tag="script">
		<script type="text/javascript">
		</script>
		</content>

		<@s.actionerror theme="bootstrap"/>
		<@s.actionmessage theme="bootstrap"/>
		<div class="row">
			<div class="col-md-12">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title"><@s.text name="label.store.product.category.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden name="category.id" />
							<@s.textfield key="label.store.product.category.name" name="category.name" value="${(category.name!'')}" />
							
							<div class="form-group">
								<label class="control-label">
										<@s.text name="label.store.product.category.parent" />
								</label>    
								<div class="controls">
									<div class="input-group col-xs-12">
										<select name="parent" class="form-control">
											<option value="null">-- <@s.text name="label.store.combo.category" /> --</option>
											<#list parent.content as r>
												<option value="${r.id!}" <#if category.parent??><#if category.parent.id == r.id>selected</#if></#if>>${r.name!}</option>
											</#list>
										</select>
									</div>
								</div>
							</div>
							
							<@s.textarea rows="2" key="label.store.product.category.description" name="category.description" />
							
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
						</@s.form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>