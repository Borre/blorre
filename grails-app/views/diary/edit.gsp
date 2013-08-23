<%@ page import="org.springframework.validation.FieldError; com.blorre.diary.Diary" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'diary.label', default: 'Diary')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
        <r:require module="diary"/>
	</head>
	<body>
        <div class="container">

            <g:if test="${flash.message}">
            <div class="alert alert-info">${flash.message}</div>
            </g:if>

            <g:hasErrors bean="${diaryInstance}">
            <div class="alert alert-error">
            <ul>
                <g:eachError bean="${diaryInstance}" var="error">
                <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </div>
            </g:hasErrors>
            <g:form class="form-horizontal" action="edit" id="${diaryInstance?.id}" >
                <fieldset class="well">
                    <g:hiddenField name="version" value="${diaryInstance?.version}" />
                    <g:render template="form"/>
                    <br/>
                    <button type="submit" class="btn btn-primary">
                        <i class="icon-ok icon-white"></i>
                        <g:message code="default.button.update.label" default="Update" />
                    </button>
                    <button type="submit" class="btn btn-danger" name="_action_delete" formnovalidate>
                        <i class="icon-trash icon-white"></i>
                        <g:message code="default.button.delete.label" default="Delete" />
                    </button>
                </fieldset>
            </g:form>
        </div>
	</body>
</html>
