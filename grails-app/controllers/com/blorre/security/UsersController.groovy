package com.blorre.security

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class UsersController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [userInstance: new User(params)]
                break
            case 'POST':
                User userInstance = new User(params)
                if (!userInstance.save(flush: true)) {
                    render view: 'create', model: [userInstance: userInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect action: 'show', id: userInstance.id
                break
        }
    }

    def show() {
        User userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
            return
        }

        [userInstance: userInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def userInstance = User.get(params.id)
                if (!userInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                    redirect action: 'list'
                }

                [userInstance: userInstance]
                break
            case 'POST':
                User userInstance = User.get(params.id)
                if (!userInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (userInstance.version > version) {
                        userInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'user.label', default: 'User')] as Object[],
                                "Another user has updated this User while you were editing")
                        render view: 'edit', model: [userInstance: userInstance]
                        return
                    }
                }

                userInstance.properties = params

                if (!userInstance.save(flush: true)) {
                    render view: 'edit', model: [userInstance: userInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect action: 'show', id: userInstance.id
                break
        }
    }

    def delete() {
        User userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException ignored) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
