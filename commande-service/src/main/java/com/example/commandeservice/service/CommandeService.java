package com.example.commandeservice.service;

import com.example.commandeservice.dto.CommandeRequest;
import com.example.commandeservice.dto.ItemRequest;
import com.example.commandeservice.model.Commande;
import com.example.commandeservice.model.Item;
import com.example.commandeservice.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;

    public Commande creerCommande(CommandeRequest commandeRequest) {
        Commande commande = new Commande();
        commande.setDateCommande(LocalDateTime.now());

        // Convertir les items
        List<Item> items = convertItemRequestsToItems(commandeRequest.getItems(), commande);
        commande.setItems(items);

        return commandeRepository.save(commande);
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }

    public Commande updateCommande(Long id, CommandeRequest commandeRequest) {
        Commande existingCommande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'id " + id));

        existingCommande.getItems().clear();

        List<Item> updatedItems = convertItemRequestsToItems(commandeRequest.getItems(), existingCommande);
        existingCommande.getItems().addAll(updatedItems);
        return commandeRepository.save(existingCommande);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    // Méthode privée réutilisable
    private List<Item> convertItemRequestsToItems(List<ItemRequest> itemRequests, Commande commande) {
        return itemRequests.stream().map(req -> {
            Item item = new Item();
            item.setIdProduit(req.getIdProduit());
            item.setIdUser(req.getIdUser());
            item.setQuantite(req.getQuantite());
            item.setCommande(commande);
            return item;
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
