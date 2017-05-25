# QuickSort Animator - un fenomenale animatore di algoritmi

#### Di cosa stiamo parlando

Il QuickSort è uno dei migliori algoritmi per l'ordinamento dei dati.  È un algoritmo ricorsivo, ed è costruito in modo da dividere tutti i dati in porzioni sempre più piccole, ordinarle, e poi unire tutte queste porzioni per completare l'ordinamento complessivo. 

La chiave è la divisione ricorsiva in parti, perchè queste vengono formate prendendo un elemento singolo come riferimento (il *pivot*, o *perno*), e spostando gli elementi più piccoli prima del pivot (ovviamente in caso di ordinamento crescente), mentre gli elementi più grandi vengono spostati dopo. I gruppi così formati verranno continuamente divisi in due, e verranno ordinati applicando la stessa logica.

#### Un po' più in dettaglio

```pascal
QuickSort(ITEM[] Items, integer primo, integer ultimo)
 	 if (primo < ultimo) then
  			integer pivot ← Pivot(Items, primo, ultimo)
    			QuickSort(Items, primo, pivot - 1)
  			QuickSort(Items, pivot + 1, ultimo)

```

Questa versione pensa di ordinare una parte di `Items` che va da `Items[primo]` a `Items[ultimo]`. `QuickSort()` richiamerà sè stessa, a condizione che l'indice da cui cominciare sia prima di quello sul quale finire. Questo, di fatto, ci dice se abbiamo già raggiunto il gruppo di taglia minima (formato da un solo elemento) o no -- e in quest'ultimo caso occorre continuare la ricorsione. Ricorsione che comincia con chiamare `Pivot()`, che è la funzione che si occupa in primo luogo di ragguppare gli elementi prima o dopo dell'elemento di riferimento, e poi di capire quale dovrà essere il riferimento (ovvero il pivot) per il giro di ordinamento successivo. L'ordinamento proseguirà quindi sui due sottogruppi che vanno da `primo` a `pivot - 1` e da `pivot + 1` a `ultimo`.

`Pivot()` è costruita così:

```pascal
integer Pivot(ITEM[] Items, integer primo, integer ultimo)
		ITEM p ← Items[primo]
		integer j ← primo;
		for integer i ← primo to ultimo do
				if Items[i] < p then
						Items[i] ↔ Items[j + 1]
						j ← j + 1
		Items[j] ↔ p
		return j
```

Come prima cosa viene preso come elemento di riferimento il primo elemento della sequenza da ordinare, e viene impostato un indice ausiliario (`j`) , che indicherà la posizione finale del pivot. Dopodichè si scorre il vettore degli elementi: se si incontra un elemento più piccolo del pivot lo si scambia con quello in posizione successiva a `j` (che sarà comunque sempre prima di `i`, che indica la posizione dell'elemento correntemente esaminato), e si fa avanzare `j`.

Una volta esaminati tutti gli elementi, visto che `j` è sempre stato prima (o al limite nella stessa posizione) di i, il risultato sarà che tutti gli elementi più piccoli del pivot occuperanno le posizioni fino a j, mentre quelli più grandi saranno successivi. Il pivot, quindi, verrà spostato dalla prima alla j-esima posizione del vettore, perchè sarà più grande di tutti gli elementi che sono stati messi prima di j. A questo punto j indicherà la posizione del pivot, e quindi verrà ritornata al chiamante.

####  Considerazioni tecniche

+ L'algoritmo ha complessità media O(n log n) ,ottima per un algoritmo di ordinamento basato su confronti e scambi. Il caso peggiore è quello in cui viene sempre scelto come pivot l'elemento più piccolo o più grande dell'insieme (ovvero, nel nostro caso, quando il vettore è già ordinato), perchè questo porta ad avere sottoproblemi ricorsivi assolutamente sbilanciati l'uno rispetto all'altro, trovandoci un gruppo vuoto e un gruppo con tutti gli elementi, arrivando a complessità di circa O(n²).
+ La scelta del pivot può essere migliorata, prendendo un elemento a caso tra quelli del vettore -- o, meglio ancora, l'elemento medio tra una serie di elementi presi casualmente. Questo per evitare i problemi evidenziati nel punto precedente.


#### Il programma

+ Lo scopo del programma è quello di visualizzare l'esecuzione del QuickSort. L'utente può inserire dati da file, da tastiera o scegliere la generazione casuale (sempre rimandendo all'interno di un range di limitazione per la grandezza dell'input). L'esecuzione mostra ogni passo dell'ordinamento, e viene mostrata visualizzando i vari dati in rettangoli, con altezze in funzione della grandezza relativa dei dati che rappresentano. Vengono colorati i rettangoli al momento confrontati, insieme al pivot e ai rettangoli scambiati.

+ **Nota sull'acquisizione dei dati da file:** -- In caso di acquisizione dei dati da file, il programma acquisisce sempre la quantità di dati impostata nella maschera iniziale: se ne trova di meno completerà quelli trovati con dati nulli (0, o stringhe vuote), e se ne trova di più ignorerà quelli successivi. In caso di file misti, con input non consistente in tipo, il programma si limiterà a ignorare le righe in input con i dati che non sono del tipo richiesto. Il formato del file in input è di un dato per riga.


+ La documentazione delle classi si trova in `/doc`

  ![](http://www.icon100.com/up/3854/128/5-Light-bulb-outline-sign-inside-a-circle.png)

