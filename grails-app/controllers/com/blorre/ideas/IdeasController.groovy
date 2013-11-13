package com.blorre.ideas

import com.blorre.calendar.BlorreCalendar
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class IdeasController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ideasInstanceList: Ideas.list(params), ideasInstanceTotal: Ideas.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[ideasInstance: new Ideas(params)]
			break
		case 'POST':
	        Ideas ideasInstance = new Ideas()
            ideasInstance.title = params.get('title')
            ideasInstance.idea = params.get('idea')

            BlorreCalendar dayToTrack = BlorreCalendar.findOrCreateByDayToTrack(new Date().clearTime())
            dayToTrack.addToIdeas(ideasInstance)

	        if (!dayToTrack.save(flush: true) && !ideasInstance.save(flush: true)) {
	            render view: 'create', model: [ideasInstance: ideasInstance]
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'ideas.label', default: 'Ideas'), ideasInstance.id])
	        redirect action: 'show', id: ideasInstance.id
			break
		}
    }

    def show() {
        Ideas ideasInstance = Ideas.get(params.id)
        if (!ideasInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ideas.label', default: 'Ideas'), params.id])
            redirect action: 'list'
            return
        }

        [ideasInstance: ideasInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def ideasInstance = Ideas.get(params.id)
	        if (!ideasInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ideas.label', default: 'Ideas'), params.id])
	            redirect action: 'list'
	        }

	        [ideasInstance: ideasInstance]
			break
		case 'POST':
	        def ideasInstance = Ideas.get(params.id)
	        if (!ideasInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ideas.label', default: 'Ideas'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (ideasInstance.version > version) {
	                ideasInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'ideas.label', default: 'Ideas')] as Object[],
	                          "Another user has updated this Ideas while you were editing")
	                render view: 'edit', model: [ideasInstance: ideasInstance]
	                return
	            }
	        }

	        ideasInstance.properties = params

	        if (!ideasInstance.save(flush: true)) {
	            render view: 'edit', model: [ideasInstance: ideasInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'ideas.label', default: 'Ideas'), ideasInstance.id])
	        redirect action: 'show', id: ideasInstance.id
			break
		}
    }

    def delete() {
        Ideas ideasInstance = Ideas.get(params.id)
        if (!ideasInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ideas.label', default: 'Ideas'), params.id])
            redirect action: 'list'
            return
        }

        try {
            ideasInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'ideas.label', default: 'Ideas'), params.id])
            redirect action: 'list'
        }
        catch (ignored) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ideas.label', default: 'Ideas'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
