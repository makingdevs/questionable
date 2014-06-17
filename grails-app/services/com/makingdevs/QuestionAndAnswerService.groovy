package com.makingdevs

import grails.transaction.Transactional

@Transactional
class QuestionAndAnswerService {

	def questionService
    def answerService

    def createQuestionWithAnswersFromSimpleText(fullQuestion){
      def questionsAndAnswersList = (fullQuestion =~ /#\w+[\s\w+]+\??[\n\w+]*\n|[^#].+/)
      def question
      def answers = []
    	
      if(questionsAndAnswersList){
        questionsAndAnswersList.each{
          if(it.trim()){
            if(it.trim()[0]=="#")
              question = questionService.buildQuestionFromText(it.trim())
            else
              answers << answerService.buildAnswerFromText(it.trim())
          }
        }
      }
      
      answers.each { question.addToAnswers(it) }
      question.save()
      question
    }

    def createQuestionsWithAnswersFromSimpleText(fullQuestions){
      def questionsAndAnswersList = (fullQuestions =~ /#\w+[\s\w+]+\??[\n\w+]*\n|[^#].+/)
      def questions = []
      def answers = []

      if(questionsAndAnswersList){
        questionsAndAnswersList.each{
          if(it.trim()){
            if(it.trim()[0]=="#"){
              if(answers && questions){                
                answers.each{ answer -> questions.last().addToAnswers(answer) }
                answers.clear()
              }
              def question = questionService.buildQuestionFromText(it.trim())
              questions << question
            }
            else
              answers << answerService.buildAnswerFromText(it.trim())
          }
        }   
      }

      answers.each { questions.last().addToAnswers(it) }
      questions*.save()
      questions
    }
}
