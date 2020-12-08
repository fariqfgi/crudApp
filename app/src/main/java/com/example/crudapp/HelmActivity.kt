package com.example.crudapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.crudapp.Database.AppRoomDB
import kotlinx.android.synthetic.main.activity_helm.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HelmActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helm)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allHelm = db.helmDao().getAllHelm()
            Log.d("HelmActivity", "dbResponse: $allHelm")
        }
    }

    fun setupListener() {
        btn_createHelm.setOnClickListener {
            startActivity(Intent(this, EditHelmActivity::class.java))
        }
    }
}