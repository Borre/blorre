<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>LastFM</title>
    <meta name="layout" content="bootstrap">
</head>
<body>
    <div class="container">
        <ul class="nav nav-pills">
            <li><a href="<g:createLink action="execute"/>">Refresh data</a></li>
        </ul>
        <table class="table table-striped">
            <thead>
                <tr>
                    <g:sortableColumn property="artist" title="${message(code: 'lastFm.artist', default: 'Artist')}" />

                    <g:sortableColumn property="song" title="${message(code: 'lastFm.song', default: 'Song')}" />

                    <g:sortableColumn property="streamingDate" title="${message(code: 'lastFm.date', default: 'Date')}" />

                    <th></th>
                </tr>
            </thead>
            <tbody>
            <g:each in="${lastFmInstanceList}" var="lastFmInstance">
                <tr>
                    <td>${fieldValue(bean: lastFmInstance, field: "artist")}</td>

                    <td>${fieldValue(bean: lastFmInstance, field: "song")}</td>

                    <td><g:formatDate date="${lastFmInstance.streamingDate}" format="yyyy/MM/dd HH:mm:s"/></td>

                    <td class="link">
                        <a href="<g:youtubeLink artist="${lastFmInstance?.artist}" song="${lastFmInstance?.song}"/>" target="_blank">Show &raquo;</a>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <div class="pagination">
            <bootstrap:paginate total="${lastFmInstanceTotal}" />
        </div>
    </div>
</body>
</html>