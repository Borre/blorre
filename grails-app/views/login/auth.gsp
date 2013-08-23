<html>
<head>
    <title><g:message code="springSecurity.login.title"/></title>
    <r:require module="login"/>
    <r:layoutResources/>
</head>

<body>
<div class="container">
    <form action="${postUrl}" method="POST" id="loginForm" class="form-signin" autocomplete="off">
        <h2 class="form-signin-heading"><g:message code="springSecurity.login.header"/></h2>
        <g:if test='${flash.message}'>
            <div class="alert alert-danger">${flash.message}</div>
        </g:if>
        <input type="text" name="j_username" id="username" class="input-block-level" placeholder="<g:message code="springSecurity.login.username.label"/>">
        <input type="password" name="j_password" id="password" class="input-block-level" placeholder="<g:message code="springSecurity.login.password.label"/>">
        <label class="checkbox">
            <input type="checkbox" value="remember-me" name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>> Remember me
        </label>
        <input type="submit" id="submit" class="btn btn-large btn-primary" value="${message(code: "springSecurity.login.button")}">
    </form>

</div>
</body>
</html>
