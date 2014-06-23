package com.makingdevs

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*


@TestFor(TagsService)
@Mock([Question])
class TagsServiceSpec extends Specification {

  @Unroll
  def "addTagsToQuestionFromSimpleText"(){
    given:
      def tags = "groovy,grails,spring"
      def currentTags = []
      def question = new Question().save(validate:false)
    and:
      Question.metaClass.parseTags = {tagsToSave ->currentTags = tagsToSave.split(",")}
      Question.metaClass.getTags = { currentTags }
    when:
      service.addTagsToAQuestionFromSimpleText(question,tags)
    then:
      question.tags == tags.tokenize(',')
  }

  @Unroll
  def "updateTagsToQuestionFromSimpleText"(){
    given:
      def tags = _tags
      def currentTags = _currentTags
      def question = new Question().save(validate:false)
    and:
      Question.metaClass.parseTags = {tagsToSave -> currentTags = tagsToSave.split(",")}
      Question.metaClass.setTags = {listOfTags -> currentTags = listOfTags}
      Question.metaClass.getTags = { currentTags }
    when:
      service.updateTagsToAQuestionFromSimpleText(question,tags)
    then:
      question.tags == currentTags
    where:
      _tags           ||  _currentTags
      ''              ||  []
      'groovy,grails' ||  ['groovy','grails']
  }

}