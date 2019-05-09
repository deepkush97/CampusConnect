package com.afreet.campusconnect.Utils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {


    public static String deptConversion( String deptCode){

        switch (deptCode){
            case "07" : return "Computer Engineering";
            case "home" : return "College";
            case "06" : return "Civil Engineering";
            case "60" : return "Civil Engineering";
            case "19" : return "Mechanical Engineering";
            case "09" : return "Electrical Engineering";
            case "00" : return "Humanities";
            case "11" : return "Electronics and Communication Engineering";
            default: return "";
        }
    }

    public static String semesterConversion(String semester) {

        switch (semester) {
            case "1":
                return "First Semester";
            case "2":
                return "Second Semester";
            case "3":
                return "Third Semester";
            case "4":
                return "Fourth Semester";
            case "5":
                return "Fifth Semester";
            case "6":
                return "Sixth Semester";
            case "7":
                return "Seventh Semester";
            case "8":
                return "Eighth Semester";
            default:
                return "";
        }
    }

    public static List<String> getDepartments(){
        List<String> dept = new ArrayList<>();
        dept.add("home");
        dept.add("06");
        dept.add("19");
        dept.add("09");
        dept.add("07");
        dept.add("00");
        dept.add("11");

        return dept;
    }

    public static String getCollegeHttpsURL(){
        return "https://gecdahod.ac.in/";
    }

    public static String getCollegeHttpURL(){
        return "http://gecdahod.ac.in/";
    }

    public static String getCollegeBigLogo() {
        return "http://gecdahod.ac.in/imgs/logo_big.png";
    }

    public static String getCollegeLogo() {
        return "http://gecdahod.ac.in/imgs/main-logo2.png";
    }

    public static String facultyImageUrl(){
        return getCollegeHttpsURL()+"staffpics/";
    }

    public static String studentImageUrl() {
        return getCollegeHttpsURL() + "studentpics/";
    }


    public static String noticeFileUrl(){
        return getCollegeHttpsURL()+"notices/";
    }

    public static void callViewProfileActivity(Context callingActivity, String userId, String userType) {

        Intent intent = new Intent(callingActivity, ViewProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("userId", userId);
        intent.putExtra("userType", userType);
        callingActivity.startActivity(intent);
    }
}
