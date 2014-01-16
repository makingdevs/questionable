package com.makingdevs

import grails.transaction.Transactional

@Transactional
class QuestionService {

    def serviceMethod() {

    }

    def evaluateAnswer(question_id,answer){
      def question = Question.get(question_id) 
      switch(question.questionType) {

        case QuestionType.OPEN:
          if (answer)
            return 1.0
          else
            return 0.0
        break

        case QuestionType.TRUE_FALSE:
          if(question.answers.first().solution==answer)
            1.0
          else
            0.0
        break

        case QuestionType.MULTIPLE_RESPONSE:
        def answer_user = Answer.get(answer)
        def id_answer_solution
        int i = 0
        while(question.answers.getAt(i).solution==true) {
          id_answer_solution = question.answers.getAt(i)
          i++
        }
        if (answer_user.id==id_answer_solution.id){
          return 1.0
        }else{
          return 0.0          
        }
        break
      }
    }
}
