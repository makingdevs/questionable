package grails.plugins.questionable

class QuestionableGrailsPlugin {
    def grailsVersion = "2.3 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp",
        "grails-app/controllers/com/makingdevs/UserTestController.groovy",
        "grails-app/domain/com/makingdevs/UserTest.groovy",
        "grails-app/domain/com/makingdevs/AnotherUserTest.groovy",
        "grails-app/views/userTest/*"
    ]


    def author = "Jorge Acosta Lemus"
    def authorEmail = "jorge@makingdevs.com"
    def description = '''\
That plugin add questionarys for evaluate something
'''
    def documentation = "https://github.com/makingdevs/questionable"
    def license = "APACHE"
    def organization = [ name: "MakingDevs", url: "http://makingdevs.com/" ]

    def developers = [
      [ name: "Jorge Acosta Lemus", email: "jorge@makingdevs.com" ],
      [ name: "Sergio Rodríguez", email: "sergio@makingdevs.com" ],
      [ name: "Felipe Juárez", email: "felipe@makingdevs.com" ],
      [ name: "Rodrigo Martínez", email: "rockdrigo.mtz@gmail.com" ],
      [ name: "José Juan Reyes", email: "juan@makingdevs.com" ],
      [ name: "Marlen Rodríguez Reyes", email: "marlen@makingdevs.com"]
    ]

    def issueManagement = [ system: "GITHUB", url: "https://github.com/makingdevs/questionable/issues" ]

    def scm = [ url: "https://github.com/makingdevs/questionable" ]


    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { ctx ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }
}
