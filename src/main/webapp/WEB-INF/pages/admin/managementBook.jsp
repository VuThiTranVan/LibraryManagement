<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Management book 
 * vu.thi.tran.van@framgia.com
 * 17/05/2017
 -->
<section class="bg_white clearfix messageError">
	<div class="body clearfix mt20 manageUser" id="messageContainer">
	</div>
</section>
<label id="mgsNoResult" class = "hidden_elem"><spring:message code='no_find_result_search' text='' /></label>
<section class="bg_white clearfix">
	<div class="body clearfix mt20 manageUser">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Search area</h3>
			</div>
			<div class="panel-body">
				<form role="form" id="searchForm" action=""
					method="post">
					<div class="form-group form-group-lg">
						<div class="col-sm-6">
							<label>Name Book:</label>
							<input class="form-control" id="name"
								name="name" type="text" placeholder="please input book code or book name">
						</div>
						<div class="col-sm-6">
							<label>Categories:</label>
							<select id="categoryId"
								name="categoryId" class="form-control">
								<option value="0" selected="selected"></option>
								<c:forEach items="${listCategory}" var="catLevel1">
									<c:if test="${catLevel1.catSubId == 0}">
										<optgroup label="${catLevel1.name}">
											<c:forEach items="${listCategory}" var="catLevel2">
												<c:if test="${catLevel2.catSubId==catLevel1.categoriesId }">
													<option value="${catLevel2.categoriesId}">${catLevel2.name}</option>
												</c:if>
											</c:forEach>
										</optgroup>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>
					<div id="sub_btn">
						<input type="button" onclick="submitClear()" value="Clear"
							class="btn btn-defaul">
						<input type="button" 
							value="Search" id="btn_seach" class="btn">
						<input type="button" id="btn_download" name="download"
							value="Report CSV" class="btn">
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
										<th>Book Code</th>
										<th>Name</th>
										<th>Category</th>
										<th>Publishers</th>
										<th>Status</th>
										<th>Total quantity</th>
										<th>Total borrowed</th>
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
