package service.impl;

import dao.IProduitDAO;
import dao.impl.ProduitDAOImpl;
import model.Produit;
import service.IProduitService;
import java.util.List;

public class ProduitServiceImpl implements IProduitService {

    private static ProduitServiceImpl instance;
    private IProduitDAO produitDAO = ProduitDAOImpl.getInstance();

    private ProduitServiceImpl() {}

    public static synchronized ProduitServiceImpl getInstance() {
        if (instance == null) instance = new ProduitServiceImpl();
        return instance;
    }

    @Override public void addProduit(Produit p)        { produitDAO.add(p); }
    @Override public void deleteProduit(Long id)       { produitDAO.delete(id); }
    @Override public void updateProduit(Produit p)     { produitDAO.update(p); }
    @Override public Produit getProduitById(Long id)   { return produitDAO.findById(id); }
    @Override public List<Produit> getAllProduits()     { return produitDAO.findAll(); }
}