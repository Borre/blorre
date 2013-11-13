package com.blorre.diary

import com.blorre.calendar.BlorreCalendar
import com.blorre.security.User
class Diary {

    String title
    String post
    Date dateTransaction = new Date()

    static belongsTo = [
            day: BlorreCalendar
    ]

    static mapping = {
        sort(dateTransaction: "desc")
        post(type: "text")
    }

    static constraints = {
        post nullable: false
        dateTransaction nullable: false
        day nullable: true
    }
}
