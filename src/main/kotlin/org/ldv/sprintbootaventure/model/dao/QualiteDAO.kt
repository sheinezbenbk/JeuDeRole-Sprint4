package org.ldv.sprintbootaventure.model.dao

import org.ldv.sprintbootaventure.controller.admin.Qualite
import org.springframework.data.jpa.repository.JpaRepository

interface QualiteDAO: JpaRepository<Qualite,Long>{
}