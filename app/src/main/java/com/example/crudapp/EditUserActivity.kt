package com.example.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudapp.Database.AppRoomDB
import com.example.crudapp.Database.User
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditUserActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        setupListener()
    }

    fun setupListener(){
        btn_saveUser.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().addUser(
                    User(0, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
    }
}