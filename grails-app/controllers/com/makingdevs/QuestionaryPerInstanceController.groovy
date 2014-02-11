package com.makingdevs
import com.makingdevs.*

class QuestionaryPerInstanceController {

  def questionaryPerInstanceLinkService

  def agregar(){
    def clazzIntance = Class.forName(params.clazz).newInstance()
    def instance = clazzIntance.get(params.instanceId)
    def questionaryPerInstanceLink=questionaryPerInstanceLinkService.createQuestionaryPerInstance(instance,params.questionaryId.toLong())
    redirect(controller: "userTest", action: "show", id:"1")
  }

  def ver(){

  }

}