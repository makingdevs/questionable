package com.makingdevs

class OpenAnswerPerUser {

  Date dateCreated
  Date lastUpdated

  String answerUser

  static belongsTo = [answerPerInstance:AnswerPerInstance]

  static constraints = {
    answerUser size:1..200, blank:false
 }
}