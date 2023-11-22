package org.ldv.springbootaventure.controller.admin


import org.ldv.springbootaventure.model.dao.ArmeDAO
import org.ldv.sprintbootaventure.model.entity.Arme
import org.ldv.sprintbootaventure.model.dao.QualiteDAO
import org.ldv.sprintbootaventure.model.dao.TypeArmeDAO
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
class ArmeControlleur (val armeDao: ArmeDAO, val qualiteDao: QualiteDAO, val typeArmeDao: TypeArmeDAO) {

    @GetMapping("/admin/Arme")
    fun index(model : Model): String {
        // Récupère toutes les qualités depuis la base de données
        val Armes = this.armeDao.findAll()

        // Ajoute la liste des qualités au modèle pour affichage dans la vue
        model.addAttribute("Armes", Armes)

        // Retourne le nom de la vue à afficher
        return "admin/Arme/index"
    }

    @GetMapping("/admin/Arme/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneArme = this.armeDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("Arme", uneArme)

        // Retourne le nom de la vue à afficher
        return "admin/Arme/show"
    }

    /**
     * Affiche le formulaire de création d'une nouvelle qualité.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/Arme/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Arme avec des valeurs par défaut
        val nouvelleArme = Arme(null, "", "","")
        val qualites = qualiteDao.findAll()
        model.addAttribute("qualites",qualites)
        val typeArmes = typeArmeDao.findAll()
        model.addAttribute("typeArmes",typeArmes)


        // Ajoute la nouvelle qualité au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelleArme", nouvelleArme)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/Arme/create"
    }

    @PostMapping("/admin/Arme")
    fun store(@ModelAttribute nouvelleArme: Arme, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle qualité dans la base de données
        val savedArme = this.armeDao.save(nouvelleArme)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedArme.id} réussi")
        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/Arme"
    }

    @GetMapping("/admin/Arme/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère la qualité avec l'ID spécifié depuis la base de données
        val uneArme = this.armeDao.findById(id).orElseThrow()

        // Ajoute la qualité au modèle pour affichage dans la vue
        model.addAttribute("Arme", uneArme)

        val qualites = qualiteDao.findAll()
        model.addAttribute("qualites",qualites)
        val typeArmes = typeArmeDao.findAll()
        model.addAttribute("typeArmes",typeArmes)

        // Retourne le nom de la vue à afficher
        return "admin/Arme/edit"
    }

    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param Arme L'objet Arme mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la mise à jour réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Arme/update")
    fun update(@ModelAttribute Arme: Arme, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité existante dans la base de données
        val armeModifier = this.armeDao.findById(Arme.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de la qualité avec les nouvelles valeurs du formulaire
        armeModifier.nom = Arme.nom
        armeModifier.description = Arme.description
        armeModifier.cheminImage = Arme.cheminImage
        armeModifier.typeArme = Arme.typeArme
        armeModifier.qualite = Arme.qualite


        // Sauvegarde la qualité modifiée dans la base de données
        val savedArme = this.armeDao.save(armeModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedArme.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/Arme"
    }

    /**
     * Gère la suppression d'une qualité par son identifiant.
     *
     * @param id L'identifiant de la qualité à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des qualités après la suppression réussie.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Arme/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche de la qualité à supprimer dans la base de données
        val Arme: Arme = this.armeDao.findById(id).orElseThrow()

        // Suppression de la qualité de la base de données
        this.armeDao.delete(Arme)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${Arme.nom} réussie")

        // Redirige vers la page d'administration des qualités
        return "redirect:/admin/Arme"
    }


}