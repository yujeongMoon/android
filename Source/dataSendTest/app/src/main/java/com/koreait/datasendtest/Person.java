package com.koreait.datasendtest;

import java.io.Serializable;

// Serializable 인터페이스는 아무런 내용을 가지지 않는 표시(Marker) 인터페이스로 현재 클래스의 객체를 전송할 때
// 직렬화를 시킨다는 의미로 Serializable 인터페이스가 구현된 객체는 객체의 내용을 전송할 때 직렬(한줄)로 저장 또는 전송한다는 의미이다.
// => 자바에서 사용하는 방법
public class Person implements Serializable {

    private String name;
    private int age;
    private boolean gender;

    public Person() { // 기본 생성자
        // 기본 생성자가 실행되면 Person(String name, int age, boolean gender) 생성자를 실행한다.
        // super => 부모 클래스
        // super() => 부모 클래스의 생성자
        // this => 현재 클래스
        // this() => 현재 클래스의 생성자
        this("문유정", 0 , false);
    }

    public Person(String name, int age, boolean gender) {
        // super();
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

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
                ", 성별 = " + (gender ? "남" : "여");
    }
}
