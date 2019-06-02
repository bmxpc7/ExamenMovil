package com.example.examen;

public class itemsDatos {
    private String mAvatarImage;
    private String mFirstName;
    private String mLastName;

    public itemsDatos(String AvatarImage, String FirstName, String LastName){
        mAvatarImage = AvatarImage;
        mFirstName = FirstName;
        mLastName = LastName;
    }

    public String getImageUrl() {
        return mAvatarImage;
    }

    public String getFirstName(){
        return mFirstName;
    }

    public String getLastName(){
        return mLastName;
    }
}
