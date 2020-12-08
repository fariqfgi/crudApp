package com.example.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudapp.Database.AppRoomDB
import com.example.crudapp.Database.Helm
import kotlinx.android.synthetic.main.activity_edit_helm.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditHelmActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_helm)
        setupListener()
    }

    fun setupListener(){
        btn_saveHelm.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.helmDao().addHelm(
                    Helm(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
    }
}