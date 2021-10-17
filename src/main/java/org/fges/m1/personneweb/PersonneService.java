package org.fges.m1.personneweb;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
    private final List<Personne> personnes;

    public PersonneService() {
        personnes = new ArrayList<>();
        personnes.add(new Personne(0, "bob", "bob"));
        personnes.add(new Personne(1, "bill", "bill"));
        personnes.add(new Personne(2, "jules", "jules"));
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public Personne addPersonne(Personne personne) {
        personnes.add(personne);
        return personne;
    }

    public Personne modifyPersonne(Personne personne) {
        final Optional<Personne> optionalPersonne = getPersonneById(personne.getId());
        if (optionalPersonne.isEmpty()) {
            throw new IllegalArgumentException("Personne non trouvée");
        }

        Personne personne1 = optionalPersonne.get();
        personne1.setNom(personne.getNom());
        personne1.setPrenom(personne.getPrenom());
        return personne1;
    }

    public void deletePersonne(int id) {
        final Optional<Personne> optionalPersonne = getPersonneById(id);
        Personne personne = optionalPersonne.orElseThrow(() -> new IllegalArgumentException("Personne non trouvée"));
        personnes.remove(personne);
    }

    public Optional<Personne> getPersonneById(int personneId) {
        return personnes.stream().filter(personne1 -> personne1.getId() == personneId).findFirst();
    }
}