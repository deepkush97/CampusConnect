package com.afreet.campusconnect.Models;

/**
 * This is a model class of notice.
 * It will be used to store and fetch(work) on the notice object throughout this
 * system.
 *
 * Two constructor : default constructor and parameterized constructor.
 * Getter and Setter Fuctions
 * toString() Function is used to return the values in String format.
 *
 * it will be mainly used in NoticeActivity.
 */
public class Notice {

    private String noticeId;
    private String userId;
    private String facultyName;
    private String facultyImage;
    private String caption;
    private String link;
    private String dept;
    private String date;

    public Notice() {
    }

    public Notice(String noticeId, String userId, String facultyName, String facultyImage, String caption, String link, String dept, String date) {
        this.noticeId = noticeId;
        this.userId = userId;
        this.facultyName = facultyName;
        this.facultyImage = facultyImage;
        this.caption = caption;
        this.link = link;
        this.dept = dept;
        this.date = date;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyImage() {
        return facultyImage;
    }

    public void setFacultyImage(String facultyImage) {
        this.facultyImage = facultyImage;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId='" + noticeId + '\'' +
                ", userId='" + userId + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", facultyImage='" + facultyImage + '\'' +
                ", caption='" + caption + '\'' +
                ", link='" + link + '\'' +
                ", dept='" + dept + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
