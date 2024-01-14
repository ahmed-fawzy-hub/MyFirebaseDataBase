package com.example.myfirebasedatabase

class Employees(val id:String,val firstname:String,val lastname:String,
                val address:String,val department:String) {
    constructor():this( "","","","",""){}
}