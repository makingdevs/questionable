package com.makingdevs

import grails.transaction.Transactional

@Transactional
class TagsService {

	def addTagsToAQuestionFromSimpleText(question,tags){		
		question.parseTags(tags)		
	}
	
	def updateTagsToAQuestionFromSimpleText(question,tags){		
		question.setTags(tags.tokenize(","))
	}
	
}
