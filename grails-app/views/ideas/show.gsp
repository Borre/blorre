
<%@ page import="com.blorre.ideas.Ideas" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'ideas.label', default: 'Ideas')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
                    <div class="alert alert-info">${flash.message}</div>
				</g:if>

				<dl>

                    <g:if test="${ideasInstance?.title}">
                        <dt><g:message code="ideas.title.label" default="Title" /></dt>

                        <dd><g:fieldValue bean="${ideasInstance}" field="title"/></dd>
                    </g:if>

                    <g:if test="${ideasInstance?.dateTransaction}">
                        <dt><g:message code="ideas.dateTransaction.label" default="Date" /></dt>

                        <dd><g:formatDate date="${ideasInstance?.dateTransaction}" format="yyyy/MM/dd"/></dd>
                    </g:if>
				
					<g:if test="${ideasInstance?.idea}">
						<dt><g:message code="ideas.idea.label" default="Idea" /></dt>
						
					    <dd>${ideasInstance?.idea?.encodeAsHTML()?.replace('\n', '<br> \n')}</dd>
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${ideasInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${ideasInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
