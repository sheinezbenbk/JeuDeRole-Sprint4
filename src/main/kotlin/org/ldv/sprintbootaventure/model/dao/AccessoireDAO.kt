package org.ldv.springbootaventure.model.dao

import org.ldv.springbootaventure.model.entity.Accessoire
import org.springframework.data.jpa.repository.JpaRepository


interface AccessoireDAO: JpaRepository<Accessoire, Long> {


    fun findByNomContains(nom: String): List<Accessoire>

}