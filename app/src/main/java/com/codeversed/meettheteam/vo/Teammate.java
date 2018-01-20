package com.codeversed.meettheteam.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class Teammate implements Parcelable {

  @SerializedName("avatar")
  @Expose private String avatar;

  @SerializedName("bio")
  @Expose private String bio;

  @SerializedName("firstName")
  @Expose private String firstName;

  @SerializedName("id")
  @Expose private int id;

  @SerializedName("lastName")
  @Expose private String lastName;

  @SerializedName("title")
  @Expose private String title;

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getFirstName() {
    return firstName == null ? "" : firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName == null ? "" : lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFullName() {
    return String.format("%s %s", getFirstName(), getLastName());
  }


  protected Teammate(Parcel in) {
    avatar = in.readString();
    bio = in.readString();
    firstName = in.readString();
    id = in.readInt();
    lastName = in.readString();
    title = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(avatar);
    dest.writeString(bio);
    dest.writeString(firstName);
    dest.writeInt(id);
    dest.writeString(lastName);
    dest.writeString(title);
  }

  @SuppressWarnings("unused")
  public static final Parcelable.Creator<Teammate> CREATOR = new Parcelable.Creator<Teammate>() {
    @Override
    public Teammate createFromParcel(Parcel in) {
      return new Teammate(in);
    }

    @Override
    public Teammate[] newArray(int size) {
      return new Teammate[size];
    }
  };
}
