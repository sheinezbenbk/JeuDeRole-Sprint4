package org.ldv.sprintbootaventure.controller.admin

import org.ldv.sprintbootaventure.model.dao.TypeArmeDAO
import org.ldv.sprintbootaventure.model.entity.TypeArme
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class TypeArmeControlleur (val typeArmeDAO: TypeArmeDAO){
    /**
     * Affiche la liste de toutes les qualités.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/TypeArmeControlleur")
    fun index(model: Model): String {
        // Récupère toutes les qualités depuis la base de données
        val typeArme= this.typeArmeDAO.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("typeArme", typeArme)

        // Retourne le nom de la vue à afficher
        return "admin/typeArmeControlleur/index"
    }
    @GetMapping("/admin/TypeArmeControlleur/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneQualite = this.typeArmeDAO.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("qualite", uneQualite)

        // Retourne le nom de la vue à afficher
        return "admin/TypeArmeControlleur/show"
    }
    /**
     * Affiche le formulaire de création d'une nouvelle qualité.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/TypeArmeControlleur/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Qualite avec des valeurs par défaut
        val nouveauTypeArme = Qualite(null, "", "", 0)

        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleQualite", nouveauTypeArme)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/TypeArmeControlleur/create"
    }
    /**
     * Gère la soumission du formulaire d'ajout de qualité.
     *
     * @param nouvelleQualite L'objet Qualite créé à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après l'ajout réussi.
     */
//    @PostMapping("/admin/qualite")
//    fun store(@ModelAttribute nouvelleQualite: Qualite, redirectAttributes: RedirectAttributes): String {
//        // Sauvegarde la nouvelle qualité dans la base de données
//        val savedQualite = this.typeArmeDAO.save(nouvelleQualite)
//        // Ajoute un message de succès pour être affiché à la vue suivante
//        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedQualite.nom} réussi")
//        // Redirige vers la page d'administration des qualités
//        return "redirect:/admin/qualite"
//    }
}