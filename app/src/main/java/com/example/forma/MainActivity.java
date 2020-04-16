package com.example.forma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String petType;
    private String petGender;
    private Pet myPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OpenForma(View view) {
        setContentView(R.layout.forma);

        Spinner spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.spinner_array_1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.spinner_array_2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petGender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    public void OpenMain(View view) {
        setContentView(R.layout.activity_main);
    }

    public void SaveData(View view) { ;

        String petName;
        String petBreed;
        int petAge ;
        String mCity;
        String mAddress;
        String mPhoneNr;

        EditText name = findViewById(R.id.editName);
        EditText breed = findViewById(R.id.editBreed);
        EditText age = findViewById(R.id.editAge);
        EditText city = findViewById(R.id.editCity);
        EditText address = findViewById(R.id.editAddress);
        EditText phoneNr = findViewById(R.id.editPhoneNr);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        petName = name.getText().toString().trim();
        if(petName.equals("")){
            builder.setTitle("Kļūda!");
            builder.setMessage("Nav ievadīts mājdzīvnieka vārds.");
            builder.show();
            return;
        }

        petBreed = breed.getText().toString().trim();
        if(petBreed.equals("")){
            builder.setTitle("Kļūda!");
            builder.setMessage("Nav ievadīta mājdzīvnieka šķirne.");
            builder.show();
            return;
        }

        if(age.getText().toString().trim().matches("-?\\d+(\\.\\d+)?")){
            petAge = Integer.parseInt(age.getText().toString());
        }
        else {
            builder.setTitle("Kļūda!");
            builder.setMessage("Vecumam ir jābūt skaitlim.");
            builder.show();
            return;
        }

        mCity = city.getText().toString().trim();
        if(mCity.equals("")){
            builder.setTitle("Kļūda!");
            builder.setMessage("Nav ievadīta pilsēta.");
            builder.show();
            return;
        }

        mAddress = address.getText().toString().trim();
        if(mAddress.equals("")){
            builder.setTitle("Kļūda!");
            builder.setMessage("Nav ievadīta adrese.");
            builder.show();
            return;
        }

        mPhoneNr = phoneNr.getText().toString().trim();
        if(mPhoneNr.equals("")){
            builder.setTitle("Kļūda!");
            builder.setMessage("Nav ievadīts telefona numurs.");
            builder.show();
            return;
        }

        myPet = new Pet(petName, petType, petBreed, petAge, petGender, mCity, mAddress, mPhoneNr);

        builder.setTitle("Ok!");
        builder.setMessage(myPet.getPetName() + "\n" + myPet.getPetType() + "\n" + myPet.getPetBreed() +
                "\n" + myPet.getPetAge() + "\n" + myPet.getPetGender() + "\n" + myPet.getCity() +
                "\n" + myPet.getAddress() + "\n" + myPet.getPhoneNr());

        builder.show();

        myPet.saveAsJsonFile(getApplicationContext());
    }

    public void ReadJson(View view) {
        Log.d(TAG , "  ReadJson :");
        Pet[] myPets;
        myPets = Pet.getMyPets(getApplicationContext());
        if(myPet == null){
            Log.d(TAG , "  ReadJson : Pet.getMyPets returned null");
            return;
        }

        for (Pet myPet : myPets) {
            Log.d(TAG , "  ReadJson :" + myPet.getPetName() + " " + myPet.getPetType() + " " + myPet.getPetBreed() +
                    " " + myPet.getPetAge() + " " + myPet.getPetGender() + " " + myPet.getCity() +
                    " " + myPet.getAddress() + " " + myPet.getPhoneNr());
        }
    }
}
