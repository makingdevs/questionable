package com.makingdevs

class QuestionaryPerInstanceLink {

  QuestionaryPerInstance questionaryPerInstance

  Long questionaryPerInstanceRef
  String type

  static constraints = {
    questionaryPerInstance min:0L
    type blank:false
  }
}
