package com.example.laba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SeeContactActivity extends AppCompatActivity {
    ContactItem contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_contact);
        Bundle arguments = getIntent().getExtras();
        contact = (ContactItem) arguments.getParcelable("contact");
        ImageView prevAva = findViewById(R.id.avatar);
        prevAva.setImageURI((Uri.parse(contact.getAva())));
        TextView text = findViewById(R.id.fullInfoView);
        text.setText(String.format("%s\n%s\n%s\n%s", contact.getName(), contact.getSurname(), contact.getAddress(), contact.getIsMale() ? "male" : "female"));
        Button vk = findViewById(R.id.vkButton);
        Button phone = findViewById(R.id.phoneButton);
        Button mail = findViewById(R.id.mailButton);
        vk.setText(contact.getVkLink());
        phone.setText(contact.getPhone());
        mail.setText(contact.getMail());
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+contact.getPhone()));
                startActivity(intent);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{contact.getMail()});
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Выберите email клиент :"));
            }
        });

        vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/" + URLEncoder.encode(contact.getVkLink(), "UTF-8"))));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}