package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Vector;

import model.*;

public class Database {
	private Connection con;
	private static String username = "d5bhifs01";
	private static String password = "d5bhifs01";
	private static String connectionString = "jdbc:oracle:thin:212.152.179.117:1521:ora11g";
	
	public Database() throws SQLException	{
		//con = createConnection();
	}
	
	public Database (String _connectionString, String _username, String _password) throws SQLException	{
		username = _username;
		password = _password;
		connectionString = _connectionString;
		
		con = createConnection();
	}
	
	private Connection createConnection() throws SQLException	{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		return DriverManager.getConnection (connectionString, username, password);
	}
	
	public Vector<Abteilung> getAllAbteilungen() throws SQLException	{
		Vector<Abteilung> abteilungen = new Vector<Abteilung>();
		/*ResultSet rs = con.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY).executeQuery ("SELECT AB_ID, NAME, ABETAGE FROM ABTEILUNG");
		Abteilung newAbteilung;
		
		while (rs.next())	{
			newAbteilung = new Abteilung (rs.getInt("AB_ID"), rs.getString ("NAME"), rs.getInt ("ABETAGE"));
			
			abteilungen.add (newAbteilung);
		}*/
		abteilungen.add(new Abteilung(1, "Informatik", 2));
		abteilungen.add(new Abteilung(2, "Tiefbau", 2));
		abteilungen.add(new Abteilung(3, "Hochbau", 1));
		abteilungen.add(new Abteilung(4, "Netzwerktechnik", 1));
		
		return abteilungen;
	}
	
	public Vector<Schueler> getAllSchueler() throws SQLException	{
		Vector<Schueler> schueler = new Vector<Schueler>();
		/*ResultSet rs = con.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY).executeQuery ("SELECT S_ID, VORNAME, VORNAME, KLASSE FROM SCHUELER");
		Schueler newSchueler;
		
		while (rs.next())	{
			newSchueler = new Schueler ();
			newSchueler.setS_id(rs.getInt("s_ID"));
			newSchueler.setVorname(rs.getString ("VORNAME"));
			newSchueler.setNachname(rs.getString ("NACHNAME"));
			newSchueler.setKlasse(rs.getString ("KLASSE"));
			schueler.add (newSchueler);
		}*/
		
		Schueler newSchueler = new Schueler();
		newSchueler.setS_id(1);
		newSchueler.setVorname("Henrik");
		newSchueler.setNachname("Csöre");
		newSchueler.setKlasse("5BHIFS");
		
		schueler.add(newSchueler);
		
		newSchueler = new Schueler();
		newSchueler.setS_id(2);
		newSchueler.setVorname("Stefan");
		newSchueler.setNachname("Egger");
		newSchueler.setKlasse("5BHIFS");
		
		schueler.add(newSchueler);
		
		newSchueler = new Schueler();
		newSchueler.setS_id(3);
		newSchueler.setVorname("Jonas");
		newSchueler.setNachname("Schaltegger");
		newSchueler.setKlasse("5BHIFS");
		
		schueler.add(newSchueler);
		
		return schueler;
	}
	
	
}
