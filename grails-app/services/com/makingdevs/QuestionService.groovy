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

  def buildQuestionFromText(simpleText){    
    Question question
    if(simpleText){    
      simpleText = simpleText.trim()
      /*
      def tagsList =  (simpleText =~ /\[.+\]$/)
      def tags = ""
      if(tagsList){
        tags = tagsList[0]
        simpleText -= tags
      }*/
      simpleText -= simpleText[0]
      def typeQuestionInString = simpleText.split(" ")[0]      
      def questionType = QuestionType.valueOf(typeQuestionInString.trim())
      if(questionType)
        question = new Question(questionType:questionType,
                                description:(simpleText-questionType).trim())
      else
        throw new RuntimeException("Cannot parse question '$simpleText'")
    }
    else
      throw new RuntimeException("Cannot parse an empty question")

    question
  }

  def getTagsFromText(simpleText){
    def tagsBetweenCrotchets = (simpleText =~ /\[.*\]/)    
    def tagList = []       
    
    if(tagsBetweenCrotchets){
      def tagsWithoutCrotchets = tagsBetweenCrotchets[0].replaceAll("\\[|\\]","")      
      tagList = tagsWithoutCrotchets.split(",")
    }
    
    tagList
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