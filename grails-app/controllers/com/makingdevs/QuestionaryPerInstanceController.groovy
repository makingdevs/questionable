package com.makingdevs
import com.makingdevs.*

class QuestionaryPerInstanceController {

  def questionaryPerInstanceLinkService

  def agregar(){
    def clazzIntance = Class.forName(params.clazz).newInstance()
    def instance = clazzIntance.get(params.instanceId)
    def questionaryPerInstanceLink=questionaryPerInstanceLinkService.createQuestionaryPerInstance(instance,params.questionaryId.toLong())
    def url=params.url-grailsApplication.metadata.'app.name'-'/'
    redirect(url:url)
  }
}