package com.gestion.service;

import com.gestion.model.Produit;
import java.util.List;

public interface IProduitService {
    void addProduit(Produit produit);
    void deleteProduit(Long id);
    void updateProduit(Produit produit);
    Produit getProduitById(Long id);
    List<Produit> getAllProduits();
}