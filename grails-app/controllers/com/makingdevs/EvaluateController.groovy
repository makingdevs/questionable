package com.makingdevs

import com.makingdevs.*

class EvaluateController {

  def questionService
  def questionaryPerInstanceService

  def evaluate(){
    def rating=questionService.evaluateAnswer(params.id, tipoDescription(params.id,params.description))
    redirect(action: "rating", id:params.id, params: [rating:rating])
  }

  def rating (){
    def question= Question.get(params.id)
    [params:params,
    question:question]
  }

  def evaluateQuestionary(){
    def idPregunta = []
    def respuestaUsuario =[]
    def questionaryPerInstance=QuestionaryPerInstance.get(params.questionaryPerInstance)

    if(questionaryPerInstance.questionaryPerInstanceStatus==QuestionaryPerInstanceStatus.SIN_CONTESTAR){
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
      idPregunta << params.getAt("question[${i}]").id
      respuestaUsuario << params.getAt("question[${i}]").description
    }
    for (int a = 0; a < params.numPreguntas.toLong(); a++) {
      def question=Question.get(idPregunta[a])
      questionaryPerInstanceService.addAnswer(idPregunta[a],tipoDescription(idPregunta[a],respuestaUsuario[a]),questionaryPerInstance.id)
    }}
    def evaluateQuestionary=questionaryPerInstanceService.evaluateQuestionary(questionaryPerInstance,params.questionaryPerInstanceLink)
    [listaDeEvaluaciones:evaluateQuestionary.listaDeEvaluaciones,
    ratingTotal:evaluateQuestionary.ratingTotal,
    questionaryPerInstance:questionaryPerInstance,
    url:params.url]
  }

  private def tipoDescription(idPregunta,description){
    def question=Question.get(idPregunta)
    if (question.questionType==QuestionType.OPEN)return description
    else return description*.toLong()
  }
}