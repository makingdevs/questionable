package com.makingdevs

import com.makingdevs.*

class QuestionaryTagLib {

  def questionaryPerInstanceService
    
  def showQuestionaryForThisInstance = { attrs, body ->
    def listQuestionaryAvailable=Questionary.list()
    def questionaryLinks = QuestionaryPerInstanceLink
      .findAllWhere(type:attrs.instance.class.getSimpleName(),
      questionaryPerInstanceRef:attrs.instance.id)
    out << render(template:"/questionaryPerInstance/questionaryForThisInstance", 
      model:[listQuestionaryAvailable:listQuestionaryAvailable,
            instance:attrs.instance,
            questionaryLinks:questionaryLinks], plugin:"questionable")
  }

  def showListQuestionaryRating= { attrs, body ->
    def questionaryLinks = QuestionaryPerInstanceLink
      .findAllWhere(type:attrs.instance.class.getSimpleName(),
      questionaryPerInstanceRef:attrs.instance.id)
    def cuestionarios = []
    def ratingUser = []
    def ratingTotal = []
    questionaryLinks.each { cuestionariosLinks ->
      if (cuestionariosLinks.questionaryPerInstance.questionaryPerInstanceStatus==QuestionaryPerInstanceStatus.CONTESTADO) {
        cuestionarios += cuestionariosLinks.questionaryPerInstance.questionary.codeName
        ratingUser += questionaryPerInstanceService.evaluateQuestionary(cuestionariosLinks.questionaryPerInstance,cuestionariosLinks.id).ratingTotal
        ratingTotal += cuestionariosLinks.questionaryPerInstance.questionary.questions.size()
      }
    }
    out << render(template:"/questionaryPerInstance/showListQuestionaryRating",
      model:[cuestionarios:cuestionarios,
            ratingUser:ratingUser,
            ratingTotal:ratingTotal], plugin:"questionable")
  }

}
