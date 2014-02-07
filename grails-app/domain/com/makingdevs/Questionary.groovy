package com.makingdevs

@groovy.transform.ToString
class Questionary {

  String title
  String description
  String codeName

  static constraints = {
    title blank:false, size:1..100
    description blank:false, size:1..300
    codeName blank:false, size:1..100
  }

  static hasMany = [questions : Question]
}