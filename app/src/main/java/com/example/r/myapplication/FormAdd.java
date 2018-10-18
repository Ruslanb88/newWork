package com.example.r.myapplication;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.GridView;
import java.util.ArrayList;


public class FormAdd extends Activity implements OnClickListener{
    DBHelper dbHelper;
    Button btnUpdate, btnDB, btnAdd, btnRead, btnClear;
    EditText etName, etEmail, etID;
    GridView gv;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formadd);


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnDB = (Button) findViewById(R.id.btnDB);
        btnDB.setOnClickListener(this);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etID = (EditText) findViewById(R.id.etId);

        dbHelper = new DBHelper(this);

    }

    public void onClick(View v) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etID.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        ArrayList<String> data=new ArrayList<String>();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);


        switch (v.getId()) {
            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, email);

                long isinsert = database.insert(DBHelper.TABLE_CONTACTS,
                        null, contentValues);
                Log.i("is", String.valueOf(isinsert));
                break;


            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", email = " + cursor.getString(emailIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;

            case R.id.btnDB:

                gv = (GridView)findViewById(R.id.gridView1);
                gv.setAdapter(adapter);

                Cursor cursor1 = database.rawQuery(String.format("SELECT * FROM %s", DBHelper.TABLE_CONTACTS), null);
                Log.e("CURSOR", String.valueOf(cursor1.getCount()));


                while (cursor1.moveToNext()) {

                    int identifier = cursor1.getInt(0);
                    String name1 = cursor1.getString(1);
                    String email1 = cursor1.getString(2);

                    data.add(String.valueOf(identifier));
                    data.add(String.valueOf(name1));
                    data.add(String.valueOf(email1));

                    Log.e("identifier", String.valueOf(identifier));
                    Log.e("name1", String.valueOf(name1));
                    Log.e("email1", String.valueOf(email1));
                }
                adapter.notifyDataSetChanged();
            case R.id.btnUpdate:
                if (id.equalsIgnoreCase("")){
                    break;
                }
                contentValues.put(DBHelper.KEY_MAIL, email);
                contentValues.put(DBHelper.KEY_NAME, name);
                int updCount = database.update(DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_ID + "= ?", new String[] {id});
                Log.d("mLog", "updates rows count = " + updCount);
        }
        dbHelper.close();


     }

}
