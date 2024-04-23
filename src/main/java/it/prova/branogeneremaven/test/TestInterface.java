package it.prova.branogeneremaven.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import it.prova.branogeneremaven.model.Brano;
import it.prova.branogeneremaven.model.Genere;
import it.prova.branogeneremaven.service.BranoService;
import it.prova.branogeneremaven.service.GenereService;
import it.prova.branogeneremaven.service.MyServiceFactory;

public interface TestInterface {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		BranoService branoServiceInstance = MyServiceFactory.getBranoServiceInstance();
		GenereService genereServiceInstance = MyServiceFactory.getGenereServiceInstance();
		
		while (!exit) {
			System.out.println("\n *******Benvenuto nel programma Brano-Genere.******* \n Scegli una delle seguenti opzioni: ");
			System.out.println("1. Brano: Inserisci un brano. ");
			System.out.println("2. Brano: Lista completa brani. ");
			System.out.println("3. Brano: Dammi un brano. ");
			System.out.println("4. Brano: Aggiorna un brano. ");
			System.out.println("5. Brano: Cancella un brano ");
//			System.out.println("6. Brano: Carica brano per genere. ");
//			System.out.println("7. Brano: Rimuovi brano scollegando prima il genere. ");
//			System.out.println("8. Brano: Descrizioni generi associati ad un brano. ");
//			System.out.println("9. Brano: Brani con descrizioni da più di 10 caratteri. ");
			System.out.println("10. Genere: Inserisci un genere. ");
			System.out.println("11. Genere: Lista completa generi. ");
			System.out.println("12. Genere: Dammi un genere. ");
			System.out.println("13. Genere: Aggiorna un genere. ");
			System.out.println("14. Genere: Cancella un genere. ");
//			System.out.println("15. Genere: Cerca per descrizione. ");
//			System.out.println("16. Genere: Generi di brani pubblicati in un intervallo temporale. ");
			System.out.println("17. Esci dal programma. ");

			int choice = 0;
	        try {
	            String input = scanner.nextLine();
	            choice = Integer.parseInt(input);

				switch (choice) {

				case 1:
					System.out.println("Hai scelto il comando 1");
					System.out.println("Qual è il titolo del brano che vuoi inserire? ");
					
					try {
						System.out.println("Inserisci il titolo del brano:");
						String titoloB1 = scanner.nextLine();
						
						System.out.println("Inserisci l'autore del brano:");
						String autoreB1 = scanner.nextLine();
						
						System.out.println("Inserisci la data di pubblicazione del brano [dd.MM.yyyy]:");
				        LocalDate dataB1 = null;
				        boolean dataGiusta = false;
				        while (!dataGiusta) {
				            try {
				                String dataPubblicazioneB1 = scanner.nextLine();
				                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				                dataB1 = LocalDate.parse(dataPubblicazioneB1, formatter);
				                System.out.println("Data di pubblicazione valida: " + dataB1);
				                dataGiusta = true; 
				            } catch (DateTimeParseException e) {
				                System.out.println("La data inserita non è valida, usa il formato dd.MM.yyyy. Riprova:");
				            }
				        }
				        
						System.out.println("Inserisci i generi del brano (separa i nomi con virgole):");
						String generiInput = scanner.nextLine();
						String[] descrizioneGeneri = generiInput.split(",");
						
						Set<Genere> generi = new HashSet<>();
						for (String descrizioneGenere : descrizioneGeneri) {
							descrizioneGenere = descrizioneGenere.trim();
							Genere genere = genereServiceInstance.cercaPerDescrizione(descrizioneGenere);
							if (genere == null) {
								genere = new Genere();
								genere.setDescrizione(descrizioneGenere);
								genereServiceInstance.insert(genere);
							}
							generi.add(genere);
						}
						
						Brano nuovoBrano = new Brano();
						nuovoBrano.setTitolo(titoloB1);
						nuovoBrano.setAutore(autoreB1);
						nuovoBrano.setDataPubblicazione(dataB1);
						nuovoBrano.setGeneri(generi);
						
						BranoService branoService = MyServiceFactory.getBranoServiceInstance();
						branoService.insert(nuovoBrano);
						
						System.out.println("Nuovo brano inserito con successo e associato ai generi!");
						
					} catch (DateTimeParseException e) {
						System.out.println("Errore: La data inserita non è valida.");
					} catch (Exception e) {
						System.out.println("Errore durante l'inserimento del brano: " + e.getMessage());
						e.printStackTrace();
					}
					break;
					
				case 2:
					System.out.println("Hai scelto il comando 2");
					System.out.println("Ecco la lista di tutti i brani: ");
					List<Brano> tuttiIBrani;
					try {
						tuttiIBrani = branoServiceInstance.list();
						if (tuttiIBrani.isEmpty()) {
							System.out.println("Non ci sono brani al momento. ");
						} else {
							for (Brano brano : tuttiIBrani) {
								System.out.println(brano.toString());
							}
						}
					} catch (Exception e) {
						System.out.println("Errore durante il caricamento della lista brani. " + e.getMessage());
						e.printStackTrace();
					}
					break;

				case 3:
					System.out.println("Hai scelto il comando 3");
					System.out.println("Di quale brano vuoi i dettagli? ");
					System.out.println("Inserisci l'id: ");
					try {
						Long idB3 = Long.parseLong(scanner.nextLine());
				        Brano brano = branoServiceInstance.get(idB3);
						if (brano != null) {
							System.out.println(brano);
						} else {
				            System.out.println("Nessun brano trovato con l'ID fornito.");
				        }
				    } catch (NumberFormatException e) {
				        System.out.println("Errore: L'ID deve essere un numero.");
				    } catch (Exception e) {
				        System.out.println("Si è verificato un errore: " + e.getMessage());
				        e.printStackTrace();
				    }
				    break;
				    
				case 4:
					System.out.println("Hai scelto il comando 4");
					System.out.println("Quale brano vuoi aggiornare? ");
					System.out.println("Inserisci l'id: ");
					try {
						Long idB4 = Long.parseLong(scanner.nextLine());
						Brano brano = branoServiceInstance.get(idB4);
						if (brano != null) {
							System.out.println("Brano corrente: " + brano);
						} else {
							System.out.println("L'id inserito non è valido. ");
						}

						System.out.println("Inserisci il nuovo titolo:");
						brano.setTitolo(scanner.nextLine());

						System.out.println("Inserisci il nuovo autore:");
						brano.setAutore(scanner.nextLine());

						System.out.println("Inserisci la data di pubblicazione del brano [dd.MM.yyyy]:");
				        LocalDate dataB4 = null;
				        boolean dataGiusta = false;
				        while (!dataGiusta) {
				            try {
				                String dataPubblicazioneB4 = scanner.nextLine();
				                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				                dataB4 = LocalDate.parse(dataPubblicazioneB4, formatter);
				                brano.setDataPubblicazione(dataB4);
				                System.out.println("Data di pubblicazione valida: " + dataB4);
				                dataGiusta = true; 
				            } catch (DateTimeParseException e) {
				                System.out.println("La data inserita non è valida, usa il formato dd.MM.yyyy. Riprova:");
				            }
				        }

						System.out.println("Inserisci i generi del brano (separa i nomi con virgole):");
				        String generiInput = scanner.nextLine();
				        String[] descrizioneGeneri = generiInput.split(",");

				        Set<Genere> generi = new HashSet<>();
				        for (String descrizioneGenere : descrizioneGeneri) {
				        	descrizioneGenere = descrizioneGenere.trim();
				            Genere genere = genereServiceInstance.cercaPerDescrizione(descrizioneGenere);
				            if (genere == null) {
				                genere = new Genere();
				                genere.setDescrizione(descrizioneGenere);
				                genereServiceInstance.insert(genere);
				            }
				            generi.add(genere);
				        }
				        brano.setGeneri(generi);
				        branoServiceInstance.update(brano);
				        System.out.println("Nuovo brano inserito con successo e associato ai generi!");
				    } catch (DateTimeParseException e) {
				        System.out.println("Errore: La data inserita non è valida.");
				    } catch (Exception e) {
				        System.out.println("Errore durante l'inserimento del brano: " + e.getMessage());
				        e.printStackTrace();
				    }
				    break;
					
				case 5:
					System.out.println("Hai scelto il comando 5");
					System.out.println("Quale brano vuoi rimuovere? ");
					System.out.println("Inserisci l'id: ");
					boolean success = false;
				    while (!success) {
				        try {
				            Long idB5 = Long.parseLong(scanner.nextLine());
				            branoServiceInstance.delete(idB5);
				            System.out.println("Brano rimosso con successo. ");
				            success = true;
				        } catch (NumberFormatException e) {
				            System.out.println("Errore: L'ID deve essere un numero. Riprova:");
				        } catch (Exception e) {
				            System.out.println("Errore durante la rimozione del brano: " + e.getMessage());
				            break; 
				        }
				    }
				    break;

//				case 6:
//					System.out.println("Hai scelto il comando 6");
//					System.out.println("Di quale brano vuoi caricare i dettagli, inclusi i generi? ");
//					System.out.println("Inserisci l'id del brano: ");
//					try {
//					    Long idB6 = Long.parseLong(scanner.nextLine()); 
//					    Brano brano = branoServiceInstance.caricaBranoPerGenere(idB6);
//					    if (brano != null) {
//					        System.out.println("Dettagli del brano trovato:");
//					        System.out.println("Titolo: " + brano.getTitolo());
//					        System.out.println("Autore: " + brano.getAutore());
//					        System.out.println("Data di Pubblicazione: " + brano.getDataPubblicazione());
//					        System.out.print("Generi Associati: ");
//					        if (brano.getGeneri() != null && !brano.getGeneri().isEmpty()) {
//					            for (Genere genere : brano.getGeneri()) {
//					                System.out.print(genere.getDescrizione() + " ");
//					            }
//					            System.out.println();
//					        } else {
//					            System.out.println("Nessun genere associato a questo brano. ");
//					        }
//					    } else {
//					        System.out.println("Nessun brano trovato con l'ID fornito. ");
//					    }
//					} catch (NumberFormatException e) {
//					    System.out.println("Errore: L'ID deve essere un numero. ");
//					} catch (Exception e) {
//					    System.out.println("Si è verificato un errore durante il caricamento del brano: " + e.getMessage());
//					    e.printStackTrace();
//					}
//					break;
//
//				case 7:
//					System.out.println("Hai scelto il comando 7");
//					System.out.println("Quale brano vuoi rimuovere scollegando i generi? ");
//					System.out.println("Inserisci l'id del brano: ");
//				    try {
//				        Long idB7 = Long.parseLong(scanner.nextLine()); 
//				        branoServiceInstance.rimuoviBranoMaPrimaScollega(idB7);
//				        System.out.println("Brano e relative associazioni ai generi rimossi con successo.");
//				    } catch (NumberFormatException e) {
//				        System.out.println("Errore: L'ID deve essere un numero.");
//				    } catch (Exception e) {
//				        System.out.println("Si è verificato un errore durante la rimozione del brano: " + e.getMessage());
//				        e.printStackTrace();
//				    }
//				    break;
//
//				case 8:
//					System.out.println("Hai scelto il comando 8");
//					System.out.println("Di quale brano vuoi la lista dei generi? ");
//					
//				    try {
//				        Long idB8 = Long.parseLong(scanner.nextLine());
//				        List<Genere> generi = branoServiceInstance.descrizioneGeneriAssociatiBrano(idB8);
//				        if (generi != null && !generi.isEmpty()) {
//				            System.out.println("Generi associati al brano:");
//				            for (Genere genere : generi) {
//				                System.out.println("Descrizione genere: " + genere.getDescrizione());
//				            }
//				        } else {
//				            System.out.println("Nessun genere trovato per il brano specificato o brano non esistente.");
//				        }
//				    } catch (NumberFormatException e) {
//				        System.out.println("Errore: L'ID deve essere un numero.");
//				    } catch (Exception e) {
//				        System.out.println("Si è verificato un errore durante il caricamento dei generi: " + e.getMessage());
//				        e.printStackTrace();
//				    }
//				    break;
//
//
//				case 9:
//					System.out.println("Hai scelto il comando 9");
//					System.out.println("Ecco la lista dei brani con una descrizione del genere più lunga di 10 caratteri: ");
//					
//				    try {
//				        List<Brano> brani = branoServiceInstance.conDescrizioneDaPiuDieci();
//				        if (brani != null && !brani.isEmpty()) {
//				            for (Brano brano : brani) {
//				                System.out.println("Brano: " + brano.getTitolo() + ", Autore: " + brano.getAutore());
//				                System.out.println("Generi associati:");
//				                brano.getGeneri().forEach(gen9 -> System.out.println(" - " + gen9.getDescrizione()));
//				            }
//				        } else {
//				            System.out.println("Nessun brano trovato con descrizioni dei generi superiori a 10 caratteri.");
//				        }
//				    } catch (Exception e) {
//				        System.out.println("Si è verificato un errore durante la ricerca dei brani: " + e.getMessage());
//				        e.printStackTrace();
//				    }
//				    break;

				case 10:
					System.out.println("Hai scelto il comando 10");
					System.out.println("Qual è la descrizione del genere che vuoi inserire? ");
					
					try {
						System.out.println("Inserisci la descrizione: ");
						String descrizioneG13 = scanner.nextLine();
						
						Genere nuovoGenere = new Genere(); 
						nuovoGenere.setDescrizione(descrizioneG13);
						
						GenereService genereService = MyServiceFactory.getGenereServiceInstance(); 
						genereService.insert(nuovoGenere);
						
						System.out.println("Nuovo genere inserito con successo!");
						
					} catch (Exception e) {
						System.out.println("Errore durante l'inserimento del genere: " + e.getMessage());
						e.printStackTrace();
					}
					break;
					
				case 11:
					System.out.println("Hai scelto il comando 11");
					System.out.println("Ecco la lista di tutti i generi: ");
					List<Genere> tuttiIGeneri;
					try {
						tuttiIGeneri = genereServiceInstance.list();
						if (tuttiIGeneri.isEmpty()) {
							System.out.println("Non ci sono generi al momento. ");
						} else {
							for (Genere genere : tuttiIGeneri) {
								System.out.println(genere.toString());
							}
						}
					} catch (Exception e) {
						System.out.println("Errore durante il caricamento della lista generi. ");
					}
					break;

				case 12:
					System.out.println("Hai scelto il comando 12");
					System.out.println("Di quale genere vuoi i dettagli? ");
					System.out.println("Inserisci l'id: ");
					try {
						Long idG11 = Long.parseLong(scanner.nextLine());
						scanner.nextLine();
						Genere genere = genereServiceInstance.get(idG11);
						if (genere != null) {
							System.out.println(genere);
						} else {
							System.out.println("L'id inserito non è valido. ");
							scanner.nextLine();
						}
					} catch (Exception e) {
						System.out.println("L'id inserito non è valido. ");
						scanner.nextLine();
					}
					break;

				case 13:
					System.out.println("Hai scelto il comando 13");
					System.out.println("Quale genere vuoi aggiornare? ");
					System.out.println("Inserisci l'id: ");
					try {
						Long idG12 = Long.parseLong(scanner.nextLine());
						Genere genere = genereServiceInstance.get(idG12);
						if (genere != null) {
							System.out.println("Genere corrente: " + genere);
						} else {
							System.out.println("L'id inserito non è valido. ");
						}
						System.out.println("Inserisci la nuova descrizione: ");
						String descrizioneG12 = scanner.nextLine();
						
						genere.setDescrizione(descrizioneG12);
						
						genereServiceInstance.update(genere);
						System.out.println("Genere aggiornato con successo! ");
						
					} catch (Exception e) {
						System.out.println("Errore: " + e.getMessage());
						e.printStackTrace();
					}
					break;

				case 14:
					System.out.println("Hai scelto il comando 14");
					System.out.println("Quale genere vuoi rimuovere? ");
					System.out.println("Inserisci l'id: ");
					try {
						Long idG14 = Long.parseLong(scanner.nextLine());
						branoServiceInstance.delete(idG14);

					} catch (Exception e) {
						System.out.println("L'id inserito non è valido. ");
						scanner.nextLine();
					}
					break;

//				case 15:
//					System.out.println("Hai scelto il comando 15");
//					System.out.println("Inserisci la descrizione del genere che vuoi cercare: ");
//					
//				    String descrizioneG15 = scanner.nextLine();
//				    try {
//				        Genere genere = genereServiceInstance.cercaPerDescrizione(descrizioneG15);
//				        if (genere != null) {
//				            System.out.println("Descrizione genere trovato: " + genere.getDescrizione());
//				        } else {
//				            System.out.println("Nessun genere trovato con la descrizione fornita.");
//				        }
//				    } catch (Exception e) {
//				        System.out.println("Si è verificato un errore durante la ricerca del genere: " + e.getMessage());
//				        e.printStackTrace();
//				    }
//				    break;
//
//
//				case 16:
//					System.out.println("Hai scelto il comando 16");
//					System.out.println("Inserisci prima data dell'intervallo: ");
//				    System.out.println("Inserisci la prima data dell'intervallo [yyyy-MM-dd]:");
//				    String dataInizioString = scanner.nextLine();
//				    System.out.println("Inserisci la seconda data dell'intervallo [yyyy-MM-dd]:");
//				    String dataFineString = scanner.nextLine();
//				    try {
//				        LocalDate dataInizio = LocalDate.parse(dataInizioString);
//				        LocalDate dataFine = LocalDate.parse(dataFineString);
//				        List<Genere> generi = genereServiceInstance.braniPubblicatiTra(dataInizio, dataFine);
//				        if (generi.isEmpty()) {
//				            System.out.println("Nessun genere ha brani pubblicati nell'intervallo specificato. ");
//				        } else {
//				            System.out.println("Generi con brani pubblicati nell'intervallo:");
//				            for (Genere genere : generi) {
//				                System.out.println( "Descrizione genere: " + genere.getDescrizione());
//				            }
//				        }
//				    } catch (DateTimeParseException e) {
//				        System.out.println("Formato della data non valido, usa il formato yyyy-MM-dd.");
//				    } catch (Exception e) {
//				        System.out.println("Si è verificato un errore: " + e.getMessage());
//				        e.printStackTrace();
//				    }
//				    break;

				case 17:
					System.out.println("Hai scelto il comando 17");
					exit = true;
					break;
				default:
					System.out.println("Scelta non valida. Inserisci un numero tra 1 e 17.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Errore: devi inserire un numero.");
			} catch (Exception e) {
				System.out.println("Si è verificato un errore: " + e.getMessage());
				e.printStackTrace();
			}

			if (!exit) {
				System.out.println("Vuoi eseguire un'altra operazione? (y/n)");
				String response = scanner.nextLine().trim().toLowerCase();
				if (!response.equals("y")) {
					exit = true;
				}
			}
		} while (!exit);

		System.out.println("Grazie per aver utilizzato il programma Brano-Genere. Arrivederci!");
		scanner.close();
	}
}