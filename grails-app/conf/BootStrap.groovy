import edu.umn.idm.*
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityFilterPosition

class BootStrap {

	def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

		def testUser = new User(username: 'me', enabled: true, password: 'password')
		testUser.save(flush: true)

		UserRole.create testUser, adminRole, true

		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1

		def conf = SpringSecurityUtils.securityConfig

		if (conf.active && conf.passwordStealer.active) {
			println "Loading the password stealing filter..."
			// loads the password stealing filter
			SpringSecurityUtils.clientRegisterFilter 'passwordStealerFilter', 
				SecurityFilterPosition.LOGOUT_FILTER.getOrder() + 1
			println "... finshed loading the password stealing filter"
		}
    }
    def destroy = {
    }
}
