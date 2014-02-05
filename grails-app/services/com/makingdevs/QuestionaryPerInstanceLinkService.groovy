package com.makingdevs

import com.makingdevs.*
import grails.transaction.Transactional

class QuestionaryPerInstanceLinkService {

  def questionaryPerInstanceService

  def createQuestionaryPerInstance(def instance, Long questionaryId) {
    if(!Questionable.class.isAssignableFrom(instance.class)){
      
    }
    def questionaryPerInstanceLink =new QuestionaryPerInstanceLink(
      questionaryPerInstance:questionaryPerInstanceService.instanceQuestionary(questionaryId),
      type:instance.class.getSimpleName(),
      questionaryPerInstanceRef:instance.id)
      .save(flush:true)
    questionaryPerInstanceLink
  }
}
