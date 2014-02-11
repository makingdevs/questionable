package com.makingdevs

class QuestionaryTagLib {
    
  def showQuestionaryForThisInstance = { attrs, body ->
    def listQuestionaryAvailable=Questionary.list()
    def lista=[]
    lista = QuestionaryPerInstanceLink.findByType(attrs.instance.class.getSimpleName())
    out << render(template:"/questionaryPerInstance/questionaryForThisInstance", 
      model:[listQuestionaryAvailable:listQuestionaryAvailable,
            instance:attrs.instance,
            lista:lista])
  }
}
