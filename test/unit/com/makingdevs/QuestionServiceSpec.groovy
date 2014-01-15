package com.makingdevs

import static org.junit.Assert.*
import org.junit.*
import spock.lang.Specification


@TestFor(QuestionService)
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

  }

  def "Evaluar una pregunta de opcion multiple"(){
    given:"Dada una pregunta de opcion multiple"
    def pregunta = new Question()
    pregunta.description="Tipo de lenguaje groovy"
    pregunta.questionType="MULTIPLE_CHOICE"
    and:"y dada su respuestas de la pregunta"
    def answer1 = new Answer()
    answer1.description="funcional"
    answer1.solution="false"
    def answer2 = new Answer()
    answer2,description="dinamico"
    answer2.solution="true"
    def answer3 = new Answer()
    answer3.description="estatico"
    answer3.solution="false"
    pregunta.addToAnswers(answer1)
    pregunta.addToAnswers(answer2)
    pregunta.addToAnswers(answer3)
    when:"El usuario responde la pregunta correctamente"
    def respuestaUsuario = "dinamico"
    def evaluacion = service.evaluar(respuestaUsuario,pregunta)
    then:"Evaluando la pregunta el usuario debe de obtiene 1 punto"
    assert evaluacion == 1
  } 

}