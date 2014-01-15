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
            1.0
          else
            0.0
        break
        case QuestionType.TRUE_FALSE:
          def Boolean answerQuestion=question.answers.solution
          if (answer == answerQuestion)
           0.0
          else
           1.0
        break
      }
    }
}
