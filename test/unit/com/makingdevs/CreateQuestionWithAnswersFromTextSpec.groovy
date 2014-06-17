package com.makingdevs

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*

@TestFor(QuestionAndAnswerService)
@Mock([Question,Answer])
class CreateQuestionWithAnswersFromTextSpec extends Specification {
    
    @Unroll
    def "Given a full text generate the question and answers"() {
    	given:
    	def fullQuestion = """\
    	#MULTIPLE_CHOICE What is Groovy?
    	(*) un fw
    	() un lenguaje
    	( ) una herramienta
    	"""
    	and:
        def questionServiceMock = mockFor(QuestionService)
        def answerServiceMock = mockFor(AnswerService)
        questionServiceMock.demand.buildQuestionFromText{ t ->
          new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
        }
        answerServiceMock.demand.buildAnswerFromText(3..3){ t ->
          new Answer(description:"X",solution:false)
        }
        service.questionService = questionServiceMock.createMock()
        service.answerService = answerServiceMock.createMock()
      when:
        def question = service.createQuestionWithAnswersFromSimpleText(fullQuestion)
        questionServiceMock.verify()
    	then:
        question.id > 0
        question.answers.size() == 3
    }

    @Unroll
    void "Given a full text generate the questions with their answers"() {
    	given:
    	def fullQuestions = """\
        #MULTIPLE_CHOICE What is Groovy?
        () un fw
        (*) un lenguaje
        ( ) una herramienta
        #MULTIPLE_RESPONSE What is Grails?
        [*] un framework
        [] una herramienta del sistema operativo
    	"""
    	and:
        def questionServiceMock = mockFor(QuestionService)
        def answerServiceMock = mockFor(AnswerService)
        questionServiceMock.demand.buildQuestionFromText(2..2){ t -> 
          new Question(description:"What is Groovy?",questionType:QuestionType.MULTIPLE_CHOICE)
        }
        answerServiceMock.demand.buildAnswerFromText(5..5){ t -> 
          new Answer(description:"X",solution:false)
        }
        service.questionService = questionServiceMock.createMock()
        service.answerService = answerServiceMock.createMock()
    	when:
        def questions = service.createQuestionsWithAnswersFromSimpleText(fullQuestions)
        questionServiceMock.verify()        
    	then:
        questions.size() == 2
        questions[0].answers.size() == 3 
        questions[1].answers.size() == 2
    }
}
