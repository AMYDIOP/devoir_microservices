package com.example.categorieservice.services;
import com.example.categorieservice.dao.CategorieRepository;
import com.example.categorieservice.model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements ICategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Categorie ajouterCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
    }

    @Override
    public void supprimerCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public Categorie modifierCategorie(Long id, Categorie newCategorie) {
        Categorie categorie = getCategorieById(id);
        categorie.setNom(newCategorie.getNom());
        categorie.setDescription(newCategorie.getDescription());
        return categorieRepository.save(categorie);
    }
}
