package com.blorre.diary

import com.blorre.calendar.BlorreCalendar
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class DiaryController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        switch (request.method) {
            case 'GET':
                params.max = Math.min(params.max ? params.int('max') : 10, 100)
                [diaryInstanceList: Diary.list(params), diaryInstanceTotal: Diary.count(), diaryInstance: new Diary(params)]
                break
            case 'POST':
                params.dateTransaction = new Date()
                Diary diaryInstance = new Diary(params)

                BlorreCalendar day = BlorreCalendar.findOrCreateByDayToTrack(new Date().clearTime())
                day.addToDiaryEntrys(diaryInstance)

                if (!diaryInstance.save(flush: true)) {
                    render view: 'index', model: [diaryInstance: diaryInstance]
                    return
                }
                flash.message = message(code: 'default.created.message', args: [message(code: 'diary.label', default: 'Diary'), diaryInstance.id])
                redirect action: 'show', id: diaryInstance.id
                break
        }
    }

    def show() {
        def diaryInstance = Diary.get(params.id)
        if (!diaryInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'diary.label', default: 'Diary'), params.id])
            redirect action: 'index'
            return
        }

        [diaryInstance: diaryInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def diaryInstance = Diary.get(params.id)
	        if (!diaryInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'diary.label', default: 'Diary'), params.id])
	            redirect action: 'index'
	        }

	        [diaryInstance: diaryInstance]
			break
		case 'POST':
	        def diaryInstance = Diary.get(params.id)
	        if (!diaryInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'diary.label', default: 'Diary'), params.id])
	            redirect action: 'index'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (diaryInstance.version > version) {
	                diaryInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'diary.label', default: 'Diary')] as Object[],
	                          "Another user has updated this Diary while you were editing")
	                render view: 'edit', model: [diaryInstance: diaryInstance]
	                return
	            }
	        }

	        diaryInstance.properties = params

	        if (!diaryInstance.save(flush: true)) {
	            render view: 'edit', model: [diaryInstance: diaryInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'diary.label', default: 'Diary'), diaryInstance.id])
	        redirect action: 'show', id: diaryInstance.id
			break
		}
    }

    def delete() {
        def diaryInstance = Diary.get(params.id)
        if (!diaryInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'diary.label', default: 'Diary'), params.id])
            redirect action: 'index'
            return
        }

        try {
            diaryInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'diary.label', default: 'Diary'), params.id])
            redirect action: 'index'
        }
        catch (DataIntegrityViolationException e) {
            log.error(e)
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'diary.label', default: 'Diary'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
