package com.blorre.calendar

import com.blorre.diary.Diary

class BlorreCalendar {

    Date dayToTrack = new Date().clearTime()

    static hasMany = [diaryEntrys: Diary]

    static constraints = {
        dayToTrack(nullable: false, unique: true)
    }
}
