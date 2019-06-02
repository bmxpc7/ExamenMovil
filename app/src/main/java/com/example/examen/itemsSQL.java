package com.example.examen;

import android.provider.BaseColumns;

public class itemsSQL {
    public static abstract class usersEntry implements BaseColumns {
        public static final String TABLE_NAME ="Users";
        public static final String _ID = "id";
        public static final String FIRST_NAME = "name";
        public static final String LAST_NAME = "specialty";
        public static final String EMAIL = "email";
        public static final String AVATAR_URI = "avatarUri";


    }
}
