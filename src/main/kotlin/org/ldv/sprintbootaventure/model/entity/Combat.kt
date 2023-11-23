package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.springframework.data.annotation.Id

class Combat (var id: Long,var nombreTours: Int) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


}