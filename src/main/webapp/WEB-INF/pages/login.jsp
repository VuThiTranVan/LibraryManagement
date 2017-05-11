<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.vertical-offset-100 {
	padding-top: 150px;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>
	<div class="container">
		<div class="row vertical-offset-100">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="top-login">
						<div class="error-login"></div>
						<c:if test="${not empty error}">
							<div class="error-login">
								<font color="red">${error}</font>
							</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="logout-msg">
								<font color="red">${msg}</font>
							</div>
						</c:if>
					</div>
					<div class="panel-heading">
						<center>
							<h3 class="panel-title">Please sign in</h3>
						</center>
					</div>
					<div class="panel-body">
						<form name='loginForm' action="<c:url value='/login' />"
							method='POST'>
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="User Name"
										name="username" type="text">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="">
								</div>
								<div class="checkbox">
									<label> <input type='checkbox' name="remember-me-param" />Remember Me? <br />
									</label>
								</div>
								<center>
									<input class="btn-forwardscreen" name="submit" type="submit"
										value="Login">
								</center>
								<center>
									<button type="button" class="btn-default btn-css-link"
										id="myBtn">Forgot my password</button>
								</center>
							</fieldset>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal _ dialog input email -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header cus-modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">X</button>
						<h1 class="text-center">WHAT'S MY PASSWORD?</h1>
					</div>
					<div class="modal-body">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="text-center">
									<h3>If you have forgotten your password you can reset it
										here.</h3>
									<div class="panel-body">
										<section class="bg_white clearfix messageError">
											<div id="messageContainer">
												${messageForgot}
											</div>
										</section>
										<form role="form" id="forgotForm" 
											action="<c:url value='/forgotPassword' />" method='POST'>
											<fieldset>
												<div class="form-group">
													<input class="form-control input-lg css-required"
														placeholder="E-mail Address" id="email" name="email">
												</div>
												<button type="submit" id="forgotPassword" class="btn-forwardscreen">
													Send My Password
												</button>
											</fieldset>
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="text-align-right">
							<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
