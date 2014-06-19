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
			def questionWithTag = service.addTagsToAQuestionFromSimpleText(question.id,tags)
		then:
			questionWithTag.id > 0
			questionWithTag.tags == tags.tokenize(",")
	}

	@Unroll
	def "updateTagsToQuestionFromSimpleText"(){
		given:
			def tags = ""
			def currentTags = []
			def question = new Question().save(validate:false)
		and:
			Question.metaClass.parseTags = {tagsToSave -> currentTags = tagsToSave.split(",")}
			Question.metaClass.setTags = {listOfTags -> currentTags = listOfTags}
			Question.metaClass.getTags = { currentTags }
		when:
			def questionUpdated = service.updateTagsToAQuestionFromSimpleText(question.id,tags)
		then:
			questionUpdated.tags == []
	}

}