package com.makingdevs

import com.makingdevs.*
import grails.transaction.Transactional

class QuestionaryPerInstanceLinkService {

  def questionaryPerInstanceService

  def createQuestionaryPerInstance(def instance, Long questionaryId) {
    if(!Questionable.class.isAssignableFrom(instance.class)){
      throw new Exception("Pelaz!")
    }
    def questionaryPerInstanceLink =new QuestionaryPerInstanceLink(
      questionaryPerInstance:questionaryPerInstanceService.instanceQuestionary(questionaryId),
      type:instance.class.getSimpleName(),
      questionaryPerInstanceRef:instance.id)
      .save(flush:true)
    questionaryPerInstanceLink
  }

  def findQuestionaryPerInstance(Long idInstance, String criteria){
    def encontrado=QuestionaryPerInstanceLink.findAllByQuestionaryPerInstanceRef(idInstance)
    def questionario
    encontrado.questionaryPerInstance.each{it -> 
      if(it.questionary.title==criteria)
      questionario=it
    }
    questionario
  }
}
