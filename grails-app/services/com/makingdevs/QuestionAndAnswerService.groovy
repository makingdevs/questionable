package com.makingdevs

import grails.transaction.Transactional

@Transactional
class QuestionAndAnswerService {

	def questionService
    def answerService

    def createQuestionWithAnswersFromSimpleText(fullQuestion){
      def questionsAndAnswersList = (fullQuestion.trim() =~ /#\w+[\s\w+]+\??[\n\w+]*[\n|\r]|[^#].+/)
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
      log.error fullQuestions
     log.error "?"*80 
      //def separatorLinesPattern = ~/#\w+[\s\w+]+\??[\n\w+]*[\n|\r]|[^#].+/
      def questionsAndAnswersList = (fullQuestions =~ /#\w+[\s\w+]+\??[\n\w+]*[\n|\r]|[^#].+/)
      //def questionsAndAnswersList = separatorLinesPattern.matcher fullQuestions
      def questions = []
      def answers = []
      log.error questionsAndAnswersList[0]
      if(questionsAndAnswersList){
        questionsAndAnswersList.each{ line ->
          log.error line
          if(isThisLineAQuestion(line)){
            if(answers && questions){                
              answers.each{ answer -> questions.last().addToAnswers(answer) }
              answers.clear()
            }
            def question = questionService.buildQuestionFromText(line.trim())
            questions << question
          }
          if(line.trim() && !isThisLineAQuestion(line)){
            answers << answerService.buildAnswerFromText(line.trim())
          }
        }
      }

      answers.each { questions.last().addToAnswers(it) }
      questions*.save()
      questions
    }

    def isThisLineAQuestion(line){
      line.trim() && line.trim()[0]=="#"
    }
}
