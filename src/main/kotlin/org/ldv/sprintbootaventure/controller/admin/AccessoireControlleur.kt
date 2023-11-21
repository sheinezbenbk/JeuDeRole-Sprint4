package org.ldv.springbootaventure.controller.admin


import org.ldv.springbootaventure.model.dao.AccessoireDao
import org.ldv.springbootaventure.model.entity.Accessoire
import org.ldv.sprintbootaventure.model.dao.QualiteDAO
import org.ldv.sprintbootaventure.model.dao.TypeAccessoireDAO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * Affiche les détails d'une qualité en particulier.
 *
 * @param id L'identifiant unique de la qualité à afficher.
 * @param model Le modèle utilisé pour transmettre les données à la vue.
 * @return Le nom de la vue à afficher.
 * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée.
 */

@Controller
class AccessoireControlleur (val accessoireDao: AccessoireDao, val qualiteDao: QualiteDAO, val typeAccessoireDao: TypeAccessoireDAO) {

    @GetMapping("/admin/Accessoire")
    fun index(model : Model): String {
        // Récupère toutes les qualités depuis la base de données
        val Accessoires = this.accessoireDao.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("Accessoires", Accessoires)

        // Retourne le nom de la vue à afficher
        return "admin/Accessoire/index"
    }

    @GetMapping("/admin/Accessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneAccessoire = this.accessoireDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("Accessoire", uneAccessoire)

        // Retourne le nom de la vue à afficher
        return "admin/Accessoire/show"
    }

    /**
     * Affiche le formulaire de création d'une nouvelle qualité.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/Accessoire/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Accessoire avec des valeurs par défaut
        val nouvelleAccessoire = Accessoire(null, "", "","")
        val qualites = qualiteDao.findAll()
        model.addAttribute("qualites",qualites)
        val typeAccessoires = typeAccessoireDao.findAll()
        model.addAttribute("typeAccessoires",typeAccessoires)


        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleAccessoire", nouvelleAccessoire)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/Accessoire/create"
    }

    @PostMapping("/admin/Accessoire")
    fun store(@ModelAttribute nouvelleAccessoire: Accessoire, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle qualité dans la base de données
        val savedAccessoire = this.accessoireDao.save(nouvelleAccessoire)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedAccessoire.id} réussi")
        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/Accessoire"
    }

    @GetMapping("/admin/Accessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneAccessoire = this.accessoireDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("Accessoire", uneAccessoire)

        val qualites = qualiteDao.findAll()
        model.addAttribute("qualites",qualites)
        val typeAccessoires = typeAccessoireDao.findAll()
        model.addAttribute("typeAccessoires",typeAccessoires)

        // Retourne le nom de la vue à afficher
        return "admin/Accessoire/edit"
    }

    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param Accessoire L'objet Accessoire mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la mise à jour réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Accessoire/update")
    fun update(@ModelAttribute Accessoire: Accessoire, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité existante dans la base de données
        val accessoireModifier = this.accessoireDao.findById(Accessoire.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de la qualité avec les nouvelles valeurs du formulaire
        accessoireModifier.nom = Accessoire.nom
        accessoireModifier.description = Accessoire.description
        accessoireModifier.cheminImage = Accessoire.cheminImage
        accessoireModifier.typeAccessoire = Accessoire.typeAccessoire
        accessoireModifier.qualite = Accessoire.qualite


        // Sauvegarde la qualité modifiée dans la base de données
        val savedAccessoire = this.accessoireDao.save(accessoireModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedAccessoire.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/Accessoire"
    }

    /**
     * Gère la suppression d'une qualité par son identifiant.
     *
     * @param id L'identifiant de la qualité à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la suppression réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Accessoire/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité à supprimer dans la base de données
        val accessoire: Accessoire = this.accessoireDao.findById(id).orElseThrow()

        // Suppression de la qualité de la base de données
        this.accessoireDao.delete(accessoire)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${accessoire.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/Accessoire"
    }


}