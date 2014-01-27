package com.makingdevs

class AnswerPerInstance {

  Date dateCreated
  Date lastUpdated

  Question question
  OpenAnswerPerUser openAnswerPerUser

  static belongsTo = [questionaryPerInstance:QuestionaryPerInstance]

  static hasMany = [answerPerUser:AnswerPerUser]

  static constraints = {
 }
}
