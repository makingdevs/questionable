package com.makingdevs

class AnswerController {

    def create() { 
      def question = Question.get(params.id)
    [question:question]
    }

    def agregar(){
      def question = Question.get(params.question)
      def answer = new Answer(description:params.description)
      question.answers+=[answer]
      redirect(controller: "question", action: "show", id:question.id)

    }

}
