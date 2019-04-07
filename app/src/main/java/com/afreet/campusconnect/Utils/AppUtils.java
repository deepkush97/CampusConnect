package com.afreet.campusconnect.Utils;

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

    public static String facultyImageUrl(){
        return getCollegeHttpsURL()+"staffpics/";
    }
    public static String noticeFileUrl(){
        return getCollegeHttpsURL()+"notices/";
    }

}
