import com.blorre.core.BootStrapService

class BootStrap {

    BootStrapService bootStrapService

    def init = { servletContext ->

        bootStrapService.globalConfiguration()

        bootStrapService.users()

    }

    def destroy = {
    }
}
