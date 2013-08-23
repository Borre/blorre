<%@ page import="com.blorre.diary.Diary" %>

<div class="fieldcontain ${hasErrors(bean: diaryInstance, field: 'title', 'error')} ">
    <label for="title">
        <g:message code="diary.title.label" default="Title" />

    </label>
    <g:textField name="title" value="${diaryInstance?.title}" />
</div>

<div class="fieldcontain ${hasErrors(bean: diaryInstance, field: 'post', 'error')} ">
	<label for="post">
		<g:message code="diary.post.label" default="Post" />
	</label>

    <g:textArea name="post" value="${diaryInstance?.post}" class="ckeditor"/>
</div>
