import com.blorre.security.Role
import com.blorre.security.User
import com.blorre.security.UserRole

class BootStrap {

    def init = { servletContext ->

        if (!User.count()) {

            Role role = new Role(authority: 'ROLE_ADMIN').save(flush: true)

            User user = new User(username: 'Borre', enabled: 'true', password: 'test').save(flush: true)

            UserRole.create(user, role, true)

            assert User.count() == 1

            assert Role.count() == 1

            assert UserRole.count() == 1
        }
    }

    def destroy = {
    }
}
