package com.makingdevs

class TextQuestionaryController {

	def questionAndAnswerService

	def index() { }

	def create(){
		def questionary = new Questionary(params)
		log.error params.questionsAndAnswers
		
		def questions = questionAndAnswerService.createQuestionsWithAnswersFromSimpleText(params.questionsAndAnswers)		
		/*
		questions.each{ question ->
			questionary.questions << question
		}	
		if(questionary.save(flush:true))
			redirect action:"showQuestionary",id:questionary.id
		else
			render view:"index"*/
		render("Lista de preguntas")
	}

}
