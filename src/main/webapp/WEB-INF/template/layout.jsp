<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<!DOCTYPE html>
<html>
<head>
	<title>koko :: <t:insertAttribute name="title" ignore="true" /></title>
	
	<base href="/koko/" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap-responsive.min.css" />
	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
</head>
<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a href="/" class="brand">ASUC/GA Funding Request</a>

<%if (request.isUserInRole("ROLE_USER")) { %>
            <ul class="nav pull-right">
                <li><a href="j_spring_security_logout">Logout</a></li>
                <li><a href="j_spring_cas_security_logout">CAS Logout</a></li>
            </ul>
<% } %>
        </div>
    </div>
</div>
	
<t:insertAttribute name="body" />

</body>
</html>