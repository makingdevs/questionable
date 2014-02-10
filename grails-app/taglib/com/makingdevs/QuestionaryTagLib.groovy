package com.makingdevs

class QuestionaryTagLib {
    
  def showQuestionaryForThisInstance = { attrs, body ->
    def listQuestioanryAvailable=Questionary.list()
    out << "<ul>"
      listQuestioanryAvailable?.each { questionary ->
        out << """
            <li>${g.link(controller:'questionary',action:'answerQuestionary',id:"${questionary.id}"){"${questionary.codeName}"}}
            <a class='btn-small btn-primary' href=''><i class='icon-plus-sign'></i>Hola</a>
            <a class='btn-small btn-primary' href=''><i class='icon-plus-sign'></i>Hola</a>
            </li>
        """
      }
    out << "</ul>"
  }
}
