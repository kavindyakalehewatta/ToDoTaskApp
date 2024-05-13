package com.example.todotaskapp

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

//entity-table
//dao- queries
//database

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()
        database= Room.databaseBuilder(
            getApplicationContext(),myDatabase::class.java,"To_Do"
        ).build()


        add.setOnClickListener{
        val intent =Intent(this,CreateActivity::class.java)
            startActivity(intent)
        }
        deleteAll.setOnClickListener{
            DataObject.deleteAll()

            GlobalScope.launch{
                database.dao().deleteAll()
            }

            setRecyclerView()
        }
        setRecyclerView()

        fun setRecyclerView() {
    recycler_view.adapter = Adapter(DataObject.getAllData())
    reycler_view.LayoutManger = LinearLayoutManager(this)
}
    }
}
//enableEdgeToEdge()
// ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }