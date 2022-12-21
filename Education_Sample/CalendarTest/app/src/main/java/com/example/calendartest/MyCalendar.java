package com.example.calendartest;

// 달력 작업에 사용할 4개의 메소드를 기억하는 클래스
public class MyCalendar {

//  윤년, 평년, 판별 메소드
//  윤년, 평년 판별식 : 년도가 4로 나눠 떨어지고 100으로 나눠 떨어지지 않거나 400으로 나눠 떨어지면 윤년
//  년도를 넘겨받아 윤년, 평년을 판별하고 윤년이면 true, 평년이면 false를 리턴하는 메소드
    public static boolean isLeapYear(int year) {
        return year%4 == 0 && year%100 !=0 && year%400 == 100;
    }

//  년, 월을 넘겨받아 그 달의 마지막 날짜를 리턴하는 메소드
    public static int lastday(int year, int month) {
        int[] m = {31, 28, 31, 30, 31, 30, 31, 31 ,30, 31 ,30, 31};
        m[1] = isLeapYear(year) ? 29 : 28;
        return m[month-1];
    }

//  년, 월, 일을 넘겨받아 1년 1월 1일부터 지나온 날짜의 합계를 리턴하는 메소드
    public static int totalDay(int year, int month, int day) {
//      1년 1월 1일부터 저년도 12월 31일까지 지난 날짜를 계산한다.
        int total = (year -1 ) * 365 + (year -1)/4 - (year -1)/100 + (year - 1)/400;
//      전년도까지 지난 날짜에 전달까지 지난 날짜를 더한다.
        for(int i=0; i< month; i++) {
            total += lastday(year, i); // month가 아니라 i를 적어야한다.
        }
        return total + day;
    }

//  년, 월, 일을 넘겨받아 요일을 숫자로 리턴하는 메소드, 일요일(0), 월요일(1), ... , 토요일(6)
//    public static int weekDay(int year, int month, int day) {
//        return
//    }

}
