package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
class LigneInventaire (

    @EmbeddedId
    var ligneInventaireId : ligneInventaireId? = null,

    @MapsId("personnageId")
    @ManyToOne
    @JoinColumn(name = "personnage_id")
    var personnage: Personnage? = null,

    @MapsId("itemId")
    @ManyToOne
    @JoinColumn(name = "item_id")
    var item: Item? = null,

    val quantite: Int,
 ) {
}