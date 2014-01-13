package com.makingdevs

class QuestionaryController {

  def index() { }

  def create() {
    def questionary = new Questionary(params)
    if(questionary.save(flush:true)){
      redirect action:"showQuestionary", id:questionary.id
    }else{
      render view:"index"
    }
  }

  def showQuestionary(){
    def questionary = Questionary.get(params.id)
    def questions = Question.list()
    [questionary:questionary,
     questions:questions]
  }

  def addQuestion(){
    def questionary = Questionary.get(params.questionary)
      def answer = new Answer(description:params.description)
      question.answers+=[answer]
      redirect(controller: "question", action: "show", id:question.id)
  }

}