package com.gestion.service.impl;

import com.gestion.dao.IProduitDAO;
import com.gestion.dao.impl.ProduitDAOImpl;
import com.gestion.model.Produit;
import com.gestion.service.IProduitService;
import java.util.List;

public class ProduitServiceImpl implements IProduitService {
    
    // PLUS DE SINGLETON !
    private IProduitDAO produitDAO;
    
    public ProduitServiceImpl() {
        this.produitDAO = new ProduitDAOImpl();  // Nouvelle instance
    }
    
    @Override
    public void addProduit(Produit produit) {
        produitDAO.add(produit);
    }
    
    @Override
    public void deleteProduit(Long id) {
        produitDAO.delete(id);
    }
    
    @Override
    public void updateProduit(Produit produit) {
        produitDAO.update(produit);
    }
    
    @Override
    public Produit getProduitById(Long id) {
        return produitDAO.findById(id);
    }
    
    @Override
    public List<Produit> getAllProduits() {
        return produitDAO.findAll();
    }
}