package com.makingdevs

class AnswerPerUser {

  Date dateCreated
  Date lastUpdated

  Answer answer

  static belongsTo = [answerPerInstance:AnswerPerInstance]

  static constraints = {
 }
}