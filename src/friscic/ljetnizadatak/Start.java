package friscic.ljetnizadatak;

public class Start {

	public Start() {

		while (true) {

			izbornik();

			switch (Pomocno.akcija()) {
			case 1:
				Pomocno.unosKlijenta();
				break;
			case 2:
				CRUDMetode.ispisKlijentaIzBaze();
				break;
			case 3:
				Pomocno.promjenaKlijenta();
				break;
			case 4:
				Pomocno.brisanjeKlijenta();
				break;
			case 5:
				idiNaEraDijagram();
				break;
			case 6:
				idiNaKodAplikacije();
				break;
			case 7:
				Baza.zatvoriVezu();
				System.out.println("Lijep pozdrav!");
				return;

			}
		}

	}

	// Metoda koja otvara URL gdje se nalazi kod aplikacije
	private void idiNaKodAplikacije() {

		Pomocno.goToAddress("https://github.com/iMrBuu/LjetniZadatak");

	}

	// Metoda koja otvara URL gdje se nalazi slika ERA dijagrama
	private void idiNaEraDijagram() {

		Pomocno.goToAddress("https://github.com/iMrBuu/hellojp22/blob/master/era.jpg");

	}

	// Metoda koja ispisuje izbornik
	private void izbornik() {

		System.out.println("******************************");
		System.out.println(" 1 - Unos novog klijenta");
		System.out.println(" 2 - Izlistaj sve klijente");
		System.out.println(" 3 - Promjena podataka klijenta");
		System.out.println(" 4 - Brisanje klijenta");
		System.out.println(" 5 - Idi na ERA dijagram baze");
		System.out.println(" 6 - Idi na kod aplikacije");
		System.out.println(" 7 - Izlaz iz aplikacije");
		System.out.println("******************************");

	}

	public static void main(String[] args) {
		new Start();
	}
}
