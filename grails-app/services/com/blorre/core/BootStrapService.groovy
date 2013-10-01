package com.blorre.core

import com.blorre.config.GlobalConfiguration
import com.blorre.security.Role
import com.blorre.security.User
import com.blorre.security.UserRole

class BootStrapService {

    public void users() {
        if (!User.count()) {

            Role role = new Role(authority: 'ROLE_ADMIN').save(flush: true)

            User user = new User(username: 'Borre', enabled: 'true', password: 'test').save(flush: true)

            UserRole.create(user, role, true)

            assert User.count() == 1

            assert Role.count() == 1

            assert UserRole.count() == 1
        }
    }

    public void globalConfiguration() {
        if(!GlobalConfiguration.count()) {

            GlobalConfiguration globalConfiguration = new GlobalConfiguration(lastFmUser: "Borre-go")

            globalConfiguration.save(flush: true)

            assert GlobalConfiguration.count() == 1
        }
    }
}
