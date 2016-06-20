package com.makingdevs

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.junit.*
import spock.lang.*


@TestFor(QuestionService)
@Mock([Question])
class SearchingQuestionServiceSpec extends Specification {

  @Unroll
  def """ Get the questions given a tag """(){
    given:      
      def searchedTag = "groovy"
      Question.metaClass.setTags{ tagList -> }
      Question.metaClass.static.findAllByTag = { String tag -> 
        [new Question(description:"What is Groovy?",questionType:QuestionType.OPEN).save()]}
      def question = new Question(description:"What is Groovy?",questionType:QuestionType.OPEN).save()
      question.setTags(['groovy'])
    when:
      def questions = service.getQuestionsByTag(searchedTag)
    then:
      questions.size() == 1
  }
}