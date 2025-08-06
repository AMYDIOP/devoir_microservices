package com.example.produitservice.service;

import com.example.produitservice.dto.CategorieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategorieClientService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8081/api/categories"; // adapte l'URL à ton cas

    public CategorieDTO getCategorieById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, CategorieDTO.class);
    }
}
