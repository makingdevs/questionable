package com.makingdevs

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.junit.*
import spock.lang.*


@TestFor(AnswerService)
@Mock([Question,Answer])
class AnswerServiceSpec extends Specification {
  
  @Unroll
  def """ Build answer from text plain "#_simpleText" """(){
    given:
      def simpleText =  _simpleText
    when:
      def answers = service.buildAnswerFromText(simpleText)
    then:
      answer.description == _description
      answer.solution == _solution
      !answer.id
    where:
      _simpleText                             ||  _description                      | _solution
      "(*) un fw"                             ||  "un fw"                           | true
      "() un lenguaje"                        ||  "un lenguaje"                     | false
      "( ) una herramienta"                   ||  "una herramienta"                 | false
      "[*] un lenguaje dinamico"              ||  "un lenguaje dinamico"            | true
      "[] una herramiemnta del so"            ||  "una herramiemnta del so"         | false
      "[*] un lenguaje orientado a objetos"   ||  "un lenguaje orientado a objetos" | true
      "F"                                     ||  "Falso"                           | false
      "V*"                                    ||  "Verdadero"                       | true
      "F*"                                    ||  "Falso"                           | true
      "V"                                     ||  "Verdadero"                       | false
  }
  
}