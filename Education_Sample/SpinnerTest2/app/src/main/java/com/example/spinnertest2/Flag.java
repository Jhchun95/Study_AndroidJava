package com.example.spinnertest2;

// Spinner에 넣어줄 데이터(작은 국기 이미지, 국가 이름)을 기억하는 클래스
class Flag {

    private int flagId;     // 작은 국기 이미지의 id
    private String name;    // 국가 이름

    public Flag() {}
    public Flag(int flagId, String name) {
        this.flagId = flagId;
        this.name = name;
    }

    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "flagId=" + flagId +
                ", name='" + name + '\'' +
                '}';
    }
}
