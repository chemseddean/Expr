# POBJ Arc 3 : Simplification d'expressions arithmétiques (TME 7 et 8)

### Support de TME du cours programmation par objets (LU3NI002), Licence 3, Sorbonne Université, Paris, France.

# Objectifs pédagogiques : polymorphisme et surcharge, composite et visiteur

L’objectif de ce TME est d’effectuer des traitements (calculs, simplifications, dérivations) sur des
expressions arithmétiques, permettant ainsi d’illustrer les notions de surcharge et de polymorphisme
paramétrique. Ce TME est basé sur le partiel de l’année 2012–2013 proposé par Emmanuel Chailloux
(épreuve individuelle en 3 heures) et son système de correction automatique.
Nous vous fournissons ici les jeux de test utilisés lors de la correction afin de vous permettre de vous
auto-évaluer.
L’énoncé couvre les deux séances ; il n’y a pas d’énoncé séparé pour chaque TME. Vous ferez un
rendu à votre responsable comme d’habitude à la fin de chaque TME, où vous préciserez la question
atteinte à la fin de la séance.
Un objectif à viser pour la séance de TME 7 est de compléter les questions 1 à 7, qui présentent la
migration d’une architecture basée sur Composite à une architecture basée sur Visiteur. Dans ces
questions, nous ne traitons pas de façon complète les variables apparaissant dans les expressions.
Les questions 8 à 13 mettent en place un moteur de calcul symbolique plus complet (couvrant aussi
les variables), en combinant des visiteurs entre eux. Il permet notamment de calculer la dérivée
partielle d’une expression et de simplifier les expressions.
Le squelette de projet Expr est disponible sur le serveur GitLab. Comme pour les projets de
TME précédents, vous devez commencer par en faire un fork et à l’importer (cloner) sous Eclipse.
Toutes les classes fournies et demandées sont dans le package pobj.expr, et les tests unitaires dans
pobj.expr.test.
