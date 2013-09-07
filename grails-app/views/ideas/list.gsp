<%@ page import="com.blorre.ideas.Ideas" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'ideas.label', default: 'Ideas')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
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
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
                    <div class="alert alert-info">${flash.message}</div>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
                            <g:sortableColumn property="title" title="${message(code: 'ideas.title.label', default: 'Title')}" />

							<g:sortableColumn property="idea" title="${message(code: 'ideas.idea.label', default: 'Idea')}" />

                            <g:sortableColumn property="day" title="${message(code: "ideas.dateTransaction.label", default: "Day")}"/>
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${ideasInstanceList}" var="ideasInstance">
						<tr>
                            <td>${fieldValue(bean: ideasInstance, field: "title")}</td>

							<td>${fieldValue(bean: ideasInstance, field: "idea").take(100)}</td>

                            <td><g:formatDate date="${ideasInstance.dateTransaction}" format="yyyy/MM/dd"/></td>
						
							<td class="link">
								<g:link action="show" id="${ideasInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${ideasInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
