package com.bourne.caesar.petadoptionform.Models;

import java.util.ArrayList;

public class RulesModel {

    private String condition;
    private String value;
    private String action;
    private String otherwise;
    private ArrayList<String> targets;

    public String getCondition() {
        return condition;
    }

    public String getValue() {
        return value;
    }

    public String getAction() {
        return action;
    }

    public String getOtherwise() {
        return otherwise;
    }

    public ArrayList<String> getTargets() {
        return targets;
    }
}
