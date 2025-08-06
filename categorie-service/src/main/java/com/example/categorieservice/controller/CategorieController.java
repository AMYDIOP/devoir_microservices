package com.example.categorieservice.controller;

import com.example.categorieservice.model.Categorie;
import com.example.categorieservice.services.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private ICategorieService categorieService;

    @PostMapping
    public EntityModel<Categorie> ajouterCategorie(@RequestBody Categorie categorie) {
        Categorie saved = categorieService.ajouterCategorie(categorie);
        return toModel(saved);
    }

    @GetMapping
    public CollectionModel<EntityModel<Categorie>> getCategories() {
        List<Categorie> categories = categorieService.getCategories();

        List<EntityModel<Categorie>> categorieModels = categories.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(categorieModels,
                linkTo(methodOn(CategorieController.class).getCategories()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Categorie> getCategorieById(@PathVariable Long id) {
        Categorie categorie = categorieService.getCategorieById(id);
        return toModel(categorie);
    }

    @PutMapping("/{id}")
    public EntityModel<Categorie> modifierCategorie(@PathVariable Long id, @RequestBody Categorie c) {
        Categorie updated = categorieService.modifierCategorie(id, c);
        return toModel(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCategorie(@PathVariable Long id) {
        categorieService.supprimerCategorie(id);
        return ResponseEntity.noContent().build();
    }


    // Méthode utilitaire pour générer les liens HATEOAS pour chaque catégorie
    private EntityModel<Categorie> toModel(Categorie c) {
        return EntityModel.of(c,
                linkTo(methodOn(CategorieController.class).getCategorieById(c.getId())).withSelfRel(),
                linkTo(methodOn(CategorieController.class).getCategories()).withRel("all-categories"),
                linkTo(methodOn(CategorieController.class).modifierCategorie(c.getId(), c)).withRel("update"),
                linkTo(methodOn(CategorieController.class).supprimerCategorie(c.getId())).withRel("delete"));
    }
}
