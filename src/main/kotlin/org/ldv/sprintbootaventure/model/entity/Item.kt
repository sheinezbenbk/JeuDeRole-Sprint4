package org.ldv.sprintbootaventure.model.entity
import jakarta.persistence.*
import org.ldv.sprintbootaventure.model.dao.QualiteDAO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes


// Utilise l'héritage avec une seule table pour stocker les données de toutes les sous-classes dans la même table.
// La colonne "discriminateur" est utilisée pour différencier les types d'objets stockés dans la table.
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    @DiscriminatorColumn(name = "discriminateur")

// Déclare la classe comme une entité JPA
    @Entity

// Déclare la classe comme abstraite, ce qui signifie qu'elle ne peut pas être instanciée directement.
    open abstract class Item constructor(
// Clé primaire auto-incrémentée
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null,
// Nom de l'item
        var nom: String,
// Description de l'item
        var description: String,
//Chemin vers l'image de l'item
        var cheminImage:String?
    ) {
// TODO sprint 5: methode utiliser

    }

