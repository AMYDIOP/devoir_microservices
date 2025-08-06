package com.example.produitservice.service;

import com.example.produitservice.model.Produit;

import java.util.List;

public interface IProduitService {
    List<Produit> getAllProduits();
    Produit getProduitById(Long id);
    Produit createProduit(Produit produit);
    Produit modifierProduit(Long id, Produit produit);
    void supprimerProduit(Long id);
}
