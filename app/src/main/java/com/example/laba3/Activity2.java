package com.example.laba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;

public class Activity2 extends AppCompatActivity {
    ContactItem contact;
    ListView listView10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle arguments = getIntent().getExtras();//Получаем intent и достаём переданные данные
        Log.d("intTest2", getIntent().toString());
        contact = (ContactItem) arguments.getParcelable("contact");//Сохраняем данные в contact
        restoreState();//обновленние состояния активити
        TextView prevText = findViewById(R.id.prevText);
        prevText.setText(contact.toString());//Выводим в поле полученные данные
        ImageView prevAva = findViewById(R.id.prevAva);//поле с картинокй в какое мы выводим картинку из предыдущей активити
        prevAva.setImageURI(Uri.parse(contact.getAva()));//вывод картинки в это поле=

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("contact", contact);//записываем данные для сохранения
        super.onSaveInstanceState(outState);
    }
    private void restoreState(){//обновленние состояния активити
        if(contact.getPhone()!=null)
            ((EditText)findViewById(R.id.editTextPhone)).setText(contact.getPhone());//выносим данные с класса в поле ввода
        if(contact.getAddress()!=null)
            ((EditText)findViewById(R.id.editTextAdress)).setText(contact.getAddress());
        if(contact.getVkLink()!=null)
            ((EditText)findViewById(R.id.editTextVK)).setText(contact.getVkLink());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {//обновляем activity после сохранения onSaveInstanceState
        super.onRestoreInstanceState(savedInstanceState);
        contact = (ContactItem) savedInstanceState.getParcelable("contact");//достаем данные из Bundle outState
        restoreState();//
    }

    //Кнопка "Далее"
    public void clickNext1(View view) {
        Intent intent = new Intent(this, Activity3.class);
        contact.setPhone(((EditText)findViewById(R.id.editTextPhone)).getText().toString());//запись данных из activity
        contact.setAddress(((EditText)findViewById(R.id.editTextAdress)).getText().toString());
        contact.setVkLink(((EditText)findViewById(R.id.editTextVK)).getText().toString());
        intent.putExtra("contact",contact);//передача данных в intent
        startActivity(intent);
    }

    //Кнопка "Назад"
    public void clickNext2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        contact.setPhone(((EditText)findViewById(R.id.editTextPhone)).getText().toString());//запись данных из activity
        contact.setAddress(((EditText)findViewById(R.id.editTextAdress)).getText().toString());
        contact.setVkLink(((EditText)findViewById(R.id.editTextVK)).getText().toString());
        intent.putExtra("contact", contact);
        startActivity(intent);

    }

}