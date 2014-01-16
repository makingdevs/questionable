package com.makingdevs

import static org.junit.Assert.*
import org.junit.*
import spock.lang.Specification


@TestFor(QuestionService)
@Mock([Question, Answer])
class QuestionServiceSpec extends Specification{

  def "Evaluar una pregunta abierta"(){
    given:
      def question = anOpenQuestion()
    when:
      def evaluate = service.evaluateAnswer(question.id,_answer)
    then:
      evaluate == rating
      question.questionType == QuestionType.OPEN
    where:
      _answer                                       || rating
      "Esta es una respuesta abierta a la pregunta" || 1.0
      ""                                            || 0.0
  }

  def "Evaluar una pregunta con opciones de falso o verdadero"(){
    given:
      def question = aTrueFalseQuestion(rightAnswer)
    when:
      def answer = _answer
      def evaluate = service.evaluateAnswer(question.id, answer)
    then:
      evaluate == rating
      question.questionType == QuestionType.TRUE_FALSE
    where: 
      rightAnswer | _answer || rating
      false       | true    || 0.0
      false       | false   || 1.0
      true        | true    || 1.0
      true        | false   || 0.0
  }

  def "Evaluar una pregunta con multiples opciones siendo una la correcta"(){
    given:
      def question = aMultipleChoiceQuestion()
    when:
      def answer = Answer.findByDescriptionLike("%$_answer%")
      def evaluate = service.evaluateAnswer(question.id, answer.id)
    then:
      evaluate == rating
      question.questionType == QuestionType.MULTIPLE_CHOICE
    where:
      _answer     || rating
      "Dinamico"  || 1.0
      "Estatico"  || 0.0
      "Funcional" || 0.0
  }

  def "Evaluar una pregunta de multi seleccion con 3 respuestas"(){
    given:
      def question = aMultipleResponse()
    when:
      def answers = Answer.findAllByDescriptionInList(_answer_user)
      def evaluate = service.evaluateAnswer(question.id, answers.id)
    then:
      evaluate == rating
      question.questionType == QuestionType.MULTIPLE_RESPONSE
    where:
                    _answer_user                                                 || rating
      ["Lenguaje dinamico","Lenguaje orientado a objetos"]                       || 1.0
      ["Lenguaje dinamico","Lenguaje orientado a objetos","Lenguaje funcional"]  || 0.6
      ["Lenguaje dinamico","Lenguaje funcional"]                                 || 0.3
      ["Lenguaje orientado a objetos","Lenguaje funcional"]                      || 0.3
      ["Lenguaje funcional"]                                                     || 0.0
      []                                                                         || 0.0
  }

  private Question anOpenQuestion(){
    new Question(description:"¿Es esta una pregunta abierta?, Describa",questionType:QuestionType.OPEN).save()
  }

  private Question aTrueFalseQuestion(rightAnswer){
    def question = new Question(description:"¿Falso es igual a verdadero?", questionType:QuestionType.TRUE_FALSE)
    def answer = new Answer(solution:rightAnswer)
    question.addToAnswers(answer)
    question.save(validate:false)
    question
  }

  private Question aMultipleChoiceQuestion(){
    def question = new Question(description:"¿Groovy es un lenguaje de tipo?", questionType:QuestionType.MULTIPLE_CHOICE)
    def answer1 = new Answer(description:"Dinamico", solution:true)
    def answer2 = new Answer(description:"Estatico", solution:false)
    def answer3 = new Answer(description:"Funcional", solution:false)
    question.addToAnswers(answer1)
    question.addToAnswers(answer2)
    question.addToAnswers(answer3)
    question.save(validate:false)
    question
  }

  private Question aMultipleResponse(){
    def question =new Question(description:"¿Ques es Groovy?",questionType:QuestionType.MULTIPLE_RESPONSE)
    def answer1 = new Answer(description:"Lenguaje dinamico", solution:true)
    def answer2 = new Answer(description:"Lenguaje orientado a objetos", solution:true)
    def answer3 = new Answer(description:"Lenguaje funcional", solution:false)
    question.addToAnswers(answer1)
    question.addToAnswers(answer2)
    question.addToAnswers(answer3)
    question.save(validate:false)
    question
  }

}