package model;

public class Reservation {
	private Client client;
	private String type;
    private double prix;
    private String vue;
    
    public Reservation(Client client, String type, double prix, String vue) {
        this.client = client;
        this.type = type;
        this.prix = prix;
        this.vue = vue;
    }

    
    public Client getClient() { 
    	return client; 
    	}
    public void setClient(Client client) {
    	this.client = client;
    	}

    
    public String getType() { 
    	return type;
    	}
    public void setType(String type) {
    	this.type = type; 
    	}
    

    public double getPrix() {
    	return prix;
    	}
    public void setPrix(double prix) {
    	this.prix = prix;
    	}

    public String getVue() { 
    	return vue; 
    	}
    public void setVue(String vue) { 
    	this.vue = vue;
    	}


}
