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
				<spring:url value="/managementBorrowed/search" var="searchActionUrl" />
				<form role="form" id="searchForm" action="${searchActionUrl}"
					method="post">
					<div class="form-group form-group-lg">
						<div class="col-sm-6">
							<label>Borrowed code</label><input class="form-control" id="txtBorrowedCode"
								name="txtBorrowedCode" type="text" placeholder="please input text">
						</div>
						<div class="col-sm-6">
							<label>Status borrowed</label>
							<select id="txtStatus"
								name="txtStatus" class="form-control">
								<option value="" selected="selected"></option>
								<option value="1">Request</option>
								<option value="2">Accept</option>
								<option value="3">Cancel</option>
								<option value="4">Borrowed</option>
								<option value="5">Finish</option>
							</select>
						</div>
						<div class="clearfix" style="padding: 10px;"></div>
						<div class="col-sm-6">
							<label>Day intends to borrow  <b>:</b>  Day intends to payment</label>
							<div class="col-sm-6 intenDateBor" style="padding-left: 0px !important;">
								<input class="form-control" id="txtIntenDateBor"
									name="txtIntenDateBor" type="text" placeholder="please input date">
							</div>
							<div class="col-sm-6" style="padding-right: 0px !important;">
								<input class="form-control" id="txtIntenDatePay"
									name="txtIntenDatePay" type="text" placeholder="please input date">
							</div>
						</div>
						<div class="col-sm-6">
							<label>Day intends to borrow  <b>:</b>  Day intends to payment</label>
							<div class="col-sm-6" style="padding-left: 0px !important;">
								<input class="form-control" id="txtDateBor"
									name="txtDateBor" type="text" placeholder="please input date">
							</div>
							<div class="col-sm-6" style="padding-right: 0px !important;">
								<input class="form-control" id="txtDatePay"
									name="txtDatePay" type="text" placeholder="please input date">
							</div>
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
										<th>Borrowed code</th>
										<th>Username</th>
										<th>Email</th>
										<th>Intend borrowed</th>
										<th>Intend payment</th>
										<th>Borrowed</th>
										<th>Payment</th>
										<th>Status</th>
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