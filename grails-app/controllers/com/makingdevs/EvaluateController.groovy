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

  // TODO; Refactor a servicio
  def evaluateQuestionary(){
    def idPregunta = []
    def respuestaUsuario =[]
    def listaDeEvaluaciones=[]
    def ratingTotal=0
    def questionaryPerInstance=QuestionaryPerInstance.get(params.idQuestionary)
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
      idPregunta << params.getAt("question[${i}]").id
      respuestaUsuario << params.getAt("question[${i}]").description
    }
    for (int a = 0; a < params.numPreguntas.toLong(); a++) {
      def question=Question.get(idPregunta[a])
      questionaryPerInstanceService.addAnswer(idPregunta[a],tipoDescription(idPregunta[a],respuestaUsuario[a]),questionaryPerInstance.id)
    }

    for(int b = 0; b < params.numPreguntas.toLong(); b++) {
      def evaluacion=[
      answerUser:questionaryPerInstance.answerPerInstances.getAt(b),
      pregunta:questionaryPerInstance.answerPerInstances.getAt(b).question,
      rating:questionService.evaluateAnswer(
        questionaryPerInstance.answerPerInstances.getAt(b).question.id, 
        openOrNot(questionaryPerInstance.answerPerInstances.getAt(b).id))]
      listaDeEvaluaciones<<evaluacion
      ratingTotal+=evaluacion.rating
    }
    [listaDeEvaluaciones:listaDeEvaluaciones,
    ratingTotal:ratingTotal]
  }

  private def openOrNot(idAnswerPerInstance){
    def respuestaInstanciada=AnswerPerInstance.get(idAnswerPerInstance)
    if (respuestaInstanciada.openAnswerPerUsers.size()>0){return respuestaInstanciada.openAnswerPerUsers.first().userAnswer}
    if (respuestaInstanciada.answerPerUsers.size()>0){return respuestaInstanciada.answerPerUsers.answer.id*.toLong()}
  }

  private def tipoDescription(idPregunta,description){
    def question=Question.get(idPregunta)
    if (question.questionType==QuestionType.OPEN)return description
    else return description*.toLong()
  }
}