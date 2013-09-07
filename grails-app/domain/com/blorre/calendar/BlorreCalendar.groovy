package com.blorre.calendar

import com.blorre.diary.Diary
import com.blorre.ideas.Ideas

class BlorreCalendar {

    Date dayToTrack = new Date().clearTime()

    static hasMany = [
            diaryEntrys: Diary,
            ideas: Ideas
    ]

    static constraints = {
        diaryEntrys(nullable: true)
        ideas(nullable: true)
        dayToTrack(nullable: false, unique: true)
    }
}
