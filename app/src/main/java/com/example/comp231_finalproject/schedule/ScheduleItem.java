package com.example.comp231_finalproject.schedule;



public class ScheduleItem {

    private String className;
    private String startTime;
    private String endTime;
    private int mColor;

    public ScheduleItem(String className, String startTime, String endTime, int mColor) {
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mColor = mColor;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }
}
