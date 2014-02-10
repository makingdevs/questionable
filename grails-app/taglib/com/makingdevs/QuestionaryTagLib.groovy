package com.makingdevs

class QuestionaryTagLib {
    
  def showQuestionaryForThisInstance = { attrs, body ->
    def listQuestioanryAvailable=Questionary.list()
    out << render(template:"/questionaryPerInstance/questionaryForThisInstance", 
      model:[listQuestioanryAvailable:listQuestioanryAvailable,
            idInstance:attrs.instance.id,
            typeInstance:attrs.instance.class.getSimpleName()])
  }
}
