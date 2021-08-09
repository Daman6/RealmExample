package com.example.realmexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_main.*
import org.bson.codecs.IntegerCodec
import kotlin.jvm.internal.MagicApiIntrinsics

class RegisterActivity : AppCompatActivity() {


    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realm = Realm.getDefaultInstance()

        saveUser.setOnClickListener {
            if (name.text.isNullOrEmpty()){
                showToast("Enter Name")
            }else if (email.text.isNullOrEmpty()){
                showToast("Enter Email")
            }else if (password.text.isNullOrEmpty()){
                showToast("Enter Password")
            }else if (confirm_Password.text.isNullOrEmpty() &&  !confirm_Password.text.toString().equals(password.text.toString())){
                showToast("Enter Confirm Password or Doesn't Match")
            }else if (address.text.isNullOrEmpty()){
                showToast("Enter Address")
            }else if (state.text.isNullOrEmpty()){
                showToast("Enter State")
            } else if (city.text.isNullOrEmpty()){
                showToast("Enter State")
            }else if (aadhar.text.isNullOrEmpty()){
                showToast("Enter Address")
            }else if (phoneNo.text.isNullOrEmpty()) {
                showToast("Enter State")
            } else{
                addUser()
            }
        }

    }

    fun addUser() {
        realm.beginTransaction()
        try {
            val nextId: Long = realm.where(MyRealmObject::class.java).count() + 1
            val user = realm.createObject(MyRealmObject::class.java, nextId)
            user.setName(name.text.toString())
            user.setEmail(email.text.toString())
            user.setPassword(password.text.toString())
            user.setConfirmPassword(confirm_Password.text.toString())
            user.setAddress(address.text.toString())
            user.setState(state.text.toString())
            user.setCity(city.text.toString())
            user.setAadharNo(Integer.parseInt(aadhar.text.toString()))
            user.setPhoneNo(Integer.parseInt(phoneNo.text.toString()))
            realm.commitTransaction()
            showToast("user added")

            val intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("ID",nextId)
            Log.d("TAG",nextId.toString())
            startActivity(intent)
            finish()

        } catch (e: RealmException) {
            Log.d("TAG", e.toString())
        }
    }

//    fun viewUsers() {
//        val realmResult: List<MyRealmObject> = realm.where(MyRealmObject::class.java).findAll()
//        var k = ""
//        var i = 0
//        for (i in realmResult.indices) {
//
//            var id: Long = realmResult[i].getId()
//            val name: String = realmResult.get(i).getName()
//            var email: String = realmResult.get(i).getEmail()
//            var address: String = realmResult.get(i).getAddress()
//            var state: String = realmResult.get(i).getState()
//            k = k + "ID=$id Name=$name Email=$email Address=$address State=$state \n"
//        }
//        if (k.isEmpty()){
//            showToast("Empty")
//            result.text ="Empty"
//        }else{
//            result.text = k
//            showToast(k)
//            deleteAllSchema()
//        }

//    }
    fun deleteAllSchema() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}