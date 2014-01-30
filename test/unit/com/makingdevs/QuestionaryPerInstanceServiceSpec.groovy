package com.makingdevs

import static org.junit.Assert.*
import org.junit.*
import spock.lang.*


@TestFor(QuestionaryPerInstanceService)
@Mock([Questionary, Question])
class QuestionaryPerInstanceServiceSpec extends Specification{
  def "Crear una instancia de un cuestionario"(){
    given:
      def questionary=new Questionary()
      questionary.title="Cuestionario de prueba"
      questionary.description="Este es un cuestionario de prueba"
      def question = new Question(description:"Pregunta abierta",questionType:QuestionType.OPEN)
      questionary.addToQuestions(question)
      questionary.save(flush:true)
    when:
      def questionaryInstance=service.instanceQuestionary(questionary.id)
    then:
      questionaryInstance.questionary.id==questionary.id
  }
}
