package com.bourne.caesar.petadoptionform.Models;

import java.util.ArrayList;

public class ElementsModel {

    private  String type;
    private  String file;
    private  String unique_id;
    private  ArrayList<RulesModel> rules;
    private  String label;
    private  boolean isMandatory;
    private  String keyboard;
    private  String formattedNumeric;
    private  String mode;

    public String getType() {
        return type;
    }

    public String getFile() {
        return file;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public ArrayList<RulesModel> getRules() {
        return rules;
    }

    public String getLabel() {
        return label;
    }

    public boolean getIsMandatory() {
        return isMandatory;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public String getFormattedNumeric() {
        return formattedNumeric;
    }

    public String getMode() {
        return mode;
    }
}
