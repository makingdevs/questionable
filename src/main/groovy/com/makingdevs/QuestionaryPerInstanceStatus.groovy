package com.makingdevs

enum QuestionaryPerInstanceStatus {

  SIN_CONTESTAR("questionaryPerInstanceStatus.status.sin_contestar"),
  CONTESTADO ("questionaryPerInstanceStatus.status.contestado")

  private final String code

  QuestionaryPerInstanceStatus(String code){
    this.code = code
  }

  public String getCode(){ return this.code }
}