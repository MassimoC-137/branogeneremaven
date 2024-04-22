package it.prova.branogeneremaven.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.internal.StaticFilterAliasGenerator;

import it.prova.branogeneremaven.model.Brano;
import it.prova.branogeneremaven.model.Genere;
import it.prova.branogeneremaven.service.BranoService;
import it.prova.branogeneremaven.service.GenereService;
import it.prova.branogeneremaven.service.MyServiceFactory;

public interface TestInterface {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println(
					"\n *******Benvenuto nel programma Brano-Genere.******* \n Scegli una delle seguenti opzioni: ");
			System.out.println("1. Brano: Lista completa brani. ");
			System.out.println("2. Brano: Dammi un brano. ");
			System.out.println("3. Brano: Aggiorna un brano. ");
			System.out.println("4. Brano: Inserisci un brano. ");
			System.out.println("5. Brano: Cancella un brano ");
			System.out.println("6. Brano: Carica brano per genere. ");
			System.out.println("7. Brano: Rimuovi brano scollegando prima il genere. ");
			System.out.println("8. Brano: Descrizioni generi associati ad un brano. ");
			System.out.println("9. Brano: Brani con descrizioni da più di 10 caratteri. ");
			System.out.println("10. Genere: Lista completa generi. ");
			System.out.println("11. Genere: Dammi un genere. ");
			System.out.println("12. Genere: Aggiorna un genere. ");
			System.out.println("13. Genere: Inserisci un genere. ");
			System.out.println("14. Genere: Cancella un genere. ");
			System.out.println("15. Genere: Cerca per descrizione. ");
			System.out.println("16. Genere: Generi di brani pubblicati in un intervallo temporale. ");
			System.out.println("17. Esci dal programma. ");

			int choice;
			try {
				choice = scanner.nextInt();
			} catch (Exception e) {
				choice = 0;
			}

			scanner.useDelimiter("\n");
			scanner.nextLine();

			BranoService branoServiceInstance = MyServiceFactory.getBranoServiceInstance();
			GenereService genereServiceInstance = MyServiceFactory.getGenereServiceInstance();

			switch (choice) {

			case 1:
				System.out.println("Hai scelto il comando 1");
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
					System.out.println("Errore durante il caricamento della lista brani. ");
				}
				break;

			case 2:
				System.out.println("Hai scelto il comando 2");
				System.out.println("Di quale brano vuoi i dettagli? ");
				System.out.println("Inserisci l'id: ");
				Long idB2 = null;
				try {
					idB2 = scanner.nextLong();
					scanner.nextLine();
					Brano brano = branoServiceInstance.get(idB2);
					if (brano != null) {
						System.out.println(brano);
					} else {
						System.out.println("L'id inserito non è valido. ");
						scanner.nextLine();
						break;
					}
				} catch (Exception e) {
					System.out.println("L'id inserito non è valido. ");
					scanner.nextLine();
					break;
				}
				break;

			case 3:
				System.out.println("Hai scelto il comando 3");
				System.out.println("Quale brano vuoi aggiornare? ");
				System.out.println("Inserisci l'id: ");
				Long idB3 = scanner.nextLong();
				scanner.nextLine();
				try {
					Brano brano = branoServiceInstance.get(idB3);
					if (brano != null) {
						System.out.println("Brano corrente: " + brano);
					} else {
						System.out.println("L'id inserito non è valido. ");
						break;
					}

					System.out.println("Inserisci il nuovo titolo:");
					String titoloB3 = scanner.nextLine();

					System.out.println("Inserisci il nuovo autore:");
					String autoreB3 = scanner.nextLine();

					System.out.println("Inserisci la data di pubblicazione [dd.MM.yyyy]:");
					String strDataB3 = scanner.nextLine();
					LocalDate dataB3 = null;
					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
						dataB3 = LocalDate.parse(strDataB3, formatter);
					} catch (DateTimeParseException e) {
						System.out.println("La data inserita non è valida.");
						break;
					}

					brano.setTitolo(titoloB3);
					brano.setAutore(autoreB3);
					brano.setDataPubblicazione(dataB3);

					branoServiceInstance.update(brano);
					System.out.println("Brano aggiornato con successo!");

				} catch (Exception e) {
					System.out.println("Errore: " + e.getMessage());
					e.printStackTrace();
					break;
				}
				break;
			case 4:
				System.out.println("Hai scelto il comando 4");
				System.out.println("Qual è il titolo del brano che vuoi inserire? ");

				try {
					System.out.println("Inserisci il titolo del brano:");
					String titolo = scanner.nextLine();

					System.out.println("Inserisci l'autore del brano:");
					String autore = scanner.nextLine();

					System.out.println("Inserisci la data di pubblicazione del brano [dd.MM.yyyy]:");
					String dataPubblicazione = scanner.nextLine();
					LocalDate data = LocalDate.parse(dataPubblicazione, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

					Brano nuovoBrano = new Brano();
					nuovoBrano.setTitolo(titolo);
					nuovoBrano.setAutore(autore);
					nuovoBrano.setDataPubblicazione(data);

					BranoService branoService = MyServiceFactory.getBranoServiceInstance();
					branoService.insert(nuovoBrano);

					System.out.println("Nuovo brano inserito con successo!");

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

			case 6:
				System.out.println("Hai scelto il comando 6");
				System.out.println("Di quale genere vuoi caricare un brano? ");

			case 7:
				System.out.println("Hai scelto il comando 7");
				System.out.println("Quale brano vuoi rimuovere scollegando i generi? ");

			case 8:
				System.out.println("Hai scelto il comando 8");
				System.out.println("Di quale brano vuoi la lista dei generi? ");

			case 9:
				System.out.println("Hai scelto il comando 9");
				System.out.println("Ecco la lista dei brani con una descrizione più lunga di 10 caratteri: ");

			case 10:
				System.out.println("Hai scelto il comando 10");
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

			case 11:
				System.out.println("Hai scelto il comando 11");
				System.out.println("Di quale genere vuoi i dettagli? ");

			case 12:
				System.out.println("Hai scelto il comando 12");
				System.out.println("Quale genere vuoi aggiornare? ");

			case 13:
				System.out.println("Hai scelto il comando 13");
				System.out.println("Qual è la descrizione del genere che vuoi inserire? ");

			case 14:
				System.out.println("Hai scelto il comando 14");
				System.out.println("Quale genere vuoi rimuovere? ");

			case 15:
				System.out.println("Hai scelto il comando 15");
				System.out.println("Inserisci la descrizione del genere che vuoi cercare: ");

			case 16:
				System.out.println("Hai scelto il comando 16");
				System.out.println("Inserisci ");

			case 17:
				System.out.println("Hai scelto il comando 17");
				System.out.println("Inserisci la prima data dell'intervallo: ");

			default:
				System.out.println("Comando inserito non trovato o non valido. ");
			}
		}
		System.out.println("Grazie per aver utilizzato il programma Brano-Genere. ");
		scanner.close();
	}

}
