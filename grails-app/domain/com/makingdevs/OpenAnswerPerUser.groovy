package com.makingdevs

class OpenAnswerPerUser {

  Date dateCreated
  Date lastUpdated

  static belongsTo = [answerPerInstance:AnswerPerInstance]

  static constraints = {
 }
}