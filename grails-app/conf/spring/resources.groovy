import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import edu.umn.idm.PasswordStealerFilter

// Place your Spring DSL code here
beans = {

	def conf = SpringSecurityUtils.securityConfig

	passwordStealerFilter(PasswordStealerFilter) {
		usernameField = conf.apf.usernameParameter
		passwordField = conf.apf.passwordParameter 
	}
}
