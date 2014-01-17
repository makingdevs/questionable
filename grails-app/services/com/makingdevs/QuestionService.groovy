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

        case QuestionType.MULTIPLE_CHOICE:
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

        case QuestionType.MULTIPLE_RESPONSE:
        def answers_user = Answer.findAllByIdInList(answer)
        def answer_solution = []
        def acertadas=0 
        def noacertadas=0 
        def evaluate

        answer_solution = question.answers.findAll { it.solution }

        acertadas = (answer_solution.id.intersect(answers_user.id)).size()
        noacertadas = answer_solution.id.size() - acertadas

        switch(question.answers.size()) {
          case 3:
            switch(acertadas) {
              case 0:
                evaluate=0.0
              break
              case 1:
                evaluate=0.3
              break
              case 2:
                if (noacertadas==0) {
                  evaluate=1.0
                }else{
                  evaluate=0.6
                }
              break
            }
          break

          case 4:
            switch(acertadas) {
              case 0:
                evaluate=0.0
              break
              case 1:
                if (noacertadas==0) {
                  evaluate=0.75
                }else if(noacertadas==1){
                  evaluate=0.5
                }
              break
              case 2:
                if (noacertadas==0) {
                  evaluate=1.0
                }else{
                  evaluate=0.6
                }
              break
            }
          break

        }
        return evaluate
        break
      }
    }
}
