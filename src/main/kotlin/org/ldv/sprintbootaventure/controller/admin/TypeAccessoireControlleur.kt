package org.ldv.sprintbootaventure.controller.admin

import org.ldv.sprintbootaventure.model.dao.TypeAccessoireDAO
import org.ldv.sprintbootaventure.model.entity.TypeAccessoire
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class TypeAccessoireControlleur(val typeAccessoireDAO: TypeAccessoireDAO) {
    @GetMapping("/admin/typeaccessoire")
    fun index(model: Model): String {
        // Récupère toutes les typeaccessoire depuis la base de données
        val typeaccessoires = this.typeAccessoireDAO.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("typeaccessoire", typeaccessoires)

        // Retourne le nom de la vue à afficher
        return "admin/typeaccessoire/index"
    }

    @GetMapping("/admin/typeaccessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'accessoire avec l'ID spécifié depuis la base de données
        val untypeaccessoire = this.typeAccessoireDAO.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("typeaccessoire", untypeaccessoire)

        // Retourne le nom de la vue à afficher
        return "admin/typeaccessoire/show"
    }

    @GetMapping("/admin/typeaccessoire/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de typeaccessoire avec des valeurs par défaut
        val nouveautypeaccessoire = TypeAccessoire(null, "", "", )

        // Ajoute le nouveau typeaccessoire au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouveautypeaccessoire", nouveautypeaccessoire)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/typeaccessoire/create"
    }

    @PostMapping("/admin/typeaccessoire")
    fun store(@ModelAttribute nouveautypeaccessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle qualité dans la base de données
        val savedtypeaccessoire = this.typeAccessoireDAO.save(nouveautypeaccessoire)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedtypeaccessoire.nom} réussi")
        // Redirige vers la page d'administration des types d'accessoires
        return "redirect:/admin/typeaccessoire"
    }
    @GetMapping("/admin/typeaccessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'accessoire avec l'ID spécifié depuis la base de données
        val untypeaccessoire = this.typeAccessoireDAO.findById(id).orElseThrow()

        // Ajoute le type d'accessoire au modèle pour affichage dans la vue
        model.addAttribute("typeaccessoire", untypeaccessoire)

        // Retourne le nom de la vue à afficher
        return "admin/typeaccessoire/edit"
    }

    @PostMapping("/admin/typeaccessoire/update")
    fun update(@ModelAttribute typeaccessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        // Recherche de le type d'accessoire existante dans la base de données
        val typeaccessoireModifier = this.typeAccessoireDAO.findById(TypeAccessoire.id ?: 0).orElseThrow()

        // Mise à jour des propriétés du TypeAccessoire avec les nouvelles valeurs du formulaire
        typeaccessoireModifier.nom = TypeAccessoire.nom
        typeaccessoireModifier.typebonus = TypeAccessoire.typebonus

        // Sauvegarde la qualité modifiée dans la base de données
        val savedtypeaccessoire = this.typeAccessoireDAO.save(typeaccessoireModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedtypeaccessoire.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/qualite"
    }


}

