package com.makingdevs

class QuestionaryTagLib {
    
  def showQuestionaryForThisInstance = { attrs, body ->
    def listQuestionaryAvailable=Questionary.list()
    out << render(template:"/questionaryPerInstance/questionaryForThisInstance", 
      model:[listQuestionaryAvailable:listQuestionaryAvailable,
            instance:attrs.instance])
  }
}
