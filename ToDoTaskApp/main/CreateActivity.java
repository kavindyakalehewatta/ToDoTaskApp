package com.example.todotaskapp;

import static androidx.core.view.ViewGroupKt.isNotEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
//import kotlinx.android.synthetic.main.activity_create.*
import androidx.activity.EdgeToEdge;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import kotlinx.coroutines.DelicateCoroutinesApi;
import kotlinx.coroutines.GlobalScope;
//import kotlinx.coroutines.launch;

public class CreateActivity extends AppCompatActivity {
private lateinit var database: myDatabase


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        database= Room.databaseBuilder(
                getApplicationContext(),myDatabase::class.java, "To_Do"
        ).build()

        save_button.setOnClickListner{
            if (create_title.text.toString().trim{it<=""}.isNotEmpty()
           && create_priority.text.toString().trim{it<=" "}.isNotEmpty())
            {
                var title=create_title.getText().toString()
                var priority=create_priority.getText().toString()
                DataObject.setData(title.priority)

                //coroutine
                GlobalScope.launch {

                database.dao().insertTask(Entity(id:0, title, priority))
            }

                GlobalScope.launch {
                Log.i("harsh",database.dao().getTasks().toString())
                }
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}