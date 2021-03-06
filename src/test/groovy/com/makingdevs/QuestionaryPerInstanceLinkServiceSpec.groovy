package com.makingdevs

import grails.test.mixin.*
import spock.lang.*

@TestFor(QuestionaryPerInstanceLinkService)
@Mock([UserTest,Questionary,QuestionaryPerInstanceService,QuestionaryPerInstance,QuestionaryPerInstanceLink,AnotherUserTest])
class QuestionaryPerInstanceLinkServiceSpec extends Specification {

  def "Crear una instancia de cuestionario para una clase que implementa la interfaz Questionable"() {
    given:"Un usuario previamente creado y un cuestionario existente"
      def userTest = new UserTest().save(validate:false)
      new Questionary().save(validate:false)
    when:"Creamos asignamos una instancia de cuestionario a un usuario"
      def questionaryPerInstanceLink = service.createQuestionaryPerInstance(userTest,1L)
    then:
      questionaryPerInstanceLink.id > 0 // Se crea el vínculo
      questionaryPerInstanceLink.questionaryPerInstance.id > 0 // Se crea la instancia a la ref
      questionaryPerInstanceLink.questionaryPerInstance.questionary.id == 1L // Se asigna el id de cuestionario
      questionaryPerInstanceLink.type == "UserTest" // Se identifica la clase
      questionaryPerInstanceLink.questionaryPerInstanceRef == 1L // Se asigna el id de la clase asignada
  }

  def "Crear una instancia de cuestionario para una clase que no implementa la interfaz Questionable"() {
    given:"Un usuario previamente creado y un cuestionario existente"
      def anotherUserTest = new AnotherUserTest().save()
      new Questionary().save(validate:false)
    when:"Creamos asignamos una instancia de cuestionario a un usuario"
      def questionaryPerInstanceLink = service.createQuestionaryPerInstance(anotherUserTest,1L)
    then:
      Exception e = thrown()
      e.message == "Pelaz!"
  }

    def "Obtener el questionaryPerInstance de una entidad, mediante el titulo del cuestionario"() {
    given:"Dada una entidad con varios questionaryPerInstance"
     def userTest = new UserTest().save(validate:false)
     createManyQuestionaryPerInstance(userTest)
     def tituloABucar="groovy"
    when:"Buscar en la entidad el custionario con el nombre groovy"
      def questionaryPerInstance = service.findQuestionaryPerInstanceByTitle(userTest.id,tituloABucar)
    then:
      questionaryPerInstance!=null
      questionaryPerInstance.questionary.title==tituloABucar
  }

  def "Obtener el questionaryPerInstanceLink de una entidad, mediante el codeName del cuestionario"() {
    given:"Dada una entidad con varios questionaryPerInstance"
     def userTest = new UserTest().save(validate:false)
     createManyQuestionaryPerInstance(userTest)
     def codeNameABucar="groovy-initial"
    when:"Buscar en la entidad el custionario con el codeName groovy-initial"
      def questionaryPerInstanceLink = service.findQuestionaryPerInstanceByCodeName(userTest.id,codeNameABucar)
    then:
      questionaryPerInstanceLink.questionaryPerInstance!=null
      questionaryPerInstanceLink.questionaryPerInstance.questionary.codeName==codeNameABucar
  }

  private def createManyQuestionaryPerInstance(def instance){
    new Questionary(title:"groovy",codeName:"groovy-initial").save(validate:false)
    new Questionary(title:"grails",codeName:"groovy").save(validate:false)
    new Questionary(title:"java",codeName:"grails-initial").save(validate:false)
    def questionaryPerInstanceLink1 = service.createQuestionaryPerInstance(instance,1L)
    def questionaryPerInstanceLink2 = service.createQuestionaryPerInstance(instance,2L)
    def questionaryPerInstanceLink3 = service.createQuestionaryPerInstance(instance,3L)
  }
}
