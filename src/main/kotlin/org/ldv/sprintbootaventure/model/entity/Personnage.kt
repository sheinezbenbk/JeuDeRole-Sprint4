package org.ldv.sprintbootaventure.model.entity

import jakarta.persistence.*


@Entity
class Personnage constructor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    val nom : String,
    val pointDeVie : Int,
    val pointDeVieMax : Int,
    val attaque : Int,
    val defense : Int,
    val endurance : Int,
    val vitesse : Int,
    val cheminImage : String,

@OneToMany (mappedBy = "personnage")
val ligneInventaire: List<LigneInventaire> = mutableListOf()

)
{

//    open fun attaque(adversaire: Personnage) {
//        //TODO Mission 4.1
//        if (armePrincipal != null) { //Si une arme est équipée, on additionne ses dégats aux dégats totaux.
//            var degats = this.armePrincipal!!.calculerDegats() + (this.attaque / 2)
//            degats -= adversaire.calculeDefense()
//            if (degats <= 0) {
//                degats = 1
//            }
//            adversaire.pointDeVie -= degats
//            println("$nom attaque ${adversaire.nom} avec son arme ${armePrincipal} et inflige $degats points de dégâts.")
//        } else { //Si une arme n'est pas équipée alors un 1 point de dégats sera ingligée
//            var degats = this.attaque / 2
//            degats -= adversaire.calculeDefense()
//            if (degats <= 0) {
//                degats = 1
//            }
//            println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
//        }
//    }




}