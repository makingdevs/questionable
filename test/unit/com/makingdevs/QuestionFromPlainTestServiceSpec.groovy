package com.makingdevs

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*


@TestFor(QuestionService)
@Mock([Question])
class QuestionFromPlainTestServiceSpec extends Specification {

  @Unroll
  void "Create question from plain text"(){
    given:
      def simpleText = _simpleText
    when:
      def question = service.buildQuestionFromText(simpleText)
    then:
      !question.id
      question.description == _description 
      question.questionType == _questionType
    where:
    _simpleText                           || _description      | _questionType
    "#MULTIPLE_CHOICE What is Groovy?"    || "What is Groovy?" | QuestionType.MULTIPLE_CHOICE
    "#TRUE_FALSE What is Groovy?"         || "What is Groovy?" | QuestionType.TRUE_FALSE
    "#OPEN What is Groovy?"               || "What is Groovy?" | QuestionType.OPEN
    "#MULTIPLE_RESPONSE What is Groovy?"  || "What is Groovy?" | QuestionType.MULTIPLE_RESPONSE
    "#MULTIPLE_RESPONSE What is Groovy?\nMark all possibilities"  || "What is Groovy?\nMark all possibilities" | QuestionType.MULTIPLE_RESPONSE
  }

  @Unroll
  void "Create tags for question from plain text"(){
    given:
      def simpleText = _simpleText    
    when:
      def tags = service.getTagsFromText(simpleText)
    then:
      tags == _tagList
    where:
    _simpleText                                                 || _tagList
    "#MULTIPLE_CHOICE What is Spring? [spring,framework]"       || ["spring","framework"]
    "#MULTIPLE_RESPONSE What is Grails? [groovy,grails,spring]" || ["groovy","grails","spring"]
    "#OPEN What is JavaScript? [javascript]"                    || ["javascript"]
  }

}
