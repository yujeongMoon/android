package com.koreait.datasendtest;

import android.os.Parcel;
import android.os.Parcelable;

// parcelable 인터페이스는 현재 클래스로 생성한 객체를 전송할 때 직렬화 한다는 의미로
// Parcelable 인터페이스가 구현된 객체는 전송할 때 직렬(한 줄)로 만들어 저장이나 전송을 한다는 의미이다.
// => 안드로이드에서 사용하는 방법
// parcelable 인터페이스는 자바에서 지원되는 Serializable과는 별개로 안드로이드 자체적으로 지원한다.

// parcelable 인터페이스는 설계가 다 끝난 후에 구현 한다.
public class SimpleData implements Parcelable {

    private String name;
    private int age;
    private boolean gender;

    public SimpleData() {
        this("문유정", 0, false);
    }

    public SimpleData(String name, int age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }


    //여기부터
    protected SimpleData(Parcel in) {
        name = in.readString();
        age = in.readInt();
        gender = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeByte((byte) (gender ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SimpleData> CREATOR = new Creator<SimpleData>() {
        @Override
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };
    // 여기까지 parcelable 인터페이스를 구현한 클래스 이름위에 alt + Enter를 눌러서 add parcelable implementation을 함.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "이름 = '" + name + '\'' +
                ", 나이 = " + age +
                ", 성별 = " + (gender? "남" : "여");
    }
}
