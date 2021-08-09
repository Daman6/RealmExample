package com.example.realmexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){
    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        realm = Realm.getDefaultInstance()

        loginBtn.setOnClickListener {
            login()
        }
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                showToast("Checked")
            } else {
                showToast("unchecked")
            }
        }

        RegisterBtn.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(){
        if(loginEmail.text.isNullOrEmpty()){
            showToast("Enter Email")
        }else if(loginPassword.text.isNullOrEmpty()){
            showToast("Enter Password")
        }else{
            checkCredential()
        }
    }

    fun checkCredential(){
        //val realmResult: List<MyRealmObject> = realm.where(MyRealmObject::class.java).equalTo("email",loginEmail.text.toString()).findAll()
        val realmResult: List<MyRealmObject> = realm.where(MyRealmObject::class.java).findAll()
        var userdetails = ""
        var i = 0
        for (i in realmResult.indices) {

            if (loginEmail.text.toString().equals(realmResult[i].getEmail()) && loginPassword.text.toString().equals(realmResult[i].getPassword())){
                val id: Long = realmResult[i].getId()
                val intent = Intent(this,HomeActivity::class.java)
                intent.putExtra("ID",id)
                startActivity(intent)
                finish()
                showToast("Login Successfully")
            }
//            var id: Long = realmResult[i].getId()
//            val name: String = realmResult.get(i).getName()
//            var email: String = realmResult.get(i).getEmail()
//            var address: String = realmResult.get(i).getAddress()
//            var state: String = realmResult.get(i).getState()
//            userdetails = userdetails + "ID=$id Name=$name Email=$email Address=$address State=$state \n"
//            val intent = Intent(this,HomeActivity::class.java)
//            intent.putExtra("userdata",userdetails)
//            startActivity(intent)
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}