package com.bourne.caesar.petadoptionform;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bourne.caesar.petadoptionform.Models.ElementsModel;
import com.bourne.caesar.petadoptionform.Models.PagesModel;
import com.bourne.caesar.petadoptionform.Models.PetAdoptionModel;
import com.bourne.caesar.petadoptionform.Models.SectionsModel;
import com.bourne.caesar.petadoptionform.UI_Components.FormListFragment;
import com.bourne.caesar.petadoptionform.Utilities.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageView embeddedaImageView;
    private TextView formTextView;
    private PetAdoptionModel petAdoptionModel;
    private LinearLayout formListLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();

         String jsonData = getJsonString("pet_adoption-1.json.json");

        petAdoptionModel = new Gson().fromJson(jsonData, PetAdoptionModel.class);
        bindViews();
        PagesModel pagesModel = petAdoptionModel.getPages().get(0);

        //for each form section
        for (int i =0; i <pagesModel.getSections().size();i ++){
            pagesModel.getSections().get(i).getLabel();

            LinearLayout sectionLayout = new LinearLayout(this);
            sectionLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            sectionLayout.setOrientation(LinearLayout.VERTICAL);

            // LABEL
            TextView labelText = new TextView(this);
            labelText.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            labelText.setText(pagesModel.getSections().get(i).getLabel());
            sectionLayout.addView(labelText);

            //custom views
            SectionsModel sectionsModel = pagesModel.getSections().get(i);
            for (int j = 0; j<  sectionsModel.getElements().size(); j++  ){

                sectionLayout.addView(  getViews(  sectionsModel.getElements().get(j)));
            }

            formListLayout.addView(sectionLayout);

        }

    }

    private void initialization() {
        formTextView = findViewById(R.id.formName);
        embeddedaImageView = findViewById(R.id.embededPhoto);
        formListLayout = findViewById(R.id.formList);
    }

    private void bindViews() {

        if (petAdoptionModel.getName() != null){

            formTextView.setText(petAdoptionModel.getName());

        }
    }

    public View getViews(ElementsModel viewType){
        switch(viewType.getType()){
            case "text":
                return getTextViews(viewType);

            case "formattednumeric":
               getNumberTextViews(viewType);
            case "datetime":
                return new AutoCompleteTextView(this);
            case "yesno":
                return new RadioGroup(this);

        }
        return null;
    }

    public View getRadioGroup( ElementsModel viewType){
        LinearLayout textLayouts = new LinearLayout(this);
        textLayouts.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        textLayouts.setOrientation(LinearLayout.HORIZONTAL);

        // LABEL
        TextView labelText = new TextView(this);
        labelText.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        labelText.setText(viewType.getLabel());
        textLayouts.addView(labelText);

        // Custom EditText
        AutoCompleteTextView autoCompleteTextView =  new AutoCompleteTextView(this);
        autoCompleteTextView.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        textLayouts.addView(autoCompleteTextView);


        return textLayouts;
    }

    public View getNumberTextViews( ElementsModel viewType){
        LinearLayout textLayouts = new LinearLayout(this);
        textLayouts.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        textLayouts.setOrientation(LinearLayout.HORIZONTAL);

        // LABEL
        TextView labelText = new TextView(this);
        labelText.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        labelText.setText(viewType.getLabel());
        textLayouts.addView(labelText);

        // Custom EditText
        AutoCompleteTextView autoCompleteTextView =  new AutoCompleteTextView(this);
        autoCompleteTextView.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        autoCompleteTextView.setInputType(0);
        textLayouts.addView(autoCompleteTextView);


        return textLayouts;
    }


    public View getTextViews( ElementsModel viewType){
        LinearLayout textLayouts = new LinearLayout(this);
        textLayouts.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        textLayouts.setOrientation(LinearLayout.HORIZONTAL);

        // LABEL
        TextView labelText = new TextView(this);
        labelText.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        labelText.setText(viewType.getLabel());
        textLayouts.addView(labelText);

        // Custom EditText
        AutoCompleteTextView autoCompleteTextView =  new AutoCompleteTextView(this);
        autoCompleteTextView.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        textLayouts.addView(autoCompleteTextView);


        return textLayouts;
    }

    public String getJsonString(String jsonName){
        String jsonString= null;
        try{
            InputStream inputStream = getResources().getAssets().open("pet_adoption-1.json.json");
            int value = inputStream.available();
            byte[] buffer = new byte[value];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        }catch (IOException error){
            error.printStackTrace();
            return null;
        }
        return jsonString;
    }

}