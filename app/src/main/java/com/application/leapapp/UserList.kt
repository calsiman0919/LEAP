package com.application.leapapp

class UserList {

    var name: String? = null
    //var lastName: String? = null
    var email: String? = null
    var uid: String? = null

    constructor(){}

    constructor(name: String?, email: String?, uid: String?){
        this.name = name
        //this.lastName = lastName
        this.email = email
        this.uid = uid
    }
}