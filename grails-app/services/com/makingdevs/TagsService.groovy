package com.makingdevs

import grails.transaction.Transactional

@Transactional
class TagsService {

  def addTagsToAQuestionFromSimpleText(Question question, List tags){
    question.addTags(tags)
  }
  def addTagsToAQuestionFromSimpleText(Question question, String tags){
    question.parseTags(tags)
  }

  def updateTagsToAQuestionFromSimpleText(Question question, String tags){
    question.setTags(tags.tokenize(","))
  }

}
