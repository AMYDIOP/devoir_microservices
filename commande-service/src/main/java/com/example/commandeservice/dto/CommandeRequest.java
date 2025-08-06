package com.example.commandeservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommandeRequest {
    private List<ItemRequest> items;
}

