package com.example.crudapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudapp.Database.AppRoomDB
import com.example.crudapp.Database.Constant
import com.example.crudapp.Database.Helm
import kotlinx.android.synthetic.main.activity_helm.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HelmActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var helmAdapter: HelmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helm)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadHelm()
    }

    fun loadHelm(){
        CoroutineScope(Dispatchers.IO).launch {
            val allHelm = db.helmDao().getAllHelm()
            Log.d("HelmActivity", "dbResponse: $allHelm")
            withContext(Dispatchers.Main) {
                helmAdapter.setData(allHelm)
            }
        }
    }

    fun setupListener() {
        btn_createHelm.setOnClickListener {
           intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        helmAdapter = HelmAdapter(arrayListOf(), object: HelmAdapter.OnAdapterListener {
            override fun onClick(helm: Helm) {
                // read detail
                intentEdit(helm.id, Constant.TYPE_READ)
            }

            override fun onDelete(helm: Helm) {
                // delete data
                deleteDialog(helm)
            }

            override fun onUpdate(helm: Helm) {
                // edit data
                intentEdit(helm.id, Constant.TYPE_UPDATE)
            }

        })
        list_helm.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = helmAdapter
        }
    }

    fun intentEdit(helmId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditHelmActivity::class.java)
                .putExtra("intent_id", helmId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(helm: Helm) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.helmDao().deleteHelm(helm)
                    loadHelm()
                }
            }
        }
        alertDialog.show()
    }
}