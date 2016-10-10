package com.example.user.ybit;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class newNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        //EditText et1 = (EditText) findViewById(R.id.editText2);
        TextView dateTime = (TextView) findViewById(R.id.dateTime);
        Calendar rightNow = Calendar.getInstance();
        String DateTime = Integer.toString(rightNow.YEAR)+"/"+Integer.toString(rightNow.MONTH)+"/"+Integer.toString(rightNow.DATE);
        dateTime.setText(DateTime);
    }
    public void onBackPressed(){
        EditText et1 = (EditText) findViewById(R.id.noteContent);
        String content = et1.getText().toString();
        int noteId = 100;
        String title  = findViewById(R.id.titleText).toString();
        String dateTime = findViewById(R.id.dateTime).toString();
        SQLiteDatabase db = openOrCreateDatabase("notes",MODE_PRIVATE,null);
        /*
         * I wanted the db table to have a PK with auto increment so that we can modify each row with ease
         * so try to make id as INT PRIMARY_KEY AUTO_INCREMENT, it was showing some error for me
         * And if AUTO INCREMENT works no need to insert the id manually
         */
        String sqlStr = "CREATE TABLE IF NOT EXISTS `allNotes` ( `id` INT,"+
                "noteName VARCHAR(20) NOT NULL ,content TEXT NOT NULL , `createdOn` VARCHAR(20) NOT NULL );";

        db.execSQL(sqlStr);
        ContentValues cv = new ContentValues();
        cv.put("noteName",title);
        cv.put("id",noteId);
        cv.put("content",content);
        cv.put("createdOn",dateTime);
        db.insert("allNotes",null,cv);
        noteId++;
        Toast.makeText(newNoteActivity.this,content,Toast.LENGTH_LONG).show();
        finish();
    }
}
