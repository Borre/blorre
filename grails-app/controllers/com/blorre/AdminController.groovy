package com.blorre

import com.blorre.calendar.BlorreCalendar
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class AdminController {

    def index = {
        [calendar: BlorreCalendar.list([max: 30, sort: "dayToTrack", order: "desc"])]
    }
}
