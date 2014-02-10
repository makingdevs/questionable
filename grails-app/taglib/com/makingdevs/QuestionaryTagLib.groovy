package com.makingdevs

class QuestionaryTagLib {
    
  def showQuestionaryForThisInstance = { attrs, body ->
    def listQuestioanryAvailable=Questionary.list()
    out << "<ul>"
      listQuestioanryAvailable.each { questionary ->
        out << """\
          <li>${g.link(controller:'questionary',action:'answerQuestionary',id:"${questionary.id}"){"${questionary.codeName}"}}</li>
        """
      }
    out << "</ul>"
  }
}
