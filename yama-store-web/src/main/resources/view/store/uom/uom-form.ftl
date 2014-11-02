<html>
	<head>
		<title><@s.text name="page.store.uom.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.store.uom.header" /></content>
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
						<h3 class="box-title"><@s.text name="label.store.uom.title" /></h3>
					</div>
					
					<div class="box-body">
						<@s.form theme="bootstrap">
							<@s.hidden name="uom.id" />
							<@s.textfield key="label.store.uom.name" name="uom.name" value="${(uom.name!'')}" />
							<@s.textarea rows="2" key="label.store.uom.description" name="uom.description" />
							
							<@s.submit cssClass="btn btn-primary col-md-3" value="%{getText('button.main.save')}" />
						</@s.form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>