package com.makingdevs

import com.makingdevs.*

class EvaluateController {

  def questionService

  def evaluate(){
    def rating=questionService.evaluateAnswer(params.id, tipoDescription(params.id,params.description))
    redirect(action: "rating", id:params.id, params: [rating:rating])
  }

  def rating (){
    def question= Question.get(params.id)
    [params:params,
    question:question]
  }

  // TODO; Refactor a servicio
  def evaluateQuestionary(){
    def idPregunta = []
    def respuestaUsuario =[]
    def listaDeEvaluaciones=[]
    def ratingTotal=0
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
       idPregunta << params.getAt("question[${i}]").id
       respuestaUsuario << params.getAt("question[${i}]").description
    }
    for (int a = 0; a < params.numPreguntas.toLong(); a++) {
      def question=Question.get(idPregunta[a])
      def evaluacion=[pregunta:question,rating:questionService.evaluateAnswer(idPregunta[a], tipoDescription(idPregunta[a],respuestaUsuario[a]))]

      listaDeEvaluaciones<<evaluacion
      ratingTotal+=evaluacion.rating
    }
    [listaDeEvaluaciones:listaDeEvaluaciones,
    ratingTotal:ratingTotal]

  }

  private def tipoDescription(idPregunta,description){
    def question=Question.get(idPregunta)
    if (question.questionType==QuestionType.OPEN)return description
    if (question.questionType==QuestionType.TRUE_FALSE) if (description=="true") return true
    if (question.questionType==QuestionType.MULTIPLE_CHOICE||question.questionType==QuestionType.MULTIPLE_RESPONSE) return description*.toLong()
  }
}