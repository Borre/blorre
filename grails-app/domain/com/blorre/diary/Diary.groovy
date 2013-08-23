package com.blorre.diary

import com.blorre.calendar.BlorreCalendar
import com.blorre.security.User
class Diary {

    transient springSecurityService

    String title
    String post
    Date dateTransaction = new Date()

    static belongsTo = [
            day: BlorreCalendar,
            user: User
    ]

    static mapping = {
        sort(dateTransaction: "desc")
    }

    static constraints = {
        post nullable: false
        dateTransaction nullable: false
        day nullable: true
        user nullable: true
    }
}
