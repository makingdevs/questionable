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
        switch(question.questionType){
          case QuestionType.OPEN:
            it.addToOpenAnswerPerUsers(new OpenAnswerPerUser(userAnswer:respuesta)).save(flush:true)
          break
          case QuestionType.TRUE_FALSE:
            def answerUser=Answer.get(respuesta)
            it.addToAnswerPerUsers(new AnswerPerUser(answer:answerUser)).save(flush:true)
          break
          case QuestionType.MULTIPLE_CHOICE:
            def answerUser=Answer.get(respuesta)
            it.addToAnswerPerUsers(new AnswerPerUser(answer:answerUser)).save(flush:true)
          break
          case QuestionType.MULTIPLE_RESPONSE:
            respuesta.each{idRespuesta -> 
              def answerUser=Answer.get(idRespuesta)
              it.addToAnswerPerUsers(new AnswerPerUser(answer:answerUser)).save(flush:true)
            }
          break
        }
      }
    }
    questionaryPerInstance.save(flush:true)
    questionaryPerInstance
  }
}