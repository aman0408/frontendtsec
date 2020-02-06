package com.example.hospblock;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * User: Aman
 * Date: 06-02-2020
 * Time: 04:26 AM
 */
public class RegisterPatientResponse {
    @SerializedName("aadhar")
    @Expose
    private String aadhar;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("addressHashed")
    @Expose
    private String addressHashed;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("keystore")
    @Expose
    private String keystore;
    @SerializedName("name")
    @Expose
    private String name;

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressHashed() {
        return addressHashed;
    }

    public void setAddressHashed(String addressHashed) {
        this.addressHashed = addressHashed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RegisterPatientResponse{" +
                "aadhar='" + aadhar + '\'' +
                ", address='" + address + '\'' +
                ", addressHashed='" + addressHashed + '\'' +
                ", age='" + age + '\'' +
                ", keystore='" + keystore + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
