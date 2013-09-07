package com.blorre.ideas



import org.junit.*
import grails.test.mixin.*

@TestFor(IdeasController)
@Mock(Ideas)
class IdeasControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ideas/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ideasInstanceList.size() == 0
        assert model.ideasInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ideasInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ideasInstance != null
        assert view == '/ideas/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ideas/show/1'
        assert controller.flash.message != null
        assert Ideas.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ideas/list'

        populateValidParams(params)
        def ideas = new Ideas(params)

        assert ideas.save() != null

        params.id = ideas.id

        def model = controller.show()

        assert model.ideasInstance == ideas
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ideas/list'

        populateValidParams(params)
        def ideas = new Ideas(params)

        assert ideas.save() != null

        params.id = ideas.id

        def model = controller.edit()

        assert model.ideasInstance == ideas
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ideas/list'

        response.reset()

        populateValidParams(params)
        def ideas = new Ideas(params)

        assert ideas.save() != null

        // test invalid parameters in update
        params.id = ideas.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ideas/edit"
        assert model.ideasInstance != null

        ideas.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ideas/show/$ideas.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ideas.clearErrors()

        populateValidParams(params)
        params.id = ideas.id
        params.version = -1
        controller.update()

        assert view == "/ideas/edit"
        assert model.ideasInstance != null
        assert model.ideasInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ideas/list'

        response.reset()

        populateValidParams(params)
        def ideas = new Ideas(params)

        assert ideas.save() != null
        assert Ideas.count() == 1

        params.id = ideas.id

        controller.delete()

        assert Ideas.count() == 0
        assert Ideas.get(ideas.id) == null
        assert response.redirectedUrl == '/ideas/list'
    }
}
