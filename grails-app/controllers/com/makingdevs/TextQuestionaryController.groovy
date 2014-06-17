package com.makingdevs

class TextQuestionaryController {

	def questionAndAnswerService

	def index() { }

	def create(){
		def questionary = new Questionary(params)
    def questionValue = params.questionsAndAnswers
    log.error questionValue
    def regex = questionValue =~ /#\w+[\s\w+]+\??[\n\w+]*[\n|\r]|[^#].+/
    log.error regex[0]
		
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
