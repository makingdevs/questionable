package com.makingdevs

class Answer implements Comparable{

	String description
	Boolean solution

	static constraints = {
		description blank:false, size:1..1000
	}

	static belongsTo = [question: Question]

	int compareTo(q){
		this.id <=> q.id
	}

}
