package com.makingdevs

class AnswerController {

    def create() { 
      def question = Question.get(params.id)
    [question:question]
    }

    def agregar(){
      def question = Question.get(params.question)
      def answer = new Answer(description:params.description, solution:params.solution)
      question.addToAnswers(answer)
      question.save()
      redirect(controller: "question", action: "show", id:question.id)

    }

    def edit(){
      def answer = Answer.get(params.id)
      [answer:answer,
      question:params.question]
    }

    def update(){
      def answer = Answer.get(params.id)
      answer.description=params.description
      answer.save(flush:true)
      redirect(controller: "question", action: "detail", id:params.question) 
    }

}
