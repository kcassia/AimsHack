package net.wherewhat.matereal.information;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashSet;



public class Lecture implements Parcelable{

    private String name;
    private String major;
    private String professor;
    private int credit;
    private String lectureCode;
    private HashSet<LectureTime> lectureTimeSet;

    public Lecture(String name, String major, String professor, int credit, String lectureCode, HashSet<LectureTime> lectureTimeSet)
    {
        this.name = name;
        this.major = major;
        this.professor = professor;
        this.credit = credit;
        this.lectureCode = lectureCode;
        this.lectureTimeSet = lectureTimeSet;
    }

    public Lecture(Parcel src)
    {
        name = src.readString();
        major = src.readString();
        professor = src.readString();
        credit = src.readInt();
        lectureCode = src.readString();
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public Lecture createFromParcel(Parcel in)
        {
            return new Lecture(in);
        }

        @Override
        public Lecture[] newArray(int size)
        {
            return new Lecture[size];
        }
    };

    @Override
    public int describeContents(){return 0;}

    @Override

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeString(major);
        parcel.writeString(professor);
        parcel.writeInt(credit);
        parcel.writeString(lectureCode);
    }

    public String getName() {return name;}
    public HashSet<LectureTime> getLectureTimeSet(){return lectureTimeSet;}

    public void setName(String name) {
        this.name = name;
    }
}
