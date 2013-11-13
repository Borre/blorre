package com.blorre.LastFm

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class LastFmController {

    LastFmService lastFmService

    static allowedMethods = [index: 'GET']

    def index() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [lastFmInstanceList: LastFm.list(params), lastFmInstanceTotal: LastFm.count()]
    }

    def execute() {
        lastFmService.readFeed()
        redirect(action: index())
    }
}
