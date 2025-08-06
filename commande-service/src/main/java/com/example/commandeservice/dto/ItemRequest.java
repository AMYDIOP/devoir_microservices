package com.example.commandeservice.dto;

import lombok.Data;

@Data
public class ItemRequest {
    private Long idProduit;
    private Long idUser;
    private int quantite;
}
