package com.makingdevs

import grails.test.mixin.*
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
      currentTags = tags.split(",")
      question.parseTags >> currentTags
      question.getTags >> currentTags
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
      currentTags = tags.split(",")
      question.parseTags >> currentTags
      question.setTags >> currentTags
      question.getTags >> currentTags
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
