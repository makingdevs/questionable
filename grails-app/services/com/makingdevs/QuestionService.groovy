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
      }
    }
}
