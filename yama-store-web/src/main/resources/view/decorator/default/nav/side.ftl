			<aside class="left-side sidebar-offcanvas">
				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">
					<!-- Sidebar user panel -->
					<div class="user-panel">
						<div class="pull-left image">
							<!--
							<img src="img/avatar3.png" class="img-circle" alt="User Image" />
							-->
						</div>
						<div class="pull-left info">
							<p>Hello, <#if currentUser.name??>${currentUser.name.first!} ${currentUser.name.last!}<#else>${currentUser.username}</#if></p>

							<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
						</div>
					</div>
					
					<!-- sidebar menu: : style can be found in sidebar.less -->
					<ul class="sidebar-menu">
						<li class="active">
							<a href="<@s.url value="/dashboard" />">
								<i class="fa fa-dashboard"></i> <span><@s.text name="menu.main.dashboard" /></span>
							</a>
						</li>
						<#if isAdmin>
						<li class="treeview">
							<a href="#">
								<i class="fa fa-gears"></i>
								<span><@s.text name="menu.main.admin" /></span>
								<i class="fa fa-angle-left pull-right"></i>
							</a>
							<ul class="treeview-menu">
								<li><a href="<@s.url value="/admin/users" />"><i class="fa fa-angle-double-right"></i> <@s.text name="menu.main.admin.user" /></a></li>
								<li><a href="<@s.url value="/admin/roles" />"><i class="fa fa-angle-double-right"></i> <@s.text name="menu.main.admin.role" /></a></li>
							</ul>
						</li>
						<li class="active">
							<a href="<@s.url value="/applications" />">
								<i class="fa fa-cube"></i> <span> <@s.text name="menu.main.application" /></span>
							</a>
						</li>
						</#if>
						<#if (currentRoles?size > 0)>
							<#if currentRoles[0] == "QSADMIN">
								<li class="treeview">
									<a href="#">
										<i class="fa fa-archive"></i>
										<span><@s.text name="menu.main.qstore.product" /></span>
										<i class="fa fa-angle-left pull-right"></i>
									</a>
									<ul class="treeview-menu">
										<li><a href="<@s.url value="/backend/product" />"><i class="fa fa-angle-double-right"></i> <@s.text name="menu.sub.product" /></a></li>
										<li><a href="<@s.url value="/backend/product_category" />"><i class="fa fa-angle-double-right"></i> <@s.text name="menu.sub.product.category" /></a></li>
										<li><a href="<@s.url value="/backend/uom" />"><i class="fa fa-angle-double-right"></i> <@s.text name="menu.sub.uom" /></a></li>
									</ul>
								</li>
							</#if>
						</#if>
					</ul>
				</section>
				<!-- /.sidebar -->
			</aside>