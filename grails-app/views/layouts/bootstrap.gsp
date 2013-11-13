<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
		<meta name="author" content="Eduardo Hernández">
        <meta name="description" content="Blorre - life management sistem">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<r:require modules="scaffolding"/>
		<g:layoutHead/>
		<r:layoutResources/>
	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="<g:createLink uri="/"/>">${meta(name: 'app.name')}</a>
					<div class="nav-collapse">
						<ul class="nav">
                            <%
                                 def allowedControllers = [
                                         'Diary',
                                         'Ideas',
                                         'LastFm',
                                         'Users'
                                 ]
                             %>
							<li<%= request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/admin')}">Home</a></li>
							<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                                <g:if test="${c.name in allowedControllers}">
								    <li<%= c.logicalPropertyName == controllerName ? ' class="active"' : '' %>><g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
                                </g:if>
							</g:each>
                            <li><a href="${createLink(uri: '/logout')}">Logout</a></li>
						</ul>
                        <sec:ifLoggedIn>
                        <p class="navbar-text pull-right">
                            Logged in as <a href="#" class="navbar-link"><sec:loggedInUserInfo field="username"/></a>
                        </p>
                        </sec:ifLoggedIn>
					</div>
				</div>
			</div>
		</nav>

		<div class="container-fluid">
			<g:layoutBody/>
			<hr>
			<footer style="text-align: center">
				<p>&copy; Eduardo Hernández 2013</p>
			</footer>
		</div>
		<r:layoutResources/>
	</body>
</html>