package com.example.submisi3made.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class TvShow implements Parcelable {
    private static final String TAG = "TvShow";

    private String title; // title
    private String year; // release_date
    private int average; //vote_average
    private String overview; // overview
    private String photo; // poster path

    public TvShow() {

    }

    protected TvShow(Parcel in) {
        Log.d(TAG, "TvShow: running");
        title = in.readString();
        year = in.readString();
        average = in.readInt();
        overview = in.readString();
        photo = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(year);
        parcel.writeInt(average);
        parcel.writeString(overview);
        parcel.writeString(photo);
    }
}
