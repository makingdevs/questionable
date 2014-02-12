package com.makingdevs

class QuestionaryTagLib {
    
  def showQuestionaryForThisInstance = { attrs, body ->
    def listQuestionaryAvailable=Questionary.list()
    def questionaryLinks = QuestionaryPerInstanceLink
      .findAllWhere(type:attrs.instance.class.getSimpleName(),
      questionaryPerInstanceRef:attrs.instance.id)
    out << render(template:"/questionaryPerInstance/questionaryForThisInstance", 
      model:[listQuestionaryAvailable:listQuestionaryAvailable,
            instance:attrs.instance,
            questionaryLinks:questionaryLinks])
  }
}
