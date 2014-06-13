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
/*_
    void "Given a full text generate the question and answers"() {
    	given:
    	def fullQuestion = """\
    	#MULTIPLE_CHOICE What is Groovy?
    	(*) un fw
    	() un lenguaje
    	( ) una herramienta
    	#MULTIPLE_RESPONSE What is Groovy?
    	[*] un lenguaje dinamico
    	[] una herramiemnta del so
    	"""
    	and:
    	def questionServiceMock = mockFor(QuestionService)
    	questionServiceMock.demand.createQuestionFromText(2..2) = { t -> new Question().save(validate:false)}
    	questionServiceMock.demand.addAnswerToQuestionFromText(5..5) = { id,t -> new Answer().save(validate:false)}
    	service.questionService = questionServiceMock.createMock()
    	when:
    	def question = createQuestionWithAnswersFromSimpleText(fullQuestion)
    	questionServiceMock.verify()
    	then:
    	question.id > 0
    	question.answers.size() == 3
    }*/
}
