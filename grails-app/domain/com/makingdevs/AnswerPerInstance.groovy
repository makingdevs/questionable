package com.makingdevs

class AnswerPerInstance {

  Date dateCreated
  Date lastUpdated

  Question question

  static belongsTo = [questionaryPerInstance:QuestionaryPerInstance]

  static hasMany = [answerPerUsers:AnswerPerUser,
                    openAnswerPerUsers:OpenAnswerPerUser]

  static constraints = {
 }
}
