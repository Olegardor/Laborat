package com.example.laba3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class ContactItem implements Parcelable {
    private String name, surname, mail, phone, address, vkLink;
    private Boolean isMale;
    private String ava="";
    public static final Creator<ContactItem> CREATOR = new Parcelable.Creator<ContactItem>() {
        @Override
        public ContactItem createFromParcel(Parcel source) {
            ContactItem contact = new ContactItem();
            contact.setName(source.readString());
            contact.setSurname(source.readString());
            contact.setMail(source.readString());
            contact.setPhone(source.readString());
            contact.setAddress(source.readString());
            contact.setVkLink(source.readString());
            contact.setIsMale((Boolean)source.readValue(Boolean.class.getClassLoader()));
            contact.setAva(source.readString());
            return contact;
        }

        @Override
        public ContactItem[] newArray(int size) {
            return new ContactItem[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(mail);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(vkLink);
        dest.writeValue(isMale);
        dest.writeString(ava);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getAva() {
        return ava;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }


    public void setVkLink(String vkLink) {
        this.vkLink = vkLink;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSurname() {
        return surname;
    }


    public String getVkLink() {
        return vkLink;
    }

    public Boolean getIsMale() {
        return isMale;
    }

    public String toString() {
        String str = "";
        str += name != null ? (name + "\n") : "";
        str += surname != null ? (surname + "\n") : "";
        str += mail != null ? (mail + "\n") : "";
        str += isMale != null ? (isMale?"male":"female" + "\n") : "";
        str += phone != null ? (phone + "\n") : "";
        str += address != null ? (address + "\n") : "";
        str += vkLink != null ? (vkLink + "\n") : "";
         return str;
    }
    public static class DataItems {
        private List<ContactItem> items;

        List<ContactItem> getData() {
            return items;
        }
        void setData(List<ContactItem> items) {
            this.items = items;
        }
    }
}
