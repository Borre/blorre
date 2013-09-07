<%@ page import="com.blorre.ideas.Ideas" %>

<div class="control-group ${hasErrors(bean: ideasInstance, field: 'title', 'error')} ">
    <label for="title" class="control-label">
        <g:message code="ideas.title.label" default="Title"/>

    </label>
    <div class="controls">
        <g:textField name="title" value="${ideasInstance?.title}" required="true"/>
    </div>
</div>

<div class="control-group ${hasErrors(bean: ideasInstance, field: 'idea', 'error')} ">
    <label for="idea" class="control-label">
        <g:message code="ideas.idea.label" default="Idea"/>
    </label>
    <div class="controls">
        <g:textArea name="idea" value="${ideasInstance?.idea}" style="width: 60%; height: 200px"/>
    </div>
</div>
