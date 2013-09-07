<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin</title>
    <meta name="layout" content="bootstrap">
</head>
<body>
    <div class="container">
        <g:each in="${calendar}" var="day">
            <div class="media">
                <div class="media-body">
                    <h2 class="media-heading">${day.dayToTrack.format("dd/MM/yyyy")}</h2>
                    <b>Diary Posts:</b>
                    <g:each in="${day.diaryEntrys}" var="entry">
                        <div class="media">
                            <h3>${entry.title}</h3>
                            <small> at ${entry.dateTransaction.format("hh:mm:ss")}</small>
                            <div class="media-body">
                                ${entry.post.take(300)}
                                <g:createLink controller="diary" action="show" id="${entry.id}">See more</g:createLink>
                            </div>
                        </div>
                    </g:each>
                    <b>Ideas:</b>
                    <g:each in="${day.ideas}" var="idea">
                        <h3>${idea.title}</h3>
                        <small> at ${idea.dateTransaction.format("hh:mm:ss")}</small>
                        <div class="media-body">
                            ${idea.idea.take(300)}
                            <g:createLink controller="ideas" action="show" id="${idea.id}">See more</g:createLink>
                        </div>
                    </g:each>
                </div>
            </div>
        </g:each>
    </div>
</body>
</html>