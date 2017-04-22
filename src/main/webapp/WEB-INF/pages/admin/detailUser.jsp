<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Management users 
 * vu.thi.tran.van@framgia.com
 * 18/04/2017
 -->
<c:choose>
	<c:when test="${not empty user.userId}">
	   <spring:url value="/managementUsers/update" var="userActionUrl" />
                        <form:form id="updateForm" class="form-horizontal" method="post"
                            action="${userActionUrl}" modelAttribute="user">
		<section class="pb50">
			<div class="body clearfix mt20 manageUser">
				<div class="panel panel-default">
					<div class="panel-heading detail-user">
						<div class="detail-user-head-left">
							<h3 class="panel-title">Imfomation detail user</h3>
						</div>
						<div class="detail-user-head-right">
							<a  class="lableForm" href="#" onclick="clickBtnEdit()"><input type="button" value="Edit"
								class="btn btn-detail"></a>
							<a id="save" class="editForm hidden_elem" href="#">
							     <button id="register" type="submit" class="btn btn-detail" >Submit</button>
							</a>
                                <a class="editForm hidden_elem" href="#" onclick="clickBtnCancel()"><input type="button" value="cancel"
                                class="btn btn-detail"></a>
						</div>
					</div>
					<!-- /.panel-heading -->

					<div class="panel-body">
						
							<table class="table-bordered profile_regist">
								<tr>
									<th>Id user</th>
									<td><label>${user.userId}</label>
                                        <form:label path="userId"
                                                name="userId" id="userId" class="hidden_elem"
                                                placeholder="Please input text."/>
                                                <form:label path="dateUpdate"
                                                name="dateUpdate" id="dateUpdate" class="hidden_elem"
                                                placeholder="Please input text." />
                                    </td>
								</tr>
								<tr>
									<th>User name</th>
									<td><label>${user.userName}</label></td>
								</tr>
								<tr>
									<th>Full name</th>

									<td><span class="lableForm"> <label>${user.name}</label>
									</span> <span class="editForm hidden_elem"> <form:input path="name"
												name="name" id="name" class="form-control"
												placeholder="Please input text."
												style="display: inline; width: 65%;" /> <font class="red">※</font>
									</span></td>
								</tr>
								<tr>
									<th>Permissions</th>
									<td><span class="lableForm"> <label>${user.permissionsName}</label>
									</span> <span class="editForm hidden_elem"> 
									<form:select
												path="permissionsName" id="permissionsName"
												name="permissionsName" class="form-control"
												style="display: inline; width: 65%;">
												<c:forEach items="${permissionInfo}" var="per">
												    <c:choose>
													  <c:when test="${per.permissionName != permissionsName}">
													    <form:option value="${per.permissionsId}" selected="true">${per.permissionName}</form:option>
													  </c:when>
													  <c:otherwise>
													    <form:option value="${per.permissionsId}">${per.permissionName}</form:option>
													  </c:otherwise>
													</c:choose>
												    
												</c:forEach>
											</form:select> <font class="red">※</font>
									</span></td>
								</tr>
								<tr>
									<th>Birthday</th>
									<td><span class="lableForm"> <label>${user.birthDate}</label>
									</span> <span class="editForm hidden_elem"> <form:input path="birthDate"
												name="birthDate" id="birthDate" class="form-control"
												placeholder="Please input text."
												style="display: inline; width: 65%;" /> <font class="red">※</font>
									</span></td>
								</tr>
								<tr>
									<th>Address</font>
									</th>
									<td><span class="lableForm"> <label>${user.address}</label>
									</span> <span class="editForm hidden_elem"> <form:input path="address"
												name="address" id="address" class="form-control"
												placeholder="Please input text."
												style="display: inline; width: 65%;" /> <font class="red">※</font>
									</span></td>
								</tr>
								<tr>
									<th>Phone number</font>
									</th>
									<td><span class="lableForm"> <label>${user.phone}</label>
									</span> <span class="editForm hidden_elem"> <form:input path="phone"
												name="phone" id="phone" class="form-control"
												placeholder="Please input text."
												style="display: inline; width: 65%;" /> <font class="red">※</font>
									</span></td>
								</tr>
								<tr>
									<th>Gender</font>
									</th>
									<td><span class="lableForm"> <label>${user.sex}</label>
									</span> <span class="editForm hidden_elem">
									   <c:if test="${user.sex == 'Fmale' }">
									 <form:radiobutton path="sex"
                                                name="sex" id="fmale" value="0" checked="true"/> Fmale
                                                <form:radiobutton
                                                path="sex" name="sex" id="male" value="1"/>Male
									   </c:if>
									   <c:if test="${user.sex == 'Male' }">
									   <form:radiobutton path="sex"
                                                name="sex" id="fmale" value="0"/> Fmale
                                                <form:radiobutton
                                                path="sex" name="sex" id="male" value="1" checked="true"/>Male
									   </c:if>
									   
									</span></td>
								</tr>
								<tr>
									<th>Email</font>
									</th>
									<td><span class="lableForm"> <label>${user.email}</label>
									</span><span class="editForm hidden_elem"> <form:input path="email"
												name="email" id="email" class="form-control"
												placeholder="Please input text."
												style="display: inline; width: 65%;" /> <font class="red">※</font>
									</span></td>
								</tr>
								<tr>
									<th>Date create</font>
									</th>
									<td><label>${user.dateCreate}</label></td>
								</tr>
								<tr>
									<th>User create</font>
									</th>
									<td><label>${user.userCreate}</label></td>
								</tr>
								<tr>
									<th>Date update</font>
									</th>
									<td><label>${user.dateUpdate}</label></td>
								</tr>
								<tr>
									<th>User update</font>
									</th>
									<td><label>${user.userUpdate}</label></td>
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
</c:choose>
<script src="${pageContext.request.contextPath}/assets/js/customize-Admin/manageUser.js"></script>