package model;
public class UserModel {
    private String id = "";
    private String link = "";
    private String name = "";
    private String latitude = "";
    private String longitude = "";
    private String picture = "";
    private String gender= "";
    private String languages = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguages() {return languages;}

    public void setLanguages(String languages) {this.languages = languages;}

    public String getPicture() { return picture;}

    public void setPicture(String picture) {this.picture = picture;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
