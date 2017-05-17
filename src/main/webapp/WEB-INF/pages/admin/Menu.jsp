<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

			<div class="navbar-header collapse navbar-collapse menu-admin" >
				<a href="${pageContext.request.contextPath}/managementBook"><button type="button" class="btn">Manage Book</button></a>
				<a href="${pageContext.request.contextPath}/managementUsers"><button type="button" class="btn">Manage users</button></a>
				<a href="${pageContext.request.contextPath}/managementBorrowed"><button type="button" class="btn">Manage Borrowed Book</button></a>
				<a href="${pageContext.request.contextPath}/manageImportData"><button type="button" class="btn">Import Data</button></a>
				<button type="button" class="btn">manage categories</button>
				<button type="button" class="btn">Manage publishers</button>
				<button type="button" class="btn">manage Author</button>
			</div>