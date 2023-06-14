package com.example.laba3;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<ContactItem> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<ContactItem> contacts;
    private Context context;
    public ContactsAdapter(Context context, int resource, ArrayList<ContactItem> contacts){
        super(context,resource,contacts);
        this.contacts = contacts;
        this.layout = resource;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ContactItem contact = contacts.get(position);

        viewHolder.info.setText(contact.getName()+"\n"+contact.getSurname());
        viewHolder.ava.setImageURI((Uri.parse(contact.getAva())));


        return convertView;
    }
    private class ViewHolder {
        final ImageView ava;
        final TextView info;
        ViewHolder(View view){
            ava = view.findViewById(R.id.ava);
            info = view.findViewById(R.id.info);
        }
    }
}
