package com.makingdevs

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserTestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserTest.list(params), model:[userTestCount: UserTest.count()]
    }

    def show(UserTest userTest) {
        respond userTest
    }

    def create() {
        respond new UserTest(params)
    }

    @Transactional
    def save(UserTest userTest) {
        if (userTest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userTest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userTest.errors, view:'create'
            return
        }

        userTest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userTest.label', default: 'UserTest'), userTest.id])
                redirect userTest
            }
            '*' { respond userTest, [status: CREATED] }
        }
    }

    def edit(UserTest userTest) {
        respond userTest
    }

    @Transactional
    def update(UserTest userTest) {
        if (userTest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userTest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userTest.errors, view:'edit'
            return
        }

        userTest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userTest.label', default: 'UserTest'), userTest.id])
                redirect userTest
            }
            '*'{ respond userTest, [status: OK] }
        }
    }

    @Transactional
    def delete(UserTest userTest) {

        if (userTest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userTest.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userTest.label', default: 'UserTest'), userTest.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userTest.label', default: 'UserTest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
