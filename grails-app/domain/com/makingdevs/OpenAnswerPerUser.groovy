package com.makingdevs

class OpenAnswerPerUser {

  Date dateCreated
  Date lastUpdated

  String userAnswer

  static belongsTo = [answerPerInstance:AnswerPerInstance]

  static constraints = {
    userAnswer size:0..1000, blank:false, nullable: true
  }
}