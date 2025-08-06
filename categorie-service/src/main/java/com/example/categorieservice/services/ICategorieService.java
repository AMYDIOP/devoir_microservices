package com.example.categorieservice.services;
import com.example.categorieservice.model.Categorie;

import java.util.List;

public interface ICategorieService {
    Categorie ajouterCategorie(Categorie categorie);
    List<Categorie> getCategories();
    Categorie getCategorieById(Long id);
    void supprimerCategorie(Long id);
    Categorie modifierCategorie(Long id, Categorie categorie);
}
