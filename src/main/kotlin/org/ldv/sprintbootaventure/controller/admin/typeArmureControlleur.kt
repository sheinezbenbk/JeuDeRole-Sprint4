package org.ldv.sprintbootaventure.controller.admin

import org.ldv.sprintbootaventure.model.dao.TypeArmureDAO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model
@Controller
class typeArmureControlleur (val typeArmureDAO: TypeArmureDAO){

    /**
     * Affiche la liste de toutes les qualités.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/typeArmure")
    fun index(model: Model): String {
        // Récupère toutes les qualités depuis la base de données
        val typeArmure = this.typeArmureDAO.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue

        model.addAttribute("typeArmure", typeArmure)

        // Retourne le nom de la vue à afficher
        return "admin/typeArmureControlleur/index"
    }


}