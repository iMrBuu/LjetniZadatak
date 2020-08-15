package friscic.ljetnizadatak;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDMetode {

	// Create metoda

	public static void unosKlijentaUBazu(String ime, String prezime, String oib, String kontaktbroj, String email) {

		try {
			PreparedStatement query = Baza.otvoriVezu().prepareStatement(
					"INSERT INTO klijent (ime,prezime,oib,kontaktbroj,email)" + " VALUES (?,?,?,?,?)");

			query.setString(1, ime);
			query.setString(2, prezime);
			query.setString(3, oib);
			query.setString(4, kontaktbroj);
			query.setString(5, email);

			query.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Read metoda

	public static void ispisKlijentaIzBaze() {
		int redniBroj = 1;

		try {

			PreparedStatement query = Baza.otvoriVezu().prepareStatement("SELECT * FROM klijent;");

			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				System.out.println((redniBroj++) + ". " + rs.getString("ime") + " " + rs.getString("prezime") + " OIB: "
						+ rs.getString("oib") + " Kontakt broj: " + rs.getString("kontaktbroj") + " Email: "
						+ rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Update metoda

	public static void promjenaKlijentaUBazi(int redniBroj, String ime, String prezime, String oib, String kontaktbroj,
			String email) {

		try {

			PreparedStatement query = Baza.otvoriVezu().prepareStatement(
					"UPDATE klijent SET ime=?, prezime=?, oib=?, kontaktbroj=?, email=? WHERE sifra = ?");

			query.setString(1, ime);
			query.setString(2, prezime);
			query.setString(3, oib);
			query.setString(4, kontaktbroj);
			query.setString(5, email);
			query.setInt(6, redniBroj);

			query.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Delete metoda

	public static void brisanjeKlijentaUBazi(int redniBroj) {

		try {

			PreparedStatement query = Baza.otvoriVezu().prepareStatement("DELETE * FROM klijent WHERE sifra=?");

			query.setInt(1, redniBroj);

			query.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
