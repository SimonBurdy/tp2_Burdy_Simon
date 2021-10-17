package org.fges.m1.personneweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    /* Affiche toutes les personnes */
    @GetMapping("/personnes")
    public String getUsers(Model model) {
        List<Personne>  allPerson = personneService.getPersonnes();
        model.addAttribute("personnes", allPerson);
        return "personnes";
    }

    /* Affiche une page pour ajouter une nouvelle personne */
    @GetMapping("/personnes/new")
    public String newPersonne(Model model) {
        model.addAttribute("personne", new Personne());
        return "newpersonne";
    }

    /* Montre les informations d'une personne */
    @GetMapping("personnes/{id}")
    public String detailPersonne(@PathVariable("id") int personId, Model model)  {
        Optional<Personne> person  = personneService.getPersonneById(personId);
        model.addAttribute("personne", person);
        return "detailPersonne";
    }

    /* Supprime une personne */
    @GetMapping("personnes/{id}/delete")
    public String deletePersonne(@PathVariable("id") int personId, Model model)  {
        personneService.deletePersonne(personId);
        return "redirect:/personnes";
    }

    /* Ajoute ou modifier une personne */
    @PostMapping("/personnes/save")
    public String createUpdatePersonne(@ModelAttribute Personne personne, Model model) {
        Optional<Personne> person = personneService.getPersonneById(personne.getId());
        Personne newPersonne;
        if (person.isEmpty()) {
            newPersonne =  personneService.addPersonne(personne);
        } else {
            newPersonne = personneService.modifyPersonne(personne);
        }
        model.addAttribute("personne", newPersonne);
        return "redirect:/personnes";
    }


}