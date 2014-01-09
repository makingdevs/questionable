package com.makingdevs

enum QuestionType {

  OPEN("question.type.open"),
  MULTIPLE_CHOICE ("question.type.multiple_choice"),
  CLOSED("question.type.closed"),
  MULTIPLE_RESPONSE("question.type.multiple_response")

  private final String code

  QuestionType(String code){
    this.code = code
  }

  public String getCode(){ return this.code }
}