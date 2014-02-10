package com.makingdevs

class QuestionaryPerInstance {

  Questionary questionary
  QuestionaryPerInstanceStatus questionaryPerInstanceStatus

  Date dateCreated
  Date lastUpdated

  static hasMany = [answerPerInstances : AnswerPerInstance]

  static constraints = {
 }
}
