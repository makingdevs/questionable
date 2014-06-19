package com.makingdevs

import grails.transaction.Transactional

@Transactional
class TagsService {

	def addTagsToAQuestionFromSimpleText(questionId,tags){
		def question = Question.get(questionId)
		question.parseTags(tags)
		question
	}
	
	def updateTagsToAQuestionFromSimpleText(questionId,tags){
		def question = Question.get(questionId)
		if(tags)
			question.parseTags(tags)
		else
			question.setTags([])
		question
	}

}
