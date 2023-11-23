package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.*
import javax.accessibility.AccessibleRole

@Entity
class Utilisateur(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var email : String,
    var mdp: String,
    v
) {
}