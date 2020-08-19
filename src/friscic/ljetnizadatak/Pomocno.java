package friscic.ljetnizadatak;

import java.awt.Desktop;
import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Pomocno {

	private static Scanner scanner = new Scanner(System.in);

	public static int akcija() {

		int brojAkcije;

		while (true) {

			try {

				System.out.print("Odaberite akciju: ");
				brojAkcije = scanner.nextInt();
				scanner.nextLine();

				if (brojAkcije <= 7 && brojAkcije >= 1) {
					return brojAkcije;
				}

			} catch (Exception e) {

				System.out.println("---Krivi unos---");
				scanner.nextLine();
			}
		}
	}
	


	// Metoda za kreiranje klijenta

	public static void unosKlijenta() {
		String ime, prezime, oib, kontaktbroj, email;

		ime = unosImePrezime("Unesite ime klijenta: ");
		prezime = unosImePrezime("Unesite prezime klijenta: ");
		oib = unosOIB("Unesite OIB klijenta ");
		kontaktbroj = unosKontaktBroj("Unesite kontakt broj klijenta: ");
		email = unosEmail("Unesite email klijenta: ");

		CRUDMetode.unosKlijentaUBazu(ime, prezime, oib, kontaktbroj, email);

		System.out.println("---Klijent je usješno kreiran---");
	}

	// Metoda za promjenu podataka klijenta

	public static void promjenaKlijenta() {

		CRUDMetode.ispisKlijentaIzBaze();

		int sifra = unosRedniBroj("Unesite redni broj klijenta za promjenu: ");

		if (sifra == 0) {
			return;

		}

		String ime, prezime, oib, kontaktbroj, email;

		ime = unosImePrezime("Unesite ime klijenta za promjenu: ");
		prezime = unosImePrezime("Unesite prezime klijenta za promjenu: ");
		oib = unosOIB("Unesite OIB klijenta za promjenu: ");
		kontaktbroj = unosKontaktBroj("Unesite kontakt broj klijenta za promjenu: ");
		email = unosEmail("Unesite email klijenta za promjenu: ");

		CRUDMetode.promjenaKlijentaUBazi(sifra, ime, prezime, oib, kontaktbroj, email);

		System.out.println("---Promjena klijenta je izvršena---");

	}

	// Metoda za brisanje klijenta

	public static void brisanjeKlijenta() {
		CRUDMetode.ispisKlijentaIzBaze();

		int sifra = unosRedniBroj("Unesite redni broj klijenta za brisanje: ");

		if (sifra == 0) {
			return;
		}

		CRUDMetode.brisanjeKlijentaUBazi(sifra);

		System.out.println("---Klijent je uspješno obrisan---");

	}

	private static int unosRedniBroj(String poruka) {

		int redniBroj;
		Integer sifra;
		String akcija = "";

		search: while (true) {

			try {

				System.out.print(poruka);
				redniBroj = scanner.nextInt();
				scanner.nextLine();

				sifra = findSifru(redniBroj);

				if (sifra.equals(0)) {

					while (true) {
						System.out.println("Želite li nastaviti željenu akciju? Da ili Ne?");
						akcija = scanner.nextLine();

						if (akcija.toLowerCase().equals("ne")) {
							return 0;
						} else if (akcija.toLowerCase().equals("da")) {
							continue search;
						}

					}

				}

				return sifra;

			} catch (Exception e) {
				System.out.println("---Krivi unos---");
				scanner.nextLine();
			}
		}

	}

	private static Integer findSifru(Integer sifra) {
		try {

			PreparedStatement query = Baza.otvoriVezu().prepareStatement("SELECT sifra FROM klijent");

			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				if (sifra.equals(rs.getRow())) {
					return rs.getInt("sifra");

				}
			}
			System.out.println("---Klijent pod rednim brojem " + sifra + " ne postoji---");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 0;

	}

	// Metoda za unos emaila i njegovu kontrolu
	private static String unosEmail(String poruka) {
		String email;

		while (true) {

			System.out.print(poruka);
			email = scanner.nextLine();

			if (email.isEmpty()) {
				System.out.println("---Potrebno je unijeti email adresu---");
				continue;
			}

			if (!email.isEmpty() && email.length() <= 50) {

				return email;

			}
		}
	}

	// Metoda za unos kontak broja i njegovu kontrolu
	private static String unosKontaktBroj(String poruka) {

		String kontaktbroj;

		while (true) {

			System.out.print(poruka);
			kontaktbroj = scanner.nextLine();

			if (kontaktbroj.isEmpty() || !kontaktbroj.matches("[0-9]+")) {
				System.out.println("---Nepostojeći kontakt broj---");
				continue;
			}

			if (!kontaktbroj.isEmpty()) {

				return kontaktbroj;
			}
		}

	}

	// Metoda za unos OIBa i njegovu kontrolu
	private static String unosOIB(String poruka) {

		String oib;

		while (true) {

			System.out.print(poruka);
			oib = scanner.nextLine();

			if (oib.trim().isEmpty() || !oib.matches("[0-9]+")) {
				System.out.println("---Nepostojeći OIB---");
				continue;

			} else if (oib.length() != 11) {
				System.out.println("OIB mora sadržavati 11 brojeva!");
				continue;
			}
			return oib;
		}

	}

	// Metoda za unos imena i prezimena pod uvjetom da polje nije prazno i sadrži
	// barem jedno slovo

	private static String unosImePrezime(String poruka) {

		String s;

		while (true) {

			System.out.println(poruka);
			s = scanner.nextLine();

			if (!s.isEmpty() && s.matches(".*[a-zA-Z]+.*")) {

				return s;

			}

		}

	}

	public static void goToAddress(String url) {

		try {
			Desktop desktop = java.awt.Desktop.getDesktop();
			URI goTo = new URI(url);
			desktop.browse(goTo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
