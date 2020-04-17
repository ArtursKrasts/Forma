package com.example.forma;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Pet {
    private static final String TAG = "Pet";
    private static final String FILE_NAME = "myPets.json";
    private String petName;
    private String petType;
    private String petBreed;
    private int petAge;
    private String petGender;
    private String City;
    private String Address;
    private String phoneNr;

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

    public void saveAsJsonFile(Context context){
        Log.d(TAG , " saveAsJsonFile :start");
        Gson gson = new Gson();
        String json = gson.toJson(this);
        Log.d(TAG , " saveAsJsonFile: Json String : " + json);
        JSONObject petDetails = null;
        JSONParser parser = new JSONParser();

        try {
            petDetails = (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject myPet ;
        myPet = new JSONObject();
        myPet.put("pet", petDetails);

        JSONArray myPets = getMyPetsJson(context);
        if(myPets == null){
            myPets = new JSONArray();
        }
        myPets.add(myPet);

        gson = new GsonBuilder().setPrettyPrinting().create();
        json = gson.toJson(myPets);

        try {
            FileWriter file = new FileWriter(context.getFilesDir() + "/" + FILE_NAME);
            file.write(json);
            file.flush();
            Log.d(TAG , " saveAsJsonFile: Json saved at : " + context.getFilesDir() + "/" + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray getMyPetsJson(Context context){
        Log.d(TAG , " getMyPetsJson :start");
        JSONArray myPets = null;

        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader(context.getFilesDir() + "/" + FILE_NAME);
            Object obj = jsonParser.parse(reader);
            myPets = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myPets;
    }

    public static Pet[] getMyPets(Context context){
        Log.d(TAG , " getMyPets :start");
        Gson gson = new Gson();
        Pet[] myPets = null;
        JSONArray myPetsJson = getMyPetsJson(context);
        if(myPetsJson == null) {
            return null;
        }
        int len = myPetsJson.size();
        myPets = new Pet[len];
        for(int i = 0; i < len; i++){
            JSONObject petJson = (JSONObject) myPetsJson.get(i);
            Log.d(TAG , " getMyPets :Json String :" + petJson.toString());
            myPets[i] = gson.fromJson(petJson.get("pet").toString(), Pet.class);
        }
        return myPets;
    }
}
