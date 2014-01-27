package com.makingdevs

class QuestionaryPerInstance {

  Date dateCreated
  Date lastUpdated

  static belongsTo = [questionary:Questionary]

  static hasMany = [answerPerInstance : AnswerPerInstance]

  static constraints = {
 }
}
