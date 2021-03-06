package com.makingdevs

import grails.plugins.taggable.*

class Question implements Taggable,Comparable{

	String description
	QuestionType questionType

	static constraints = {
		description blank:false, size:1..1000
	}

	static hasMany = [answers : Answer]

  int compareTo(q){
    this.id <=> q.id
  }
}
