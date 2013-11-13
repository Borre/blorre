package com.blorre.LastFm

class LastFmTagLib {
    String youtubeLink = { attrs, body ->
        String convertToQuery = "${attrs.artist} ${attrs.song}".replaceAll(" ", "+")
        String url = "http://www.youtube.com/results?search_query=${convertToQuery}"
        out << url
    }
}
