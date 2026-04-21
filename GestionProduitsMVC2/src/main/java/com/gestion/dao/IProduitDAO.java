package com.gestion.dao;

import com.gestion.model.Produit;
import java.util.List;

public interface IProduitDAO {
    void add(Produit produit);
    void delete(Long id);
    void update(Produit produit);
    Produit findById(Long id);
    List<Produit> findAll();
}