package com.makingdevs

class QuestionaryPerInstance {

  Questionary questionary

  Date dateCreated
  Date lastUpdated

  static hasMany = [answerPerInstances : AnswerPerInstance]

  static constraints = {
 }
}
