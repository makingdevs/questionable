package com.makingdevs

import static org.junit.Assert.*
import org.junit.*
import spock.lang.*


@TestFor(QuestionaryPerInstanceService)
@Mock([Questionary, Question, QuestionaryPerInstance, AnswerPerInstance, OpenAnswerPerUser,Answer,AnswerPerUser])
class QuestionaryPerInstanceServiceSpec extends Specification{
  def """Crear una instancia de un cuestionario con el id del mismo 
  y el numero de AnswerPerInstances igual al numero de questions"""(){
    given:
      def questionary=createQuestionary()
    when:
      def questionaryInstance=service.instanceQuestionary(questionary.id)
    then:
      questionaryInstance.questionary.id==questionary.id
      questionaryInstance.answerPerInstances.size()==questionary.questions.size()
  }

  def "Al momento de crear una instancia de questionary el status debe de ser sin contestar"(){
    given:"se crea un cuestionario"
      def questionary=createQuestionary()
    when:"se llama al servicio para crear una instancia de questionary"
      def questionaryInstance=service.instanceQuestionary(questionary.id)
    then:
      questionaryInstance.questionary.id==questionary.id
      questionaryInstance.questionaryPerInstanceStatus==QuestionaryPerInstanceStatus.SIN_CONTESTAR
  }

    def "Dada una instancia de un cuestionario agregar una respuesta ABIERTA dada por el usaurio"(){
    given:
      def questionary=createQuestionary()
      def questionaryInstance=service.instanceQuestionary(questionary.id)
      def preguntaId= questionary.questions.first().id
      def respuesta="string"
    when:
      service.addAnswer(preguntaId,respuesta,questionaryInstance.id)
    then:
      questionaryInstance.answerPerInstances.getAt(0).answerPerUsers==null
      questionaryInstance.answerPerInstances.getAt(0).openAnswerPerUsers.size()==1
      questionaryInstance.answerPerInstances.getAt(0).openAnswerPerUsers.getAt(0).userAnswer==respuesta
  }

  def "Dada una instancia de un cuestionario agregar una respuesta FALSO CIERTO dada por el usuario"(){
    given:
      def questionary=createQuestionary()
      def questionaryInstance=service.instanceQuestionary(questionary.id)
      def preguntaId=questionary.questions.getAt(1).id
      def respuesta=Answer.get(1)
    when:
      service.addAnswer(preguntaId,respuesta.id,questionaryInstance.id)
    then:
      questionaryInstance.answerPerInstances.getAt(1).answerPerUsers.size()==1
      questionaryInstance.answerPerInstances.getAt(1).openAnswerPerUsers==null
  } 

  def "Dada una instancia de un cuestionario agregar una respuesta MULTIOPCION dada por el usuario"(){
    given:
      def questionary=createQuestionary()
      def questionaryInstance=service.instanceQuestionary(questionary.id)
      def preguntaId=questionary.questions.getAt(2).id
      def respuesta=Answer.get(2)
    when:
      service.addAnswer(preguntaId,respuesta.id,questionaryInstance.id)
    then:
      questionaryInstance.answerPerInstances.getAt(2).answerPerUsers.size()==1
      questionaryInstance.answerPerInstances.getAt(2).openAnswerPerUsers==null
  }

  def "Dada una instancia de un cuestionario agregar una respuesta MULTIRESPUESTA dada por el usuario"(){
    given:
      def questionary=createQuestionary()
      def questionaryInstance=service.instanceQuestionary(questionary.id)
      def preguntaId=questionary.questions.getAt(3).id
      def respuestas= []
      def respuesta_1 = Answer.get(5)
      def respuesta_2 =Answer.get(6)
      respuestas << respuesta_1.id << respuesta_2.id
    when:
      service.addAnswer(preguntaId,respuestas,questionaryInstance.id)
    then:
      questionaryInstance.answerPerInstances.getAt(3).answerPerUsers.size()==2
      questionaryInstance.answerPerInstances.getAt(3).openAnswerPerUsers==null
  }

  private Questionary createQuestionary(){
    def questionary=new Questionary()
    questionary.title="Cuestionario de prueba"
    questionary.description="Este es un cuestionario de prueba"
    questionary.codeName="GROOVY-ESSENTIAL"
    questionary.addToQuestions(new Question(description:"Pregunta abierta",questionType:QuestionType.OPEN))
    questionary.addToQuestions(new Question(description:"Pregunta falso verdadero",questionType:QuestionType.TRUE_FALSE)
      .addToAnswers(new Answer(description:"true",solution:true)))
    questionary.addToQuestions(new Question(description:"Pregunta multi opcion",questionType:QuestionType.MULTIPLE_CHOICE)
      .addToAnswers(new Answer(description:"respuesta uno",solution:true))
      .addToAnswers(new Answer(description:"respuesta dos",solution:false))
      .addToAnswers(new Answer(description:"respuesta tres",solution:false)))
    questionary.addToQuestions(new Question(description:"Pregunta multi respuesta",questionType:QuestionType.MULTIPLE_RESPONSE)
      .addToAnswers(new Answer(description:"respuesta uno",solution:true))
      .addToAnswers(new Answer(description:"respuesta dos",solution:true))
      .addToAnswers(new Answer(description:"respuesta tres",solution:false)))
    questionary.save(flush:true)
    questionary
  }
}
