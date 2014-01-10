package com.makingdevs

@groovy.transform.ToString
class Questionary {

  String titule
  String description
  QuestionaryCategory questionaryCategory

  static constraints = {
    titule blank:false, size:1..30
    description blank:false, size:1..1000
  }

  static hasMany = [questions : Question]
}