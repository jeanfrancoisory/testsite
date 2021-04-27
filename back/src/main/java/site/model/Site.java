package site.model;

import java.util.ArrayList;
import java.util.List;

public class Site {

    private List<Personne> listCompte;

    public Site() {
        this.listCompte = new ArrayList<Personne>();
    }

    public List<Personne> getListCompte() {
        return listCompte;
    }

    public void addPersonne(Personne personne) {
        this.listCompte.add(personne);
    }

    public boolean checkEmail(Personne personne) {
        return this.listCompte.stream()
                .anyMatch(p -> p.getEmail().equals(personne.getEmail()));
    }
}
