package com.makingdevs

import grails.test.mixin.*
import spock.lang.*

@TestFor(QuestionAndAnswerService)
@Mock([Question, Answer])
class QuestionAndAnswerServiceSpec extends Specification {

  QuestionService questionService = Mock(QuestionService)
  AnswerService answerService = Mock(AnswerService)
  TagsService tagsService = Mock(TagsService)

  def setup(){
    service.questionService = questionService
    service.answerService = answerService
    service.tagsService = tagsService
  }

  @Unroll
  def "Given a full text generate the question and answers"() {
    given:
      def fullQuestion = """#MULTIPLE_CHOICE What is Groovy?
      (*) un fw
      () un lenguaje
      ( ) una herramienta
    """
    and:
      Answer answer = new Answer(description:"X",solution:false)
      Question question = Mock(Question)
      question = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE, answers:[])
      question.metaClass.addToAnswers {
        answers.add(answer)
      }
   when:
      tagsService.addTagsToAQuestionFromSimpleText(_,_) >> []
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >> []
      answerService.buildAnswerFromText(_) >>> [answer, answer, answer]
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
    then:
      questions[0].id > 0
      3 * answerService.buildAnswerFromText(_)
  }

  def "Should get lines from full question"(){
    given:"A full question"
      def fullQuestion = """#MULTIPLE_CHOICE What is Groovy?
        (*) un fw
        () un lenguaje
        ( ) una herramienta
      """
    when:"Process full question"
      def lines = service.getLines(fullQuestion)
    then:"Expect the lines"
      lines.size() == 4
  }

  def "Should thrown an exception for question without close tag <pre>"(){
    given:"A full question"
      def fullQuestion = """#MULTIPLE_CHOICE What is Groovy?
        <pre>
          def list = [1,2,3]
        (*) un fw
        () un lenguaje
        ( ) una herramienta
      """
    when:"Process full question"
      def lines = service.getLines(fullQuestion)
    then:"Expect an exception"
      thrown Exception
  }

  def "Should build a question with answers from the lines"(){
    given:"A lines"
      def lines = ["#MULTIPLE_CHOICE What is Groovy?", "(*) un fw", "() un lenguaje", "( ) una herramienta"]
    and:
      Answer answer = new Answer(description:"X",solution:false)
      Question question = Mock(Question)
      question = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE, answers:[])
      question.metaClass.addToAnswers {
        answers.add(answer)
      }
    and:
      tagsService.addTagsToAQuestionFromSimpleText(_,_) >> []
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >> []
      answerService.buildAnswerFromText(_) >>> [answer, answer, answer]
    when:"building question"
      def questions = service.buildQuestions(lines)
    then:"Expect a questions with answers"
      questions.size() == 1
      3 * answerService.buildAnswerFromText(_)
  }

  @Unroll
  def "Given a full text generate the questions with their answers"(){
    given:
      def fullQuestions = """#MULTIPLE_CHOICE What is Groovy?
      (*) un fw
      () un lenguaje
      ( ) una herramienta
      #MULTIPLE_RESPONSE What is Grails?
      [*] un framework
      [] una herramienta del sistema operativo
    """
    and:
      Answer answerA1 = new Answer(description:"un fw",solution:true)
      Answer answerB1 = new Answer(description:"un framework",solution:true)
      Question questionA = Mock(Question)
      questionA = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE, answers:[])
      questionA.metaClass.addToAnswers {
        answers.add(answerA1)
      }
      Question questionB = Mock(Question)
      questionB = new Question(description:"What is Grails?",questionType:QuestionType.MULTIPLE_RESPONSE, answers:[])
      questionB.metaClass.addToAnswers {
        answers.add(answerB1)
      }

      tagsService.addTagsToAQuestionFromSimpleText(_,_) >>> [[],[]]
      questionService.buildQuestionFromText(_) >>> [questionA, questionB]
      questionService.getTagsFromText(_) >>> [[],[]]
      answerService.buildAnswerFromText(_) >>> [answerA1, answerA1, answerA1, answerB1, answerB1]
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestions)
    then:
      questions.size() == 2
      5 * answerService.buildAnswerFromText(_)
      2 * questionService.buildQuestionFromText(_)
  }

  @Ignore
  @Unroll
  def "Test creation with simple String"() {
    given:
      def fullQuestions = "#MULTIPLE_CHOICE What is Groovy?\n(*) un fw\n() un lenguaje\n( ) una herramienta\n#MULTIPLE_RESPONSE What is Grails?\n[*] un framework\n[] una herramienta del sistema operativo"
    and:
      Answer answer = new Answer(description:"X",solution:false)
      Question question = Mock(Question)
      question = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE, answers:[])
      question.metaClass.addToAnswers {
        answers.add(answer)
      }
      tagsService.addTagsToAQuestionFromSimpleText(_,_) >>> [[],[]]
      questionService.buildQuestionFromText(_) >>> [question, question]
      questionService.getTagsFromText(_) >>> [[],[]]
      answerService.buildAnswerFromText(_) >>> [answer, answer, answer, answer, answer]
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestions)
    then:
      questions.size() == 2
      5 * answerService.buildAnswerFromText(_)
      2 * questionService.buildQuestionFromText(_)
  }

  @Unroll
  def "Given a text generate the question with their answers and tags"() {
    given:
      def fullQuestion = _fullQuestion
    and:
      def currentTags = []
      Answer answer = new Answer(description:"X",solution:false)
      Question question = Mock(Question)
      question = new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE, answers:[])
      question.metaClass.addToAnswers {
        answers.add(answer)
      }

      tagsService.addTagsToAQuestionFromSimpleText(_,_) >> []
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >>> []
      answerService.buildAnswerFromText(_) >>> [answer, answer]

      currentTags = _currentTags
      question.metaClass.getTags { currentTags }
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
    then:
      questions.size() == 1
      2 * answerService.buildAnswerFromText(_)
      questions[0].tags == _currentTags
    where:
      _fullQuestion                                                                   ||  _currentTags
      "#MULTIPLE_CHOICE What is Groovy? [groovy,language]\n() un fw\n(*) un lenguaje" ||  ['groovy','language']
  }

  @Unroll
  def "Given a text with integrated code generate the question with their answers"(){
    given:
      def fullQuestion = """#MULTIPLE_CHOICE What is Groovy?
      <pre>
        def list = [3,5,3]

        println list
      </pre>
      (*) un fw
      () un lenguaje
      ( ) una herramienta
      """
    and:

      Answer answer = new Answer(description:"X",solution:false)
      Question question = Mock(Question)
      question = new Question(description:"""What is Groovy?
        <pre>
          def list = [3,5,3]
          println list
        </pre>
        (*) un fw
        () un lenguaje
        ( ) una herramienta
      """,questionType:QuestionType.MULTIPLE_CHOICE, answers:[])
      question.metaClass.addToAnswers {
        answers.add(answer)
      }

      tagsService.addTagsToAQuestionFromSimpleText(_,_) >> []
      questionService.buildQuestionFromText(_) >> question
      questionService.getTagsFromText(_) >>> []
      answerService.buildAnswerFromText(_) >>> [answer, answer, answer]
    when:
      def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestion)
    then:
      questions.size() == 1
      3 * answerService.buildAnswerFromText(_)
  }
}
