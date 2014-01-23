package com.makingdevs

class QuestionaryPerInstance {

  Date dateCreated
  Date lastUpdated

  static belongsTo = [questionary:Questionary]

  static constraints = {
 }
}
