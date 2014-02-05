package com.makingdevs

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(QuestionaryPerInstanceLinkService)
@Mock([UserTest,Questionary,QuestionaryPerInstanceService,QuestionaryPerInstance,QuestionaryPerInstanceLink])
class QuestionaryPerInstanceLinkServiceSpec extends Specification {

  def "Crear una instancia de cuestionario para una clase que implementa la interfaz Questionable"() {
    given:"Un usuario previamente creado y un cuestionario existente"
      def userTest = new UserTest().save()
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
}
