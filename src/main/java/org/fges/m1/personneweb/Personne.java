package org.fges.m1.personneweb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Personne {
    private Integer id;
    private String nom;
    private String prenom;

    public Personne() {

    }
}
