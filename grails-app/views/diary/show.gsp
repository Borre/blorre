
<%@ page import="com.blorre.diary.Diary" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'diary.label', default: 'Diary')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
        <div class="container">
            <g:if test="${flash.message}">
                <div class="alert alert-info">${flash.message}</div>
            </g:if>
            <div class="media span12" data-post-id="${diaryInstance.id}">
                <div class="media-body">
                    <h4 class="media-heading">
                            ${fieldValue(bean: diaryInstance, field: "title")}
                    </h4>
                    <small><g:formatDate date="${diaryInstance.dateTransaction}"/></small>
                    ${diaryInstance.post}
                </div>
                <g:form>
                    <g:hiddenField name="id" value="${diaryInstance?.id}" />
                    <g:link class="btn" action="edit" id="${diaryInstance?.id}">
                        <i class="icon-pencil"></i>
                        <g:message code="default.button.edit.label" default="Edit" />
                    </g:link>
                    <button class="btn btn-danger" type="submit" name="_action_delete">
                        <i class="icon-trash icon-white"></i>
                        <g:message code="default.button.delete.label" default="Delete" />
                    </button>
                </g:form>
            </div>
        </div>
	</body>
</html>
