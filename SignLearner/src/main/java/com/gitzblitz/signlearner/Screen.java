package com.gitzblitz.signlearner;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 2014/05/11.
 */
public class Screen implements Parcelable {

    private String screenID;
    private String videoURL;
    private String vidCaption;
    private String imagePath;



    public String getScreenID() {
        return screenID;
    }

    public void setScreenID(String screenID) {
        this.screenID = screenID;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVidCaption() {
        return vidCaption;
    }

    public void setVidCaption(String vidCaption) {
        this.vidCaption = vidCaption;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return vidCaption;
    }

    public Screen(Parcel in){
        screenID = in.readString();
        videoURL = in.readString();
        vidCaption = in.readString();
        imagePath = in.readString();
    }

    public Screen() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(screenID);
        parcel.writeString(videoURL);
        parcel.writeString(vidCaption);
        parcel.writeString(imagePath);
    }


    public static final Creator<Screen> CREATOR = new Creator<Screen>(){

        @Override
        public Screen createFromParcel(Parcel parcel) {
            return new Screen(parcel);
        }

        @Override
        public Screen[] newArray(int i) {
            return new Screen[i];
        }
    };
}
