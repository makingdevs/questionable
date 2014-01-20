package com.makingdevs

import com.makingdevs.*

class EvaluateController {

  def questionService

  def open(){
    def question = Question.findAllByQuestionType(QuestionType.OPEN)
    [question:question]
  }

  def true_false(){
    def question = Question.findAllByQuestionType(QuestionType.TRUE_FALSE)
    [question:question]
  }

  def multiple_choice(){
    def question = Question.findAllByQuestionType(QuestionType.MULTIPLE_CHOICE)
    [question:question]
  }

  def multiple_response(){
    def question = Question.findAllByQuestionType(QuestionType.MULTIPLE_RESPONSE)
    [question:question]
  }

  def evaluate(){
    def rating=questionService.evaluateAnswer(params.id, tipoDescription(params.id,params.description))
    redirect(action: "rating", id:params.id, params: [rating:rating])
  }

  def rating (){
    def question= Question.get(params.id)
    [params:params,
    question:question]
  }

  private def tipoDescription(idPregunta,description){
    def question=Question.get(idPregunta)
    if (question.questionType==QuestionType.OPEN)return description
    if (question.questionType==QuestionType.TRUE_FALSE) if (description=="true") return true
    if (question.questionType==QuestionType.MULTIPLE_CHOICE||question.questionType==QuestionType.MULTIPLE_RESPONSE) return description*.toLong()
  }
}