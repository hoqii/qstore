<html>
	<head>
		<title><@s.text name="page.store.product.title" /></title>
	</head>
	<body>
		<content tag="header"><@s.text name="page.store.product.header" /></content>
		<content tag="script">
		<script type="text/javascript" src="<@s.url value="/scripts/jquery/simple-pagination.js" />"></script>
		<script type="text/javascript">
		$(function() {
			$('#pagination').pagination({
				pages: ${products.totalPages},
				currentPage: ${products.number},
				hrefTextPrefix: '?q=${q!}&max=${max!}&page='
			});
		});
		</script>
		</content>
		<div class="row">
			<div class="col-md-6">
				<a href="<@s.url value="/backend/product/-/edit" />" class="btn btn-default col-md-3"><@s.text name="button.main.add" /></a>
			</div>
			<div class="col-md-6">
				<@s.form theme="bootstrap" method="GET">
					<div class="form-group col-md-10">
						<input name="q" value="${q!}" type="text" class="form-control" placeholder="<@s.text name="button.main.search" />...">
					</div>
					<@s.submit cssClass="btn btn-success col-md-2" value="%{getText('button.main.search')}" />
				</@s.form>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-header">
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th><@s.text name="label.store.product.name" /></th>
										<th><@s.text name="label.store.product.price" /></th>
										<th><@s.text name="label.store.product.uom" /></th>
										<th><@s.text name="label.store.product.category" /></th>
										<th><@s.text name="label.store.main.action" /></th>
									</tr>
								</thead>
								<tbody>
									<#assign no = 1 + ((page - 1) * max) />
									<#list products.content as r>
									<tr>
										<td>${no}</td>
										<td>${r.name}</td>
										<td>${r.price!}</td>
										<td>${r.unitOfMeasure.name!}</td>
										<td>${r.category.name!}</td>
										<td>
											<a class="btn btn-default btn-sm" href="<@s.url value="/backend/product/${r.id}/edit" />"><i class="fa fa-pencil-square-o"></i></a>
											<a class="btn btn-default btn-sm" href="<@s.url value="/backend/product/${r.id}/delete" />"><i class="fa fa-trash-o"></i></a>
										</td>
									</tr>
									<#assign no = no + 1>
									</#list>
								</tbody>
							</table>
						</div>
					</div>
					<div class="box-footer">
						<div class="row">
							<div class="col-md-6">
								<div id="pagination"></div>
							</div>
							<div class="col-md-6">
								<div class="pagination alert pull-right">Found ${products.numberOfElements} entries</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>