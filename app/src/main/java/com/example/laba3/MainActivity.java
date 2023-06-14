package com.example.laba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageAva;
    private final int Pick_image = 1;

    private Uri imageUri;
    ContactItem contact = new ContactItem();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageAva = findViewById(R.id.imageAva);

        char[] gI = getIntent().toString().toCharArray();
        for (int i = 0; i < getIntent().toString().length(); i++) {
            if (gI[i] == 'h' && gI[i + 1] == 'a' && gI[i + 2] == 's') {
                Bundle arguments = getIntent().getExtras();
                contact = (ContactItem) arguments.getParcelable("contact");
                break;
            }
        }
        restoreState();
    }

    //Кнопка "Далее"
    public void clickNext(View view) {
        Intent intent = new Intent(this, Activity2.class);
        contact.setName(((EditText) findViewById(R.id.editTextName)).getText().toString());
        contact.setSurname(((EditText) findViewById(R.id.editTextSurname)).getText().toString());
        contact.setMail(((EditText) findViewById(R.id.editTextMail)).getText().toString());
        contact.setIsMale(((RadioButton) findViewById(R.id.radioButtonMale)).isChecked());
        if(imageUri!=null)
            contact.setAva(imageUri.toString());
        intent.putExtra("contact", contact);
        startActivity(intent);
    }

    private void restoreState(){//обновленние состояния активити
        if(contact.getName()!=null)
            ((EditText)findViewById(R.id.editTextName)).setText(contact.getName());//выносим данные с класса в поле ввода
        if(contact.getSurname()!=null)
            ((EditText)findViewById(R.id.editTextSurname)).setText(contact.getSurname());
        if(contact.getMail()!=null)
            ((EditText)findViewById(R.id.editTextMail)).setText(contact.getMail());
        if(contact.getIsMale()!=null)
            ((RadioButton) findViewById(R.id.radioButtonMale)).isChecked();
        if(contact.getAva()!=null)
            imageAva.setImageURI(Uri.parse(contact.getAva()));
    }

    //Выбор изображения при нажатии на imageAva
    public void selectImage(View view) {
        //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        //Тип получаемых объектов - image:
        photoPickerIntent.setType("image/*");
        //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
        startActivityForResult(photoPickerIntent, Pick_image);
    }

    //Установка изображения в imageAva
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    imageUri = imageReturnedIntent.getData();
                    imageAva.setImageURI(imageUri);
                }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("contact", contact);//записываем данные для сохранения
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {//обновляем activity после сохранения onSaveInstanceState
        super.onRestoreInstanceState(savedInstanceState);
        contact = (ContactItem) savedInstanceState.getParcelable("contact");//достаем данные из Bundle outState
        restoreState();//
    }
}