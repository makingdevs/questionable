package com.makingdevs

import static org.junit.Assert.*
import org.junit.*
import spock.lang.*


@TestFor(QuestionaryPerInstanceService)
@Mock([Questionary, Question, QuestionaryPerInstance])
class QuestionaryPerInstanceServiceSpec extends Specification{
  def "Crear una instancia de un cuestionario"(){
    given:
      def questionary=new Questionary()
      questionary.title="Cuestionario de prueba"
      questionary.description="Este es un cuestionario de prueba"
      questionary.addToQuestions(new Question(description:"Pregunta abierta",questionType:QuestionType.OPEN))
      questionary.addToQuestions(new Question(description:"Pregunta falso verdadero",questionType:QuestionType.TRUE_FALSE))
      questionary.addToQuestions(new Question(description:"Pregunta multi opcion",questionType:QuestionType.MULTIPLE_CHOICE))
      questionary.addToQuestions(new Question(description:"Pregunta multi respuesta",questionType:QuestionType.MULTIPLE_RESPONSE))
      questionary.save(flush:true)
    when:
      def questionaryInstance=service.instanceQuestionary(questionary.id)
    then:
      questionaryInstance.questionary.id==questionary.id
      questionaryInstance.answerPerInstances.size()==questionary.questions.size()
  }
}
