<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <title><g:message code="diary.label"/></title>
    <r:require module="diary"/>
</head>
<body>
    <div class="container">

        <g:if test="${flash.message}">
            <div class="alert alert-info">${flash.message}</div>
        </g:if>

        <g:form class="form-horizontal" action="index" name="createPost">
            <fieldset class="well">
                <g:render template="form"/>
                <br/>
                <button type="submit" class="btn btn-primary pull-right">
                    <i class="icon-ok icon-white"></i>
                    <g:message code="default.button.create.label" default="Create" />
                </button>
            </fieldset>
        </g:form>

        <div class="row">
            <g:each in="${diaryInstanceList}" var="diaryInstance">
                <div class="media span12" data-post-id="${diaryInstance.id}">
                    <div class="media-body">
                        <h4 class="media-heading">
                            <g:link action="show" id="${diaryInstance.id}">
                                ${fieldValue(bean: diaryInstance, field: "title")}
                            </g:link>
                        </h4>
                        <small><g:formatDate date="${diaryInstance.dateTransaction}"/></small>
                        ${diaryInstance.post}
                    </div>
                </div>
            </g:each>

            <div class="pagination">
                <bootstrap:paginate total="${diaryInstanceTotal}"/>
            </div>
        </div>
    </div>
</body>
</html>