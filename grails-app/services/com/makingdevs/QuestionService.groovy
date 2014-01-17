package com.makingdevs

import grails.transaction.Transactional

@Transactional
class QuestionService {

  def evaluateAnswer(question_id,answer){
    def ratings = 0
    def question = Question.get(question_id) 
    switch(question.questionType) {

      case QuestionType.OPEN:
      if (answer)
      ratings = 1.0
      break

      case QuestionType.TRUE_FALSE:
      if(question.answers.first().solution==answer)
      ratings = 1.0
      break

      case QuestionType.MULTIPLE_CHOICE:
      def answer_user = Answer.get(answer)
      def id_answer_solution
      int i = 0
      while(question.answers.getAt(i).solution==true) {
        id_answer_solution = question.answers.getAt(i)
        i++
      }
      if (answer_user.id==id_answer_solution.id) ratings = 1.0
      break

      case QuestionType.MULTIPLE_RESPONSE:

      def answersFromUser = Answer.findAllByIdInList(answer)
      def checks = 0
      question.answers.each { answerQuestion ->
        def answersMatches = this.&getIfAnswerUserMatchWithAnswerQuestion.curry(answersFromUser,answerQuestion)
        if(theSolutionIsTrue(answersMatches())) ++checks
        if(theSolutionIsFalse(answerQuestion) && !answersMatches()) ++checks
      }

      if(checks == question.answers.size())
        ratings = 1.0
      else
        ratings = checks * Math.round( 1 / question.answers.size() * 100 ) / 100

      break
    }
    ratings
  }

  private Answer getIfAnswerUserMatchWithAnswerQuestion(answersFromUser,answerFromQuestion){
    answersFromUser.find { au -> au.id == answerFromQuestion.id }
  }

  private def theSolutionIsFalse(answer){
    !answer.solution
  }

  private def theSolutionIsTrue(answer){
    answer?.solution
  }
}