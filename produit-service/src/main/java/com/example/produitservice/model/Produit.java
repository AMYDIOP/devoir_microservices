package com.example.produitservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produits", indexes = {@Index(name = "id_reference", columnList = "reference")})
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private Double prix;

    private String description;

    @Column(nullable = false)
    private Integer quantite;

    @Column(name = "categorie_id", nullable = false)
    private Long categorieId;

    @Column(nullable = false, unique = true)
    private String reference;

    // 🔸 Constructeurs
    public Produit() {}

    public Produit(Long id, String nom, Double prix, String description, Integer quantite, Long categorieId, String reference) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.quantite = quantite;
        this.categorieId = categorieId;
        this.reference = reference;
    }

    // 🔸 Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }

    public Long getCategorieId() { return categorieId; }
    public void setCategorieId(Long categorieId) { this.categorieId = categorieId; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}
