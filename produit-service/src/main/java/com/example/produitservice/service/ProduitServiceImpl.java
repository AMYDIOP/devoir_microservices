package com.example.produitservice.service;

import com.example.produitservice.dao.ProduitRepository;
import com.example.produitservice.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements IProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit modifierProduit(Long id, Produit produit) {
        Optional<Produit> existingProduit = produitRepository.findById(id);
        if (!existingProduit.isPresent()) {
            return null;
        }

        Produit produitToUpdate = existingProduit.get();
        produitToUpdate.setNom(produit.getNom());
        produitToUpdate.setPrix(produit.getPrix());
        produitToUpdate.setQuantite(produit.getQuantite());
        produitToUpdate.setDescription(produit.getDescription());
        produitToUpdate.setCategorieId(produit.getCategorieId());
        produitToUpdate.setReference(produit.getReference());

        return produitRepository.save(produitToUpdate);
    }

    @Override
    public void supprimerProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
