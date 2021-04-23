package site.model;

import java.util.List;

public class Site {

    private List<Personne> listCompte;

    public Site() {
    }

    public List<Personne> getListCompte() {
        return listCompte;
    }

    public void addPersonne(Personne personne) {
        this.listCompte.add(personne);
    }
}
