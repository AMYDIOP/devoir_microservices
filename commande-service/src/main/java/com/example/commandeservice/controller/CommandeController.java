package com.example.commandeservice.controller;

import com.example.commandeservice.dto.CommandeRequest;
import com.example.commandeservice.model.Commande;
import com.example.commandeservice.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    // creer
    @PostMapping
    public ResponseEntity<Commande> creerCommande(@RequestBody CommandeRequest commandeRequest) {
        Commande commande = commandeService.creerCommande(commandeRequest);
        return ResponseEntity.created(URI.create("/api/commandes/" + commande.getId())).body(commande);
    }

    // Liste
    @GetMapping
    public ResponseEntity<List<Commande>> getAll() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    // details
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }
    // Modifier une commande
    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody CommandeRequest commandeRequest) {
        Commande updatedCommande = commandeService.updateCommande(id, commandeRequest);
        return ResponseEntity.ok(updatedCommande);
    }


    //Supprimer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }

}
