package dao.impl;

import dao.IProduitDAO;
import model.Produit;
import java.util.*;

public class ProduitDAOImpl implements IProduitDAO {

    private static ProduitDAOImpl instance;
    private Map<Long, Produit> produits = new LinkedHashMap<>();
    private Long compteur = 1L;

    private ProduitDAOImpl() {
        add(new Produit("Clavier Mécanique",  "Cherry MX Red, rétro-éclairé RGB",     299.00));
        add(new Produit("Souris Gaming",       "Sans fil 16000 DPI, ergonomique",       149.00));
        add(new Produit("Écran 27\"",          "4K IPS 144Hz HDR400",                  1299.00));
        add(new Produit("Casque Audio",        "Bluetooth 5.0, réduction de bruit",     399.00));
    }

    public static synchronized ProduitDAOImpl getInstance() {
        if (instance == null) instance = new ProduitDAOImpl();
        return instance;
    }

    @Override
    public void add(Produit p) {
        p.setId(compteur++);
        produits.put(p.getId(), p);
    }

    @Override
    public void delete(Long id) { produits.remove(id); }

    @Override
    public void update(Produit p) { produits.put(p.getId(), p); }

    @Override
    public Produit findById(Long id) { return produits.get(id); }

    @Override
    public List<Produit> findAll() { return new ArrayList<>(produits.values()); }
}