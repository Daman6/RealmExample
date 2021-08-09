package com.example.realmexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    var ID: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        realm = Realm.getDefaultInstance()
        viewUsers()


        logoutBtn.setOnClickListener {
            viewUsers()
        }
    }

    fun viewUsers() {
        val realmResult: List<MyRealmObject> = realm.where(MyRealmObject::class.java).findAll()
        var user = ""
        var i = 0
        ID = intent.getLongExtra("ID",0)
        Log.d("TAG",ID.toString())

        for (i in realmResult.indices) {

            if (ID!! == realmResult[i].getId()) {
                val id: Long = realmResult[i].getId()
                val name: String = realmResult.get(i).getName()
                val email: String = realmResult.get(i).getEmail()
                val address: String = realmResult.get(i).getAddress()
                val state: String = realmResult.get(i).getState()
                val city: String = realmResult.get(i).getCity()
                val aadhar: Int = realmResult.get(i).getAadharNO()
                val phone: Int = realmResult.get(i).getPhoneNO()

                user = user + "ID=$id \n  Name=$name \n Email=$email \n Address=$address \n State=$state \n City=$city \n Aadhar=$aadhar \n Phone No $phone"
            }
        }
        if (user.isEmpty()){
            showToast("Empty")
            dataResult.text ="Empty"
        }else{
            showToast(user)
            dataResult.text = user
        }

    }
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}