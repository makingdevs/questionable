package com.makingdevs

import grails.transaction.Transactional
import java.text.Normalizer
import java.text.Normalizer.Form

@Transactional
class QuestionAndAnswerService {

	def questionService
  def answerService

  def createQuestionWithAnswersFromSimpleText(fullQuestion){      

      def lines = fullQuestion.split('\n|\t')
      def answers = []
      def question
      
      lines = lines*.trim()      

      for(def i=1;i<lines.size();i++){
        def j = i-1
        if(lines[j]==~ /\#.+/){        
          while(!(isThisLineAnswer(lines[i]))){
            lines[j]+='\n'+lines[i]
            lines-=lines[i]         
          }
        }
      }
          	
      if(lines){
        lines.each{ line ->
          if(line.trim()){
            if(line.trim()[0]=="#")
              question = questionService.buildQuestionFromText(line.trim())
            else
              answers << answerService.buildAnswerFromText(line.trim())
          }
        }
      }
      
      answers.each { answer ->
        question.addToAnswers(answer)
      }

      question.save()
      question
    }

    def createQuestionsWithAnswersFromSimpleText(fullQuestions){
                  
      def questions = []
      def answers = []      
      def lines = fullQuestions.split('\n|\t')
      lines = lines*.trim()
      
      for(def i=1;i<lines.size();i++){
        def j = i-1
        if(lines[j]==~ /\#.+/){        
          while(!(isThisLineAnswer(lines[i]))){
            lines[j]+='\n'+lines[i]
            lines-=lines[i]         
          }
        }
      } 
                  
      if(lines){
        lines.each{ line ->
          log.error line
          if(isThisLineAQuestion(line)){
            if(answers && questions){                
              answers.each{ answer -> questions.last().addToAnswers(answer) }
              answers.clear()
            }
            def question = questionService.buildQuestionFromText(line.trim())
            questions << question
          }
          if(line.trim() && !isThisLineAQuestion(line)){
            answers << answerService.buildAnswerFromText(line.trim())
          }
        }
      }

      answers.each { answer ->
        questions.last().addToAnswers(answer)
      }

      questions*.save()
      questions
    }

    def isThisLineAQuestion(line){
      line.trim() && line.trim()[0]=="#"
    }

    def isThisLineAnswer(line){
      line ==~ /[\([\*|\ ]?\)|\[[\*|\s]?\]].+|F\*?|V\*?$/
    }
}
