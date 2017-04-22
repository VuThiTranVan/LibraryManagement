<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Management users 
 * vu.thi.tran.van@framgia.com
 * 18/04/2017
 -->
<section class="bg_white clearfix messageError">
	<div class="body clearfix mt20 manageUser" id="messageContainer">
	</div>
</section>
<section class="bg_white clearfix">
	<div class="body clearfix mt20 manageUser">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Search area</h3>
			</div>
			<div class="panel-body">
				<spring:url value="/managementUsers/search" var="searchActionUrl" />
				<form role="form" id="searchForm" action="${searchActionUrl}"
					method="post">
					<div class="form-group form-group-lg">
						<div class="col-sm-6">
							<label>Name</label> <input class="form-control" id="txtName"
								name="txtName" type="text" placeholder="please input text">
						</div>
						<div class="col-sm-6">
							<label>Permission</label> <select id="txtPermission"
								name="txtPermission" class="form-control">
								<option value="0" selected="selected"></option>
								<c:forEach items="${permissionInfo}" var="per">
									<option value="${per.permissionsId}">${per.permissionName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>
					<div id="sub_btn">
						<input type="button" onclick="submitClear()" value="Clear"
							class="btn btn-defaul"> <input type="submit"
							value="Search" class="btn">
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
</section>
<section class="pb50">
	<div id="resultSearch" class="hidden_elem">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">List result</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-result" width="100%">
								<thead>
									<tr>
										<th>Username</th>
										<th>Name</th>
										<th>Birthdate</th>
										<th>Address</th>
										<th>Email</th>
										<th>Sex</th>
										<th>Phone</th>
										<th>Permission</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
	</div>

	<div class="clearfix"></div>
	<div id="sub_btn">
		<a href="/SpringSecurity/home" class="btn btn-detail"><input
			type="button" value="BACK HOME" class="btn-forwardscreen"></a>
	</div>
</section>
<script src="${pageContext.request.contextPath}/assets/js/customize-Admin/manageUser.js"></script>

