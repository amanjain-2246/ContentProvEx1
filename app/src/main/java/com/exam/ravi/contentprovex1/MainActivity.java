package com.exam.ravi.contentprovex1;

import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.net.Uri;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.exam.amanj.contentprovex1.R.layout.activity_main);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        String name = ((EditText)findViewById(com.exam.amanj.contentprovex1.R.id.editText)).getText().toString();
        String grade =  ((EditText)findViewById(com.exam.amanj.contentprovex1.R.id.editText2)).getText().toString();
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,name);
        values.put(StudentsProvider.GRADE, grade);
        ContentResolver cr = getContentResolver();
        Uri uri = cr.insert(StudentsProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(),uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {

        // Retrieve student records
        String URL = "content://com.example.provider.College/students";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
