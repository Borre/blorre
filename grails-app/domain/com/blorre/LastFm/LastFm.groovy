package com.blorre.LastFm

import com.blorre.calendar.BlorreCalendar

class LastFm {

    String artist
    String song
    Date streamingDate = new Date()

    static belongsTo = [day: BlorreCalendar]

    static constraints = {
        artist(nullable: false, blank: false)
        song(nullable: false, blank: false)
        day(nullable: false)
    }

    static mapping = {
        sort(streamingDate: "desc")
    }
}
