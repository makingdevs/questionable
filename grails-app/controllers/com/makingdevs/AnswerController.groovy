package com.makingdevs

class AnswerController {

    def create() { 
      def question = Question.get(params.id)
    [question:question]
    }

    def agregar(){
      def question = Question.get(params.question)
      def answer = new Answer(description:params.description, solution:params.solution)
        
      question.save()
      redirect(controller: "question", action: "show", id:question.id)

    }

}
