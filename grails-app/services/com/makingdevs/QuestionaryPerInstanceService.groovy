package com.makingdevs

import grails.transaction.Transactional

@Transactional
class QuestionaryPerInstanceService {

  def instanceQuestionary(questionaryId){
    def questionary=Questionary.get(questionaryId)
    def questionaryPerInstance=new QuestionaryPerInstance(questionary:questionary)
    questionary.questions.each{it->
      questionaryPerInstance.addToAnswerPerInstances(new AnswerPerInstance(question:it))
    }
    questionaryPerInstance.save(true:false)
    questionaryPerInstance
  }

}