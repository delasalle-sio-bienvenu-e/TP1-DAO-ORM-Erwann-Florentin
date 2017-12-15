package com.mydigitalschool.dao_orm.daojooq.business;

import java.util.ArrayList;
import java.util.List;

public class Entity1 { //parc attraction
    public Integer id;
    public Integer id_societe;
    public String nom;
    public Integer taille;
    public final List<Integer> tarif = new ArrayList<>();
    
    public boolean hasTarif(final String tarif) {
        return tarif.contains(tarif);
    }
    
}
