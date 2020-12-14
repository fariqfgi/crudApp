package com.example.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.crudapp.Database.AppRoomDB
import com.example.crudapp.Database.Constant
import com.example.crudapp.Database.Helm
import kotlinx.android.synthetic.main.activity_edit_helm.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditHelmActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var helmId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_helm)
        setupListener()
        setupView()
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

    fun setupView() {
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                btn_saveHelm.visibility = View.GONE
                getHelm()
            }
        }
    }

    fun getHelm() {
        helmId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
           val helms =  db.helmDao().getHelm( helmId )[0]
            txt_merk.setText( helms.merk )
            txt_stok.setText( helms.stok.toString() )
            txt_harga.setText( helms.harga.toString() )
        }
    }
}