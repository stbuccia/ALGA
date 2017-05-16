# ALGA - un fighissimo animatore di algoritmi



- [ ] Finestre
  - [ ] Centrare tuttoi
  - [ ] Font uguale
  - [ ] seconda decente

- [x] Animazioni

- [x] Decidere come fare i colori

- [ ] Legenda

- [ ] Scrivere readme
  - [ ] Pseudocodice
  - [ ] fare in modo che abbiamo rich text nella finestra del file. C'è un modo elegante, senza dovercelo fare tutto a manazza? Il top sarebbe riuscire a trasformare la formattazione in markdown in maniera automatica

- [ ] Javadoc

- [ ] Decidere dimensione fissa finestre per comodità

      ​


## Cose da risolvere

- QSORTVIEW: Esecuzione per passi non va avanti se si conclude prima un ordinamento completo in automatico
- WELCOMEVIEW: Slider non reimposta etichetta dello stato: se prima questa era attiva perchè un valore non era ammissibile, cambiare lo slider oltre a reimpostare il testo dell'etichetta dovrebbe anche reimpostare lo stato, perchè il valore che prima non andava bene viene sovrascritto con un numero 
- QSORTVIEW: Le stringhe non hanno la barra dell'altezza del pivot
- WELCOMEVIEW: Sarebbe carino trovare un modo per avere un riferimento assoluto (magari far funzionare getClass().getResource()) al file dei dati con cui settare la casella del percorso, in modo da non avere il percorso di default che dipende dall'utente