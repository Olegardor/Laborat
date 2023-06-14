package com.example.laba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Activity3 extends AppCompatActivity {//
    ContactItem contact;
    String FILE_NAME = "contacts.json";//название файла
    List<ContactItem> contacts = new ArrayList<>();//создание списка контактов
    Intent intent = new Intent(this, SeeContactActivity.class);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Bundle arguments = getIntent().getExtras();
        contact = (ContactItem) arguments.getParcelable("contact");
        TextView itemText = findViewById(R.id.itemView);
        itemText.setText(contact.toString());
        read();
        resetItemsView();
    }

    private void resetItemsView() {//пересоздаем список где выводятся объекты
        ListView list = findViewById(R.id.itemsView);
        ContactsAdapter adapter = new ContactsAdapter(this, R.layout.countact_view, (ArrayList<ContactItem>) contacts);//переменная для переноса из List в ListView
        list.setAdapter(adapter);//переносим данные в itemsView
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {//нажатие на пункты списка
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactItem selected = (ContactItem) adapterView.getItemAtPosition(i);//объект, выбранный при нажатии
                intent.putExtra("contact", selected);//
                startActivity(intent);//открываем выбранный intent
            }
        });
    }

    private File getExternalPath() {
        return new File(getExternalFilesDir(null), FILE_NAME);//берется путь для внутренней папки приложения
    }

    //Кнопка "Запомнить"
    public void save(View view) {
        contacts.add(contact);//добавить контакт в список
        FileOutputStream fos = null;//поток вывода файла
        try {
            Gson gson = new Gson();//объявление класса котроый может перевести объект в json и обратно
            ContactItem.DataItems dataItems = new ContactItem.DataItems();//создаем объект dataItems
            dataItems.setData(contacts);//добавляем список в dataItems
            String text = gson.toJson(dataItems);//записываем в строку наш объект  в формате json

            fos = new FileOutputStream(getExternalPath());//инициализация потока
            fos.write(text.getBytes());//запись в поток(в файл)
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();//выводи уведовмление о том что все сохранилось
        }
        catch (IOException ex) {//на случай появления ошибки при записи в файл выводятся след сообщзения
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();//
        }
        finally {//блок с закрытием потока
            try {
                if (fos != null)//
                    fos.close();//
            }
            catch (IOException ex) {//

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();// уведомление об ощибке закрытия потока на случай когда она появилась
            }
        }
        resetItemsView();//пересоздаем список где выводятся объекты
    }

    public void read() {//считывание с файла
        try (FileInputStream fileInputStream = new FileInputStream(getExternalPath());//поток вывода из файла
             InputStreamReader streamReader = new InputStreamReader(fileInputStream)) {//объект который читает поток вывода

            Gson gson = new Gson();//объявление класса котроый может перевести объект в json и обратно
            ContactItem.DataItems dataItems = gson.fromJson(streamReader, ContactItem.DataItems.class);//считываем из json и переделывавем в объект DataItems
            Toast.makeText(this, "Файл загружен", Toast.LENGTH_SHORT).show();//вывод  уведомления о зарузке файла
            contacts = dataItems.getData();//получаем лсит контактов из объекта прослойки dataItems
        } catch (IOException ex) {//проверка считывания файла
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();//
        }

    }
}