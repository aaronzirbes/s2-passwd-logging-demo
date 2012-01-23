package edu.umn.idm

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class SecretController {
	def index = { render "Secure Access Only." }
}
