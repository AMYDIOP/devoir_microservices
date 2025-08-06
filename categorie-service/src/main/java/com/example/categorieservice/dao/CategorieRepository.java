package com.example.categorieservice.dao;
import com.example.categorieservice.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Categorie findByNom(String nom);
    List<Categorie> findByNomContainingOrDescriptionContaining(String nom, String description);
}
