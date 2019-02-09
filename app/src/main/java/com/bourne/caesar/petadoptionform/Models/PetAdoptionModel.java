package com.bourne.caesar.petadoptionform.Models;

import java.util.ArrayList;

public class PetAdoptionModel {

   private String id;
   private String name;
   private ArrayList<PagesModel> pages;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<PagesModel> getPages() {
        return pages;
    }
}
