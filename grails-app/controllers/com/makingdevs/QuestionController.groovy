package com.makingdevs

class QuestionController {

  def index() { }

  def create(){
    def pregunta = new Question(params)
    if(pregunta.save(flush:true)){
      redirect action:"show", id:pregunta.id
    }else{
      render view:"create"
    }
  }

  def show(){
    def question = Question.get(params.id)
    [question:question]
  }
}