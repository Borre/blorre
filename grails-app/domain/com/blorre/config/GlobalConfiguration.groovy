package com.blorre.config

class GlobalConfiguration {

    String lastFmUser

    static constraints = {
        lastFmUser(nullable: false, blank: false)
    }
}
