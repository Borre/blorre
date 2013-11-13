package com.blorre.ideas

import com.blorre.calendar.BlorreCalendar

class Ideas {

    String title
    String idea
    Date dateTransaction = new Date()

    static belongsTo = [day: BlorreCalendar]

    static mapping = {
        idea(type: "text")
    }

    static constraints = {
        title(nullable: false, blank: false)
        idea(nullable: true, blank: true)
        dateTransaction(nullable: true)
        day(nullable: false)
    }
}
