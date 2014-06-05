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
      def answer_user = Answer.get(answer)
      question.answers.each{answerOfQuestion-> 
      if (answerOfQuestion.description==answer_user.description&&answerOfQuestion.solution==true) 
      ratings=1.0}
      break

      case QuestionType.MULTIPLE_CHOICE:
      def answer_user = Answer.get(answer)
      if (answer_user.id in question.answers.id&&answer_user.solution==true) ratings=1.0 else ratings=0.0
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

  def createQuestionFromText(simpleText){
    def question = new Question()

    def stringSplittedBySpaces = simpleText.split(" ")
    def typeQuestionInString = stringSplittedBySpaces[0].substring(1,stringSplittedBySpaces[0].size())
    def questionType = QuestionType.valueOf(typeQuestionInString)
    if(questionType){
      question.questionType = questionType
      question.description = stringSplittedBySpaces.minus("#"+typeQuestionInString).join(' ')
      question.save()
    }else{
      throw new RuntimeException("Cannot parse question '$simpleText'")
    }
     
    question
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