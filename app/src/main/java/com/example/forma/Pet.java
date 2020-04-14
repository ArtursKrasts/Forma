package com.example.forma;

public class Pet {
    private String petName = "";
    private String petType = "";
    private String petBreed = "";
    private int petAge = 0;
    private String petGender = "";
    private String City = "";
    private String Address = "";
    private String phoneNr = "";

    public Pet(String petName, String petType, String petBreed, int petAge, String petGender, String city, String address, String phoneNr) {
        this.petName = petName;
        this.petType = petType;
        this.petBreed = petBreed;
        this.petGender = petGender;
        this.petAge = petAge;
        City = city;
        Address = address;
        this.phoneNr = phoneNr;
    }

    public String getPetName(){
        return petName;
    }

    public String getPetType() {
        return petType;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public int getPetAge() {
        return petAge;
    }

    public String getPetGender() {
        return petGender;
    }

    public String getCity() {
        return City;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNr() {
        return phoneNr;
    }
}
