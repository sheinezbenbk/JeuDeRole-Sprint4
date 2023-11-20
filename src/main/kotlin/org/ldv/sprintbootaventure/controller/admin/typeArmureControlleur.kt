package org.ldv.sprintbootaventure.controller.admin

import org.ldv.sprintbootaventure.model.dao.TypeArmureDAO
import org.ldv.sprintbootaventure.model.entity.TypeAccessoire
import org.ldv.sprintbootaventure.model.entity.TypeArmure
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class typeArmureControlleur (val typeArmureDAO: TypeArmureDAO){

    /**
     * Affiche la liste de toutes les qualités.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/typearmure")
    fun index(model: Model): String {
        // Récupère toutes les types armures depuis la base de données
        val typeArmure = this.typeArmureDAO.findAll()


        // Ajoute la liste des types Armures au modèle pour affichage dans la vue
        model.addAttribute("typeArmure", typeArmure)

        // Retourne le nom de la vue à afficher
        return "admin/typearmure/index"
    }

    @GetMapping("/admin/typearmure/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'armure avec l'ID spécifié depuis la base de données
        val untypearmure = this.typeArmureDAO.findById(id).orElseThrow()

        // Ajoute le type d'armure au modèle pour affichage dans la vue
        model.addAttribute("typearmure", untypearmure)

        // Retourne le nom de la vue à afficher
        return "admin/typearmure/show"
    }

    @GetMapping("/admin/typearmure/create")
    fun create(model: Model): String {

        // Crée une nouvelle instance de type d'armure avec des valeurs par défaut
        val nouveautypearmure = TypeArmure(null, "", "", )

        // Ajoute le nouveau type d'armure au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouveautypearmure", nouveautypearmure)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/typearmure/create"
    }

    @PostMapping("/admin/typearmure")
    fun store(@ModelAttribute nouveautypearmure: TypeArmure, redirectAttributes: RedirectAttributes): String {

        // Sauvegarde le nouveau type d'armure dans la base de données
        val savedtypearmure = this.typeArmureDAO.save(nouveautypearmure)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedtypearmure.nom} réussi")

        // Redirige vers la page d'administration des types d'armures
        return "redirect:/admin/typearmure"
    }





}