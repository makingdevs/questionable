package com.makingdevs

import com.makingdevs.*

class QuestionaryPerInstanceService {

  def questionService

  def instanceQuestionary(questionaryId){
    def questionary=Questionary.get(questionaryId)
    def questionaryPerInstance=new QuestionaryPerInstance(questionary:questionary,
                                                          questionaryPerInstanceStatus:QuestionaryPerInstanceStatus.SIN_CONTESTAR)
    questionary.questions.each{it->
      questionaryPerInstance.addToAnswerPerInstances(new AnswerPerInstance(question:it))
    }
    questionaryPerInstance.save(flush:false)
    questionaryPerInstance
  }

  def addAnswer(idPregunta,respuesta,idQuestionaryPerInstance){
    def question=Question.get(idPregunta)
    def questionaryPerInstance=QuestionaryPerInstance.get(idQuestionaryPerInstance)
    questionaryPerInstance.answerPerInstances.each{it-> 
      if(it.question.id==question.id){
        if(question.questionType==QuestionType.OPEN)
          it.addToOpenAnswerPerUsers(new OpenAnswerPerUser(userAnswer:respuesta)).save(flush:true)
        else
          respuesta.each{idRespuesta -> 
            def answerUser=Answer.get(idRespuesta)
            it.addToAnswerPerUsers(new AnswerPerUser(answer:answerUser)).save(flush:true)
          }
        }
      }
    questionaryPerInstance.save(flush:true)
    questionaryPerInstance
  }

  def evaluateQuestionary(def questionaryPerInstance){
    def listaDeEvaluaciones=[]
    def ratingTotal=0

    for(int b = 0; b < questionaryPerInstance.questionary.questions.size(); b++) {
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

}