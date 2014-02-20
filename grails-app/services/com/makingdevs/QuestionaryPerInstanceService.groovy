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
          it.addToOpenAnswerPerUsers(new OpenAnswerPerUser(userAnswer:respuesta)).save()
        else
          respuesta.each{idRespuesta -> 
            def answerUser=Answer.get(idRespuesta)
            it.addToAnswerPerUsers(new AnswerPerUser(answer:answerUser)).save()
          }
        }
      }
    questionaryPerInstance.save()
    questionaryPerInstance
  }

  def evaluateQuestionary(def questionaryPerInstance, def idQuestionaryPerInstanceLink){
    def listaDeEvaluaciones=[]
    def ratingTotal=0
    def questionaryPerInstanceLink=QuestionaryPerInstanceLink.get(idQuestionaryPerInstanceLink)

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
    questionaryPerInstance.questionaryPerInstanceStatus=QuestionaryPerInstanceStatus.CONTESTADO
    [listaDeEvaluaciones:listaDeEvaluaciones,
    ratingTotal:ratingTotal,
    questionaryPerInstanceLinkclazz:questionaryPerInstanceLink.type,
    questionaryPerInstanceLinkref:questionaryPerInstanceLink.questionaryPerInstanceRef]
  }

  private def openOrNot(idAnswerPerInstance){
    def respuestaInstanciada=AnswerPerInstance.get(idAnswerPerInstance)
    if (respuestaInstanciada.openAnswerPerUsers.size()>0){return respuestaInstanciada.openAnswerPerUsers.first().userAnswer}
    if (respuestaInstanciada.answerPerUsers.size()>0){return respuestaInstanciada.answerPerUsers.answer.id*.toLong()}
  }

}