package com.makingdevs

class TextQuestionController {

	def questionAndAnswerService
  def index() { }

  def create(){    	
		def questions = questionAndAnswerService.createQuestionsWithAnswersFromSimpleText(params.questionsAndAnswers)
		redirect controller:"question",action:"list"						
  }
    
}
