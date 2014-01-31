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
    questionaryPerInstance.save(flush:false)
    questionaryPerInstance
  }

  def addAnswer(idPregunta,respuesta,idQuestionaryPerInstance){
    def question=Question.get(idPregunta)
    def questionaryPerInstance=QuestionaryPerInstance.get(idQuestionaryPerInstance)
    questionaryPerInstance.answerPerInstances.each{it-> 
      if(it.question.id==question.id){
      it.addToOpenAnswerPerUsers(new OpenAnswerPerUser(userAnswer:respuesta)).save()
      }
    }
    questionaryPerInstance.save(flush:true)
    questionaryPerInstance
  }
}