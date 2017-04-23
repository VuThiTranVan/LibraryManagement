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
<c:choose>
	<c:when test="${not empty user.userName }">
		<spring:url value="/managementUsers/update" var="userActionUrl" />
		<form:form id="updateForm" class="form-horizontal" method="post"
			action="${userActionUrl}" modelAttribute="user">
			<form:input path="userId" name="userId" id="userId"
				class="hidden_elem" />
			<form:input path="dateUpdate" name="dateUpdate" id="dateUpdate"
				class="hidden_elem" />
				
			<section class="pb50">
				<div class="body clearfix mt20 manageUser">
					<div class="panel panel-default">
						<div class="panel-heading detail-user">
							<div class="detail-user-head-left">
								<h3 class="panel-title">Imfomation detail user</h3>
							</div>
							<div class="detail-user-head-right">
								<button onclick="clickBtnEdit()" id="editbtn" type="button"
									class="btnScrUser lableForm btn btn-detail">Edit</button>
								<button id="savebtn" type="submit"
									class="btnScrUser editForm hidden_elem btn btn-detail">Save</button>
								<button onclick="clickBtnCancel()" id="cancelbtn" type="button"
									class="btnScrUser editForm hidden_elem btn btn-detail">Cancel</button>
							</div>
						</div>
						<!-- /.panel-heading -->

						<div class="panel-body">

							<table class="table-bordered profile_regist">
								<tr>
									<th>Id user</th>
									<td><label>${user.userId}</label></td>
								</tr>
								<tr>
									<th>User name</th>
									<td><label>${user.userName}</label></td>
								</tr>
								<tr>
									<th>Full name</th>

									<td><span id="lblName" class="lableForm"><label>${user.name}</label></span>
										<span class="editForm hidden_elem"> <form:input
												path="name" name="name" id="name"
												class="form-control css-required"
												placeholder="This is item required"
												style="display: inline; width: 65%;" />
									</span></td>
								</tr>
								<tr>
									<th>Permissions</th>
									<td><span id="lblPermissionsName" class="lableForm"><label>${user.permissions.permissionName}</label></span>
										<span class="editForm hidden_elem"> <form:select
												path="permissions.permissionsId" id="permissionsName"
												name="permissionsName" class="form-control css-required"
												style="display: inline; width: 65%;">
												<c:forEach items="${permissionInfo}" var="per">
													<c:choose>
														<c:when
															test="${user.permissions.permissionName == per.permissionName}">
															<form:option value="${per.permissionsId}" selected="true">${per.permissionName}</form:option>
														</c:when>
														<c:otherwise>
															<form:option value="${per.permissionsId}">${per.permissionName}</form:option>
														</c:otherwise>
													</c:choose>

												</c:forEach>
											</form:select>
									</span></td>
								</tr>
								<tr>
									<th>Birthday</th>
									<td><span id="lblBirthDate" class="lableForm"><label>${user.birthDate}</label></span>
										<span class="editForm hidden_elem">
											<form:input
												path="birthDate" name="birthDate" id="birthDate"
												class="form-control css-required birthday_picker"
												placeholder="This is item required"
												style="display: inline; width: 65%;"/>
									</span></td>
								</tr>
								<tr>
									<th>Address</th>
									<td><span id="lblAddress" class="lableForm"><label>${user.address}</label></span>
										<span class="editForm hidden_elem"> <form:input
												path="address" name="address" id="address"
												class="form-control css-required"
												placeholder="This is item required"
												style="display: inline; width: 65%;" />
									</span></td>
								</tr>
								<tr>
									<th>Phone number</th>
									<td><span id="lblPhone" class="lableForm"><label>${user.phone}</label></span>
										<span class="editForm hidden_elem"> <form:input
												path="phone" name="phone" id="phone"
												class="form-control css-required"
												placeholder="This is item required"
												style="display: inline; width: 65%;" />
									</span></td>
								</tr>
								<tr>
									<th>Gender</th>
									<td><span id="lblSex" class="lableForm"><label>${user.sex}</label></span>
										<span class="editForm hidden_elem"> <c:if
												test="${user.sex == 'Fmale' }">
												<form:radiobutton path="sex" name="sex" id="fmale" value="0"
													checked="true" /> Fmale
                                                <form:radiobutton
													class="css-required" path="sex" name="sex" id="male"
													value="1" />Male
									   </c:if> <c:if test="${user.sex == 'Male' }">
												<form:radiobutton path="sex" name="sex" id="fmale" value="0" /> Fmale
                                                <form:radiobutton
													class="css-required" path="sex" name="sex" id="male"
													value="1" checked="true" />Male
									   </c:if>

									</span></td>
								</tr>
								<tr>
									<th>Email</th>
									<td><span id="lblEmail" class="lableForm"><label>${user.email}</label></span>
										<span class="editForm hidden_elem"> <form:input
												path="email" name="email" id="email"
												class="form-control css-required"
												placeholder="This is item required"
												style="display: inline; width: 65%;" />
									</span></td>
								</tr>
								<tr>
									<th>Date create</th>
									<td><label>${user.dateCreate}</label></td>
								</tr>
								<tr>
									<th>User create</th>
									<td><label>${user.userCreate}</label></td>
								</tr>
								<tr>
									<th>Date update</th>
									<td>
									<span id="lblDateUpdate"><label>${user.dateUpdate}</label></span>
									</td>
								</tr>
								<tr>
									<th>User update</th>
									<td><span id="lblUserUpdate"><label>${user.userUpdate}</label></span>
									</td>
								</tr>
							</table>

						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div id="sub_btn">
					<a href="/SpringSecurity/home"><input type="button"
						value="Back home" class="btn-forwardscreen"></a>
				</div>
			</section>
		</form:form>
	</c:when>
	<c:when test="${empty user.userName}">
		<section class="bg_white clearfix messageError">
			<div class="body clearfix mt20 manageUser" id="messageContainer">
				 <spring:message code="no_find_info_detail" text="default text" />
			</div>
		</section>
	</c:when>
</c:choose>
