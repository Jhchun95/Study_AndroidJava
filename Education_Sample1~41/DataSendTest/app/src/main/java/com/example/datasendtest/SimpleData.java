package com.example.datasendtest;

import android.os.Parcel;
import android.os.Parcelable;


//  Parcelable 인터페이스는 현재 클래스로 생성한 객체를 전송할 때 직렬화 한다는 의미로 Parcelable 인터페이스가 구현된
//  객체는 전송할 때 직렬(한 줄)로 만들어 저장이나 전송을 한다는 의미이다. => 안드로이드에서 사용하는 방법
//  Parcelable 인터페이스는 자바에서 지원되는 Serializable과는 별개로 안드로이드 자체적으로 지원한다.
//  Parcelable 인터페이스의 추상 메소드 Override는 클래스 설계가 마무리된 후 실행한다.
public class SimpleData implements Parcelable {

    private String name;
    private int age;
    private boolean gender;

    public SimpleData() {
        this("무명씨", 0, false);
    }

    public SimpleData(String name, int age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

// 여기부터
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
//  여기까지 Parcelable 인터페이스를 구현한 클래스 이름 위에서 alt + Enter를 눌러서 Add Parcelable implementation을
//  클릭하면 자동으로 완성된다. => 직렬화 기능을 안드로이드가 자동으로 코딩해준다.
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
        return "SimpleData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
