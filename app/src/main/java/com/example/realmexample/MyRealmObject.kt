package com.example.realmexample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class MyRealmObject :RealmObject(){
    @PrimaryKey
    private var id:Long = 0
    private lateinit var name:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var confirmPassword:String
    private lateinit var address:String
    private lateinit var state:String
    private lateinit var city:String
    private  var aadharNo:Int = 0
    private var phoneNo:Int = 0

    fun setId(id:Long){
        this.id = id
    }
    fun getId() :Long{
        return id
    }
    fun setName(name:String){
        this.name = name
    }
    fun getName() :String{
        return name
    }
    fun setEmail(email:String){
        this.email = email
    }
    fun getEmail() :String{
        return email
    }
    fun setPassword(password:String){
        this.password = password
    }
    fun getPassword() :String{
        return password
    }
    fun setConfirmPassword(confirmPassword:String){
        this.confirmPassword = confirmPassword
    }
    fun getConfirmPassword() :String{
        return confirmPassword
    }
    fun setAddress(address:String){
        this.address = address
    }
    fun getAddress() :String{
        return address
    }
    fun setState(state:String){
        this.state = state
    }
    fun getState() :String{
        return state
    }
    fun setCity(city:String){
        this.city = city
    }
    fun getCity() :String{
        return city
    }

    fun setAadharNo(aadharNo:Int){
        this.aadharNo = aadharNo
    }
    fun getAadharNO() :Int{
        return aadharNo
    }

    fun setPhoneNo(phoneNo:Int){
        this.phoneNo = phoneNo
    }
    fun getPhoneNO() :Int{
        return phoneNo
    }

}
