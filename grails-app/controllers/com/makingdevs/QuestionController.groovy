package com.makingdevs

class QuestionController {

  def tagsService

  def index() { }

  def save(){
    def pregunta = new Question(params)
    if(pregunta.save(flush:true)){
      tagsService.addTagsToAQuestionFromSimpleText(pregunta.id,params.tags)
      redirect action:"show", id:pregunta.id
    }else{
      render view:"create"
    }
  }

  def show(){
    def question = Question.get(params.id)
    [question:question]
  }

  def edit(){
    def question = Question.get(params.id)
    [question:question]
  }

  def update(){
    def question = Question.get(params.id)
    question.description=params.description
    tagsService.updateTagsToAQuestionFromSimpleText(params.id,params.tags)
    question.save(flush:true)
    redirect(action: "detail", id:question.id)
  }

  def list(){
    def listQuestion = Question.list()
    [listQuestion:listQuestion]
  }

  def detail(){
    def question = Question.get(params.id)
    [question:question]
  }

}