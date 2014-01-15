package com.makingdevs

import static org.junit.Assert.*
import org.junit.*
import spock.lang.Specification


@TestFor(QuestionService)
@Mock([Question, Answer])
class QuestionServiceSpec extends Specification{

  def "Evaluar una pregunta abierta"(){
    given:
      def question = anOpenQuestion()
    when:
      def evaluate = service.evaluateAnswer(question.id,_answer)
    then:
      evaluate == rating
      question.questionType == QuestionType.OPEN
    where:
      _answer                                       || rating
      "Esta es una respuesta abierta a la pregunta" || 1.0
      ""                                            || 0.0
  }

  def "Evaluar una pregunta con opciones de falso o verdadero"(){
    given:
      def question = aTrueFalseQuestion(rightAnswer)
    when:
      def answer = _answer
      def evaluate = service.evaluateAnswer(question.id, answer)
    then:
      evaluate == rating
      question.questionType == QuestionType.TRUE_FALSE
    where: 
      rightAnswer | _answer || rating
      false       | true    || 0.0
      false       | false   || 1.0
      true        | true    || 1.0
      true        | false   || 0.0
  }

  def "Evaluar una pregunta con multiples opciones siendo una la correcta"(){
    
  }

  def "Evaluar una pregunta con múltiples respuestas"(){
    
  }

  private Question anOpenQuestion(){
    new Question(description:"¿Es esta una pregunta abierta?, Describa",questionType:QuestionType.OPEN).save()
  }

  private Question aTrueFalseQuestion(rightAnswer){
    def question = new Question(description:"¿Falso es igual a verdadero?", questionType:QuestionType.TRUE_FALSE)
    def answer = new Answer(solution:rightAnswer)
    question.addToAnswers(answer)
    question.save(validate:false)
    question
  }

}