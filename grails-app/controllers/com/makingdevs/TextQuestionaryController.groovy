package com.makingdevs

class TextQuestionaryController {

	def questionAndAnswerService

	def index() { }

	def create(){
		def questionary = new Questionary(params)			
		def questions = questionAndAnswerService.createQuestionsWithAnswersFromSimpleText(params.questionsAndAnswers)
		
		questions.each{ question ->
			questionary.addToQuestions(question)
		}

		if(questionary.save(flush:true))
			redirect controller:"questionary",action:"showQuestionary",id:questionary.id
		else
			render view:"index"
	}
}
