package com.makingdevs

import static org.junit.Assert.*
import org.junit.*
import spock.lang.Specification


@TestFor(QuestionService)
@Mock([Question])
class QuestionServiceSpec extends Specification{

  def "Evaluar una pregunta abierta"(){
    given:
      def question = anOpenQuestion()
    when:
      def answer = "Esta es una respuesta abierta a la pregunta"
      def evaluate = service.evaluateAnswer(question.id,answer)
    then:
      evaluate == 1.0
      question.questionType == QuestionType.OPEN
  }

  def "Evaluar una pregunta con opciones de falso o verdadero"(){
    given:
    when:
    then:
  }

  def "Evaluar una pregunta con multiples opciones siendo una la correcta"(){
    given:
    when:
    then:
  }

  def "Evaluar una pregunta con múltiples respuestas"(){
    given:
    when:
    then:
  }

  private Question anOpenQuestion(){
    Question(description:"¿Es esta una pregunta abierta?, Describa",questionType:QuestionType.OPEN).save()
  }

}