package com.blorre.LastFm

import grails.util.Environment

class LastFmJob {

    LastFmService lastFmService

    static triggers = {
      simple repeatInterval: 900000l // execute job once in 15 minutes
    }

    def execute() {
        Environment.executeForCurrentEnvironment {
            production {
                lastFmService.readFeed()
            }
        }
    }
}
