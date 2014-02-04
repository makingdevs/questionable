package com.makingdevs

import com.makingdevs.*

class QuestionaryController {

  def questionaryPerInstanceService

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
      redirect(controller: "question", action: "show", id:[question.id])
  }

  def buscar(){
    def questionary = Questionary.get(params.questionary)
    def question= Question.getAll(params.id)
    questionary.questions+=question
    redirect(action: "showQuestionary", id:[questionary.id])
  }

  def list(){
    def questionary = Questionary.list(sort: "title", order: "asc")
    [questionary:questionary]
  }

  def answerQuestionary(){
    def questionary=Questionary.get(params.id ?: 1L)
    def questionaryPerInstance=questionaryPerInstanceService.instanceQuestionary(questionary.id)
    def numPreguntas=questionaryPerInstance.answerPerInstances.size()
    [questionaryPerInstance:questionaryPerInstance,
    numPreguntas:numPreguntas]
  }

}