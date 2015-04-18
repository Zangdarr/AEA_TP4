# AEA_TP4
Ce projet à pour but d'implémenter différents algortihmes de calcul d'arbres recouvrants de coût minimum et de les comparer.

## Algorithles programmés
Pour ce projet, deux algortihmes ont été implémentés et testés : 

* [Prim MST] qui est un algorthime glouton qui part d'un sommet et choisit l'arête qui minimise le poids à partir de celui-ci. L'ensemble s'agrandit alors et on recommence en partant du nouvelle ensemble formé pour l'agrandir.
* [Kruskal MST] qui va trier les arêtes par poids, et va les ajouter une par une en s'assurant de ne pas former de cycle.

## Remarques sur l'implémentation
* Pour [Prim MST], un mélange des arêtes est réalisé au début pour être sûr de partir d'un sommet quelconque, l'algorithme ensuite et très simple, on parcours les arête pour trouver celles qui sont aux extrémités de l'ensemble de sommets déjà marqués et déterminer laquelle minimise le poids global si on la rajoute. On effectue cette opération jusqu'à avoir tous les sommets dans notre ensemble et du coup (nbSommets - 1) arêtes.

* Pour [Kruskal MST], c'est encore un algorithme très simple, il s'agit de trier les arêtes du graphe par ordre croissant, puis de les rajouter une par une en sautant celles qui formeront un cycle si elles été prisent en compte. On s'arrête lorsque l'on a (nbSommet - 1) arêtes. Cependant cet algorithme possède un point extrèmement critique qui joue énormement sur ses performances : la recherche de cycle dans un graphe. Pour chaque arête que l'on va essayer de rajouter on va devoir lancer la recherche de cycle et plus on agrandit le graphe plus celui risque de prendre du temps. À mon grand bonheur, il existe une méthode optimal pour la recherche de cycles dans un graphe qui, vous le verrai dans la section Performances, permet à [Kruskal] de battre tout les records.

* La [Recherche de cycles] fonctionne de la façon suivante : on fur est à mesure que l'on rajoute des arêtes dans le [MST], va leur attribuer des groupes. Premier cas, si aucun des sommets de l'arête est déjà présent dans le graphe -> nouveau groupe. Second cas, si l'arête possède un et un seul sommet présent dans le graphe, cela signifie que ce sommet appartient à une arête qui appartient à un certain groupe -> ajout de l'arête à ce groupe. Et enfin le troisième cas, si les deux sommets appartiennent au graphe, deux possibilités : primo, les deux sommets sont dans le même groupe -> cycle donc on n'ajoute pas l'arête; secondo, les deux sommets appartiennent à des groupes différents -> on ajoute l'arête et on fusion les deux groupes en un seul.

## Performances
Ci dessous les executions des deux algorithmes sur différents graphes :

* Pour un graphe de 1000 sommets avec 0.7 pour les arêtes : 

        [[  PERFORMANCES  ]] 

        ---  EXECUTION TIME  ---
         - Kruskal MST time : 0.180555655
         - PRIM    MST time : 197.052687253
        
        ---  MST  ---
         - KRUSKAL :    Edges quantity : 999   Weight : 3.793941447E9
         - PRIM    :    Edges quantity : 999   Weight : 3.793941447E9

* Pour un graphe de 1000 sommets avec 1.0 pour les arêtes donc complet :
        [[  PERFORMANCES  ]] 

        ---  EXECUTION TIME  ---
         - Kruskal MST time : 0.221896845
         - PRIM    MST time : 293.993546456
        
        ---  MST  ---
         - KRUSKAL :    Edges quantity : 999   Weight : 2.368821534E9
         - PRIM    :    Edges quantity : 999   Weight : 2.368821534E9


* Pour un graphe de 100 sommets avec 0.7 pour les arêtes :
        [[  PERFORMANCES  ]] 

        ---  EXECUTION TIME  ---
         - Kruskal MST time : 0.010861531
         - PRIM    MST time : 0.059563592
        
        ---  MST  ---
         - KRUSKAL :    Edges quantity : 99   Weight : 1.71196025E8
         - PRIM    :    Edges quantity : 99   Weight : 1.71196025E8

* Pour un graphe de 100 sommets avec 1.0 pour les arêtes donc complet :
        [[  PERFORMANCES  ]] 

        ---  EXECUTION TIME  ---
         - Kruskal MST time : 0.011042555
         - PRIM    MST time : 0.07115683
        
        ---  MST  ---
         - KRUSKAL :    Edges quantity : 99   Weight : 1.15349073E8
         - PRIM    :    Edges quantity : 99   Weight : 1.15349073E8
* Pour un graphe de 10 sommets avec 0.7 pour les arêtes :
        [[  PERFORMANCES  ]] 
        
        ---  EXECUTION TIME  ---
         - Kruskal MST time : 0.001019296
         - PRIM    MST time : 9.90176E-4
        
        ---  MST  ---
         - KRUSKAL :    Edges quantity : 9   Weight : 11711.0
         - PRIM    :    Edges quantity : 9   Weight : 11711.0

* Pour un graphe de 10 sommets avec 1.0 pour les arêtes donc complet :
        [[  PERFORMANCES  ]] 

        ---  EXECUTION TIME  ---
         - Kruskal MST time : 0.001043611
         - PRIM    MST time : 0.001321029
        
        ---  MST  ---
         - KRUSKAL :    Edges quantity : 9   Weight : 12365.0
         - PRIM    :    Edges quantity : 9   Weight : 12365.0

## Remarques sur les performances
* Après analyse des résultats nous obtenons bien les mêmes poids pour les deux algorithme pour un graphe donné. 
* Avec l'implémentation qu'est la mienne [Kruskal] passe très clairement à l'échelle sans aucun problème contrairement à [Prim].

##BONUS
Voici l'execution de [Kruskal] sur le plus grand graphe que j'ai pu générer : 

        KRUSKAL OPTI  KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL  
        From file 4500x100.gph to graphe...
        done.
        KRUSKAL's running...
        Kruskal_OPTIMAL MST done.
        Execution time : 4.208086161
        Initialisation, Graphe :
         - poids : 1.0866181726352176E16
         - nombre de sommet : 4500
         - nombre d'arête : 10122750
        
        Result of KRUSKAL OPTI, Graphe :
         - poids : 2.549273908E9
         - nombre de sommets : 4500
         - nombre d'arêtes : 4499
        KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL OPTI KRUSKAL
