package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class ligneInventaireId(
    val personnageId: Long,
    val itemId: Long
) : Serializable
{

}