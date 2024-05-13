package com.example.todotaskapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
           database = Room.databaseBuilder(
                   applicationContext,myDatabase::class.java, "To_Do"
           ).build()

            val pos=intent.getIntExtra("id",-1)
               if(pos!=-1){
                   val title= DataObject.getData(pos).title
                   val priority= DataObject.getData(pos).priority
                   create_title.setText(title)
                   create_priority .setText(priority)


                   delete_button.setoncliciklistener{
                       DataObject.deleteData(pos)

                        GlobalScope.Launch{
                           database.dao().dalateTask(
                                   Enitity(:pos+1,title,priority)
                           )
                       }

               myIntent()
                   }

                   update_button.setOnClickListner{
                       DataObject.updateData(pos,title,priority)
                       Toast.makeText(this, title+" "+priority, Toast.LENGTH_SHORT).show();
                       GlobalScope.Launch{
                           database.dao().updateTask(
                                   Entity(
                                           pos+1,create_title.text.toString()
                                       create_priority.text.toString()
                                   )
                           )
                       }

                       myIntent()
                   }
               }

    }

    fun myIntent(){
        val intent =Intent(   this,MainActivity::class.java)
        startActivity(intent);
    }
}