package com.blorre.LastFm

import com.blorre.calendar.BlorreCalendar
import com.blorre.config.GlobalConfiguration

import java.text.DateFormat
import java.text.SimpleDateFormat

class LastFmService {

    private LastFm lastSong

    public void readFeed() {
        String lastFmHead = "http://ws.audioscrobbler.com/1.0/user/"
        GlobalConfiguration conf = GlobalConfiguration.first()
            lastSong = LastFm.last()
            println(lastSong.streamingDate)
            URL url = (lastFmHead + conf.lastFmUser + "/recenttracks.rss").toURL()
            Node feed = new XmlParser().parseText(url.text)

            Date lastRecord = lastFmDateFormat(feed.channel.item[0].pubDate.text())

            if (lastRecord != lastSong?.streamingDate) {
                feed.channel.item.each {
                    String title = it.title.text()
                    String playDate = it.pubDate.text()
                    compareSongs(title, playDate)
                }
            }

    }

    private void compareSongs(String title, String playDate) {
        String[] artistAndTitle = title.split("–")
        if (artistAndTitle.size() >= 2) {
            String artist = artistAndTitle[0]?.trim()
            String song = artistAndTitle[1]?.trim()
            Date dateSong = lastFmDateFormat(playDate)

            if (artist == lastSong?.artist && song == lastSong?.song) {
                dateSong.after(lastSong?.streamingDate) ?: saveSong(artist, song, dateSong)
            } else {
                saveSong(artist, song, dateSong)
            }
        } else {
            log.error("LastFm -> Error parsing song:" + artistAndTitle)
        }
    }

    private static saveSong(String artist, String song, Date playDate) {
        LastFm finder = LastFm.findByArtistAndSongAndStreamingDate(artist, song, playDate)
        if (!finder) {
            LastFm currentSong = new LastFm()
            currentSong.artist = artist
            currentSong.song = song
            currentSong.streamingDate = playDate
            BlorreCalendar day = BlorreCalendar.findOrCreateByDayToTrack(new Date().clearTime())
            day.addToSongs(currentSong)
            currentSong.save(flush: true)
        }
    }

    private static Date lastFmDateFormat(String date) {
        DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz")
        return format.parse(date)
    }
}
