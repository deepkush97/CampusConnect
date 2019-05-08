package com.afreet.campusconnect.Models;


public class Post {

    private String postId;
    private String userId;
    private String userType;
    private String postCaption;
    private String postLink;
    private String postImage;
    private String postCatId;
    private String postDateCreated;

    public Post() {
    }


    public Post(String postId, String userId, String userType, String postCaption, String postLink, String postImage, String postCatId, String postDateCreated) {
        this.postId = postId;
        this.userId = userId;
        this.userType = userType;
        this.postCaption = postCaption;
        this.postLink = postLink;
        this.postImage = postImage;
        this.postCatId = postCatId;
        this.postDateCreated = postDateCreated;
    }


    public void addPost(String postId, String userId, String userType, String postCaption, String postLink, String postImage, String postCatId, String postDateCreated) {
        this.postId = postId;
        this.userId = userId;
        this.userType = userType;
        this.postCaption = postCaption;
        this.postLink = postLink;
        this.postImage = postImage;
        this.postCatId = postCatId;
        this.postDateCreated = postDateCreated;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPostCaption() {
        return postCaption;
    }

    public void setPostCaption(String postCaption) {
        this.postCaption = postCaption;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostCatId() {
        return postCatId;
    }

    public void setPostCatId(String postCatId) {
        this.postCatId = postCatId;
    }

    public String getPostDateCreated() {
        return postDateCreated;
    }

    public void setPostDateCreated(String postDateCreated) {
        this.postDateCreated = postDateCreated;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", userType='" + userType + '\'' +
                ", postCaption='" + postCaption + '\'' +
                ", postLink='" + postLink + '\'' +
                ", postImage='" + postImage + '\'' +
                ", postCatId='" + postCatId + '\'' +
                ", postDateCreated='" + postDateCreated + '\'' +
                '}';
    }
}

