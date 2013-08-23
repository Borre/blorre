package com.blorre.diary



import org.junit.*
import grails.test.mixin.*

@TestFor(DiaryController)
@Mock(Diary)
class DiaryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/diary/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.diaryInstanceList.size() == 0
        assert model.diaryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.diaryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.diaryInstance != null
        assert view == '/diary/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/diary/show/1'
        assert controller.flash.message != null
        assert Diary.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/diary/list'

        populateValidParams(params)
        def diary = new Diary(params)

        assert diary.save() != null

        params.id = diary.id

        def model = controller.show()

        assert model.diaryInstance == diary
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/diary/list'

        populateValidParams(params)
        def diary = new Diary(params)

        assert diary.save() != null

        params.id = diary.id

        def model = controller.edit()

        assert model.diaryInstance == diary
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/diary/list'

        response.reset()

        populateValidParams(params)
        def diary = new Diary(params)

        assert diary.save() != null

        // test invalid parameters in update
        params.id = diary.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/diary/edit"
        assert model.diaryInstance != null

        diary.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/diary/show/$diary.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        diary.clearErrors()

        populateValidParams(params)
        params.id = diary.id
        params.version = -1
        controller.update()

        assert view == "/diary/edit"
        assert model.diaryInstance != null
        assert model.diaryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/diary/list'

        response.reset()

        populateValidParams(params)
        def diary = new Diary(params)

        assert diary.save() != null
        assert Diary.count() == 1

        params.id = diary.id

        controller.delete()

        assert Diary.count() == 0
        assert Diary.get(diary.id) == null
        assert response.redirectedUrl == '/diary/list'
    }
}
