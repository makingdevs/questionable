package com.makingdevs

class AnswerPerInstance {

  Date dateCreated
  Date lastUpdated

  Question question

  static belongsTo = [questionaryPerInstance:QuestionaryPerInstance]

  static hasMany = [answerPerUser:AnswerPerUser,
                    openAnswerPerUser:OpenAnswerPerUser]

  static constraints = {
 }
}
