package org.ldv.sprintbootaventure.controller.admin

import org.ldv.sprintbootaventure.model.dao.TypeAccessoireDAO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TypeAccessoireControlleur(val typeAccessoireDAO : TypeAccessoireDAO){
    @GetMapping("/admin/typeaccessoire")
    fun index(model: Model): String {
        // Récupère toutes les typeaccessoire depuis la base de données
        val typeaccessoires = this.typeAccessoireDAO.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("typeaccessoire", typeaccessoires)

        // Retourne le nom de la vue à afficher
        return "admin/typeaccessoire/index"
    }

    @GetMapping("/admin/typeaccessoire/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Qualite avec des valeurs par défaut
        val nouveautypeaccessoire = Qualite(null, "", "", 0)

        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouveautypeaccessoire", nouveautypeaccessoire)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/typeaccessoire/create"
}}