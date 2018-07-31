package com.natasa_tsibi.app1hua;


/**
 * Created by Natasa on 5/12/2017.
 */

public class User {
    // declare variables
    private int id;
    private String uid, timestamp;
    private float longitute, latitude;

    // Constructor
    public User(int id,String uid, float longitute, float latitude, String timestamp) {
        this.id = id;
        this.uid = uid;
        this.longitute = longitute;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    // empty Constructor
    public User() {
    }

    // getter for ID
    public int getId() {
        return id;
    }

    // setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // getter for UserID
    public String getUid() {
        return uid;
    }

    // setter for UserID
    public void setUid(String uid) {
        this.uid = uid;
    }

    // getter for Longitude
    public float getLongitute() {
        return longitute;
    }

    // setter for Longitude
    public void setLongitute(float longitute) {
        this.longitute = longitute;
    }

    // getter for Latitude
    public float getLatitude() {
        return latitude;
    }

    // setter for Latitude
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    // getter for Timestamp
    public String getTimestamp() {
        return timestamp;
    }

    // setter for Timestamp
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
