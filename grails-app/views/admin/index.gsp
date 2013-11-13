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
                    <h2>Happen this day: ${day.dayToTrack.format("dd/MM/yyyy")}</h2>
                    <g:if test="${day?.diaryEntrys}">
                        <b>Diary Posts:</b>
                        <g:each in="${day?.diaryEntrys}" var="entry">
                            <div class="media">
                                <h3>${entry.title}</h3>
                                <small> at ${entry.dateTransaction.format("hh:mm:ss")}</small>
                                <div class="media-body">
                                    ${entry.post.take(300)}
                                    <a href="<g:createLink controller="diary" action="show" id="${entry.id}"/>">See more</a>
                                </div>
                            </div>
                        </g:each>
                    </g:if>
                    <g:if test="${day?.ideas}">
                        <b>Ideas:</b>
                        <g:each in="${day?.ideas}" var="idea">
                            <h3>${idea.title}</h3>
                            <small> at ${idea.dateTransaction.format("hh:mm:ss")}</small>
                            <div class="media-body">
                                ${idea.idea.take(300)}
                                <a href="<g:createLink controller="ideas" action="show" id="${idea.id}"/>">See more</a>
                            </div>
                        </g:each>
                    </g:if>
                    <g:if test="${day?.songs}">
                        <b>Songs:</b>
                        <div class="media-body">
                            <ul>
                                <g:each in="${day?.songs}" var="song">
                                    <li>
                                        <a href="<g:youtubeLink artist="${song?.artist}" song="${song?.song}"/>" target="_blank">
                                            ${song?.artist} - ${song?.song}
                                        </a>
                                    </li>
                                </g:each>
                            </ul>
                        </div>
                    </g:if>
                </div>
            </div>
        </g:each>
    </div>
</body>
</html>