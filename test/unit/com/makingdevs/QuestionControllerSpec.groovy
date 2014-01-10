package com.makingdevs

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class QuestionControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Testing Question Controller"() {

    	given:"Dos respuestas"

    	def pregunta = new Question(description:"¿Que es Groovy?")
		def respuesta_a = new Answer(description:"Es un lenguaje")
    	def respuesta_b = new Answer(description:"Lenguje Dinamico")

    	when:"se le asignara a una pregunta"

    	pregunta.answers=[respuesta_a,respuesta_b]

    	then:"respuesta de pregunta sera igual a dos"

    	pregunta.answers.size()==2

    }
}
