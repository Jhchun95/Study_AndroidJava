package com.example.listviewtest3;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// 텍스트 파일(chunja.txt , chunja2.txt)의 데이터를 읽어서 기억하는 클래스
// 데이터가 저장된 클래스 객체를 전송해야 하므로 Parcelable 인터페이스를 구현받아 직렬화한다.
// Parcelable 인터페이스의 자동 완성 메소드는 클래스의 멤버 변수를 모두 선언한 후 만들어줘야한다.
public class Chunja implements Parcelable{

    private int index; // 일련번호
    private String h;   // 한자(天)
    private String k;   // 음(천)
    private String c;   // 뜻과 음(하늘 천)
    private String p;   // 뜻 풀이, chunja2.txt일 경우에만 사용한다.

    public Chunja() {}
    public Chunja(int index, String h, String k, String c, String p) {
        this.index= index;
        this.h = h;
        this.k = k;
        this.c = c;
        this.p = p;
    }

    protected Chunja(Parcel in) {
        index = in.readInt();
        h = in.readString();
        k = in.readString();
        c = in.readString();
        p = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(h);
        dest.writeString(k);
        dest.writeString(c);
        dest.writeString(p);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Chunja> CREATOR = new Creator<Chunja>() {
        @Override
        public Chunja createFromParcel(Parcel in) {
            return new Chunja(in);
        }

        @Override
        public Chunja[] newArray(int size) {
            return new Chunja[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Chunja{" +
                "index=" + index +
                ", h='" + h + '\'' +
                ", k='" + k + '\'' +
                ", c='" + c + '\'' +
                ", p='" + p + '\'' +
                '}';
    }

//  읽어들일 텍스트 파일의 데이터가 저장된 IntputStream 객체를 인수로 넘겨받아 데이터를 한 줄씩 읽어 구분자("|")로
//  구분해서 Chunja 클래스의 멤버 변수에 저장하고 텍스트 파일에서 읽어들인 데이터가 저장된 Chunja 클래스 객체를 ArrayList에 저장시켜
//  리턴하는 정적(static) 메소드를 만든다.

    public static ArrayList<Chunja> readChunja(InputStream inputStream) {
//      텍스트 파일에서 읽어들인 데이터를 저장시켜 리턴할 ArrayList를 선언한다.
        ArrayList<Chunja> chunjas = new ArrayList<>();

//      텍스트 파일의 내용이 저장된 InputStream 객체에서 데이터를 읽어들이는 스캐너를 만든다.
//      Scanner 생성자의 2번째 인수로 반드시 텍스트 파일을 생성할 때 사용한 인코딩 방식(캐릭터 셋)을 지정해야한다.
        Scanner scanner = new Scanner(inputStream, "UTF-8");

//      InputStream 객체에서 더 이상 읽어들일 데이터가 없을 때까지 반복하여 데이터를 읽어 Chunja 클래스 객체의 멤버 변수에 저장해서
//      ArrayList에 넣어준다.
//      hasNextLine() : 스캐너로 읽어들일 다음 데이터(줄)이 있으면 true, 없으면 false를 리턴한다.

        while (scanner.hasNextLine()) {
//          스캐너로 텍스트 파일의 데이터 한 줄을 읽어들인다.
            String str = scanner.nextLine().trim();
//          읽어들인 데이터가 빈 줄이 아닐 경우에만 Chunja 클래스 객체의 멤버 변수에 저장한다.
            if(str.length() > 0) {
//              Chunja 클래스의 객체를 만든다.
                Chunja chunja = new Chunja();
//              StringTokenizer 클래스를 이용해서 "|"를 기준으로 문자열을 분리한다.
//              StringTokenizer(문자열[, "구분자"][, true])
//              StringTokenizer 클래스의 생성자 인수에 문자열만 쓰면 공백 또는 탭이 기본 구분자로 사용된다.
//              StringTokenizer 클래스의 생성자 2번째 인수로 구분자를 지정한다.
//              StringTokenizer 클래스의 생성자 2번째 인수로 여러 개의 구분자를 지정할 수 있다.
//              StringTokenizer 클래스의 생성자 3번째 인수에 true를 쓰면 구분자도 토큰에 포함시켜 처리한다. => 기본값은 false
                StringTokenizer stringTokenizer = new StringTokenizer(str, "|&^");
//              구분자를 경계로 분리된 데이터(토큰)을 Chunja 클래스의 멤버 변수에 저장한다.
//              nextToken() : 구분자로 분리된 StringTokenizer 클래스 객체의 데이터 1건을 읽어들인다.
//              Integer.parseInt() => 인수로 지정된 문자열을 정수로 변환한다.
//              Double.parseDouble() => 인수로 지정된 문자열을 실수로 변환한다.
//              Boolean.parseBoolean() => 인수로 지정된 문자열을 논리값으로 변환한다.
                chunja.setIndex(Integer.parseInt(stringTokenizer.nextToken()));
                chunja.setH(stringTokenizer.nextToken());
                chunja.setK(stringTokenizer.nextToken());
                chunja.setC(stringTokenizer.nextToken());
//              chunja.setP(stringTokenizer.nextToken());
//              hanMoreTokens() : StringTokenizer 클래스 객체에 다음에 읽어들일 데이터(토큰)가 있으면 true, 없으면 false를 리턴한다.
                if (stringTokenizer.hasMoreTokens()) {
                        chunja.setP(stringTokenizer.nextToken());
                }
//              Chunja 클래스 객체를 ArrayList에 추가한다.
                chunjas.add(chunja);
//              텍스트 파일에서 읽어서 Chunja 클래스의 객체에 저장된 내용을 log로 출력해 확인한다.
//              Log.e("Chunja", chunja.toString());
            }
        }

        return chunjas;
    }

}
