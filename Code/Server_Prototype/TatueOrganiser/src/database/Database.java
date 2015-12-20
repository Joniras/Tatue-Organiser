package database;

import java.sql.*;
import java.util.Vector;

import model.*;

public class Database {
	private Connection con;
	private static String username = "d5bhifs01";
	private static String password = "d5bhifs01";
	private static String connectionString = "jdbc:oracle:thin:@192.168.128.151:1521:ora11g";
	
	public Database() throws SQLException	{
		con = createConnection();
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
	
	//#####ABTEILUNG
	public Vector<Abteilung> getAllAbteilungen() throws SQLException	{
		Vector<Abteilung> abteilungen = new Vector<Abteilung>();
		ResultSet rs = con.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY).executeQuery ("SELECT AB_ID, ABNAME, ABETAGE FROM ABTEILUNG");
		Abteilung newAbteilung;
		
		while (rs.next())	{
			newAbteilung = new Abteilung (rs.getInt("AB_ID"), rs.getString ("ABNAME"), rs.getInt ("ABETAGE"));
			
			abteilungen.add (newAbteilung);
		}
		
		return abteilungen;
	}
	
	public void addAbteilung(Abteilung a) throws SQLException {
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO ABTEILUNG VALUES (seq_abteilung_id.nextval, ?, ?)");
		
		insertion.setString(1, a.getAb_name());
		insertion.setInt(2, a.getAb_etage());
		
		insertion.executeQuery();
	}
	
	public void deleteAbteilung(int a_id) throws SQLException{
		PreparedStatement del = con.prepareStatement ("DELETE FROM ABTEILUNG WHERE AB_ID = ?");
		del.setInt(1, a_id);
		del.executeQuery();
	}
	
	public void updateAbteilung(Abteilung a) throws SQLException	{
		PreparedStatement update = con.prepareStatement ("UPDATE ABTEILUNG SET ABNAME = ?, ABETAGE = ? WHERE AB_ID = ?");
	
		update.setString (1, a.getAb_name());
		update.setInt (2, a.getAb_etage());
		update.setInt (3, a.getAb_id());
		
		update.executeQuery();
	}
	//#####ABTEILUNG
	//#####SCHUELER
	public Vector<Schueler> getAllSchueler() throws SQLException	{
		Vector<Schueler> schueler = new Vector<Schueler>();
		ResultSet rs = con.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY).executeQuery ("SELECT S_ID, VORNAME, NACHNAME, KLASSE, ISGUIDE FROM SCHUELER");
		Schueler newSchueler;
		
		while (rs.next())	{
			newSchueler = new Schueler ();
			newSchueler.setS_id(rs.getInt("S_ID"));
			newSchueler.setVorname(rs.getString ("VORNAME"));
			newSchueler.setNachname(rs.getString ("NACHNAME"));
			newSchueler.setKlasse(rs.getString ("KLASSE"));
			newSchueler.setGuide(rs.getInt("ISGUIDE")==0 ? false : true);
			schueler.add (newSchueler);
		}
		
		return schueler;
	}
	
	public void addSchueler (Schueler s) throws SQLException {
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO SCHUELER VALUES (seq_schueler_id.nextval, ?, ?, ?, ?, null)");
		
		insertion.setString(1, s.getVorname());
		insertion.setString(2, s.getNachname());
		insertion.setString(3, s.getKlasse());
		insertion.setInt (4, s.isGuide() ? 1 : 0);
		
		insertion.executeQuery();
	}
	
	public void deleteSchueler(int s_id) throws SQLException{
		PreparedStatement del = con.prepareStatement ("DELETE FROM SCHUELER WHERE S_ID = ?");
		del.setInt(1, s_id);
		del.executeQuery();
	}
	
	public void updateSchueler(Schueler s) throws SQLException	{
		PreparedStatement update = con.prepareStatement ("UPDATE SCHUELER SET VORNAME = ?, NACHNAME = ?, KLASSE = ?, ISGUIDE = ? WHERE S_ID = ?");
	
		update.setString (1, s.getVorname());
		update.setString (2, s.getNachname());
		update.setString (3, s.getKlasse());
		update.setInt (4, s.isGuide() ? 1 : 0);
		update.setInt (5, s.getS_id());
		
		update.executeQuery();
	}
	//#####SCHUELER
	//#####STAENDE
	public Vector<Schueler> getSchuelerVonStand(int ST_ID) throws SQLException{
		Vector<Schueler> schueler = new Vector<Schueler>();
		ResultSet rs = con.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY).executeQuery ("SELECT S_ID, VORNAME, NACHNAME, KLASSE, ISGUIDE FROM SCHUELER JOIN STAND ON SCHUELER.ST_ID = STAND.ST_ID");
		Schueler newSchueler;
		
		while (rs.next())	{
			newSchueler = new Schueler ();
			newSchueler.setS_id(rs.getInt("S_ID"));
			newSchueler.setVorname(rs.getString ("VORNAME"));
			newSchueler.setNachname(rs.getString ("NACHNAME"));
			newSchueler.setKlasse(rs.getString ("KLASSE"));
			newSchueler.setGuide(rs.getInt("ISGUIDE")==0 ? false : true);
			schueler.add (newSchueler);
		}
		
		return schueler;
	}
	public Vector<Stand> getAllStaende() throws SQLException	{
		Vector<Stand> staende = new Vector<Stand>();
		ResultSet rs = con.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY).executeQuery ("SELECT ST_ID, STNAME, INFO FROM STAND");
		Stand newStand;
		
		while (rs.next())	{
			newStand = new Stand ();
			newStand.setSt_id(rs.getInt("ST_ID"));
			newStand.setStName(rs.getString ("STNAME"));
			newStand.setInfo(rs.getString ("INFO"));
			newStand.setStandSchueler(getSchuelerVonStand(rs.getInt("ST_ID")));
			staende.add (newStand);
		}
		
		return staende;
	}
	public Vector<Stand> getStaendeVonAbteilungen(int a_id) throws SQLException	{
		Vector<Stand> staende = new Vector<Stand>();
		PreparedStatement statement = con.prepareStatement ("SELECT ST_ID, STNAME, INFO FROM STAND WHERE AB_ID = ?");
		statement.setInt(1, a_id);
		ResultSet rs = statement.executeQuery();
		Stand newStand;
		
		while (rs.next())	{
			newStand = new Stand ();
			newStand.setSt_id(rs.getInt("ST_ID"));
			newStand.setStName(rs.getString ("STNAME"));
			newStand.setInfo(rs.getString ("INFO"));
			newStand.setStandSchueler(getSchuelerVonStand(rs.getInt("ST_ID")));
			staende.add (newStand);
		}
		
		return staende;
	}
	
	public void addStandZuAbteilung(int a_id, Stand s) throws SQLException {
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO STAND VALUES (seq_stand_id.nextval, ?, ?, ?)");
		
		insertion.setString(1, s.getStName());
		insertion.setString(2, s.getInfo());
		insertion.setInt(3, a_id);
		
		insertion.executeQuery();
	}
	
	public void addSchuelerZuStand(int st_id, Schueler s) throws SQLException {
		PreparedStatement insertion = con.prepareStatement ("UPDATE SCHUELER SET ST_ID = ? WHERE S_ID = ?");
		
		insertion.setInt(1, st_id);
		insertion.setInt(2, s.getS_id());
		
		insertion.executeQuery();
	}
	public Vector<StandRating> getAllRatingsVonStand(int id) throws SQLException{
		Vector<StandRating> fields = new Vector<StandRating>();
		PreparedStatement prep = con.prepareStatement ("SELECT SR_ID, FREUNDLICHKEIT, KOMPETENZ FROM GUIDERATINGS WHERE ST_ID = ?");
		
		prep.setInt(1, id);
		
		ResultSet rs = prep.executeQuery();
		
		StandRating newField;
		
		while (rs.next())	{
			newField = new StandRating();
			newField.setFreundlichkeit(rs.getFloat("FREUNDLICHKEIT"));
			newField.setKompetenz(rs.getFloat("KOMPETENZ"));
			fields.add (newField);
		}
		
		return fields;
	}

	public void addRatingZuStand(int id, StandRating gr) throws SQLException{
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO STANDRATING VALUES (seq_standrating_id.nextval, ?, ?, ?");
		
		insertion.setFloat(1, gr.getFreundlichkeit());
		insertion.setFloat(2, gr.getKompetenz());
		insertion.setInt(3, id);
		
		insertion.executeQuery();
	}
	//#####STAENDE
	//#####GUIDE
	public Vector<Schueler> getAllGuides() throws SQLException	{
		Vector<Schueler> guides = new Vector<Schueler>();
		ResultSet rs = con.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY).executeQuery ("SELECT S_ID, VORNAME, NACHNAME, KLASSE FROM SCHUELER WHERE ISGUIDE = 1");
		Schueler newGuide;
		
		while (rs.next())	{
			newGuide = new Schueler ();
			newGuide.setS_id(rs.getInt("S_ID"));
			newGuide.setVorname(rs.getString ("VORNAME"));
			newGuide.setNachname(rs.getString ("NACHNAME"));
			newGuide.setKlasse(rs.getString ("KLASSE"));
			newGuide.setGuide(true);
			guides.add (newGuide);
		}
		
		return guides;
	}
	
	public void addGuide(int s_id) throws SQLException {
		PreparedStatement insertion = con.prepareStatement ("UPDATE SCHUELER SET ISGUIDE = ? WHERE S_ID = ?");
		
		insertion.setInt(1, 1);
		insertion.setInt(2, s_id);
		
		insertion.executeQuery();
	}
	
	public void deleteGuide(int s_id) throws SQLException{
		PreparedStatement statement = con.prepareStatement ("UPDATE SCHUELER SET ISGUIDE = ? WHERE S_ID = ?");
		
		statement.setInt(1, 0);
		statement.setInt(2, s_id);
		
		statement.executeQuery();
		
		PreparedStatement delStatement = con.prepareStatement ("DELETE FROM GUIDERATINGS WHERE S_ID = ?");

		delStatement.setInt(1, s_id);
		
		delStatement.executeQuery();
	}
	
	public Vector<GuideRating> getAllRatingsVonGuide(int id) throws SQLException{
		Vector<GuideRating> fields = new Vector<GuideRating>();
		PreparedStatement prep = con.prepareStatement ("SELECT GR_ID, FREUNDLICHKEIT, KOMPETENZ FROM GUIDERATINGS WHERE S_ID = ?");
		
		prep.setInt(1, id);
		
		ResultSet rs = prep.executeQuery();
		
		GuideRating newField;
		
		while (rs.next())	{
			newField = new GuideRating();
			newField.setFreundlichkeit(rs.getFloat("FREUNDLICHKEIT"));
			newField.setKompetenz(rs.getFloat("KOMPETENZ"));
			fields.add (newField);
		}
		
		return fields;
	}

	public void addRatingZuGuide(int id, GuideRating gr) throws SQLException{
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO GUIDERATING VALUES (seq_guiderating_id.nextval, ?, ?, ?");
		
		insertion.setFloat(1, gr.getFreundlichkeit());
		insertion.setFloat(2, gr.getKompetenz());
		insertion.setInt(3, id);
		
		insertion.executeQuery();
	}
	//#####GUIDE
	//#####QUIZ
	public void addQuizZuAbteilung(int a_id, Quiz q) throws SQLException{
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO QUIZ VALUES (seq_quiz_id.nextval, ?, ?)");
		
		insertion.setString(1, q.getTitel());
		insertion.setInt(2, a_id);
		
		insertion.executeQuery();
	}
	
	public Vector<Quiz> getQuizzesVonAbteilung(int ab_id) throws SQLException{
		
		//QUIZ
		Vector<Quiz> quizzes = new Vector<Quiz>();
		
		PreparedStatement prep_quiz = con.prepareStatement ("SELECT Q_ID, TITEL FROM QUIZ WHERE AB_ID = ?");
		prep_quiz.setInt(1, ab_id);
		ResultSet rs_quiz = prep_quiz.executeQuery();
		
		while(rs_quiz.next()){
			Quiz q = new Quiz();
			q.setQ_id(rs_quiz.getInt("Q_ID"));
			q.setTitel(rs_quiz.getString("TITEL"));
			
			//FRAGEN
			Vector<Frage> fragen = new Vector<Frage>();
			PreparedStatement prep_fragen = con.prepareStatement ("SELECT F_ID, TEXT FROM FRAGE WHERE Q_ID = ?");
			prep_fragen.setInt(1, q.getQ_id());
			
			ResultSet rs_fragen = prep_fragen.executeQuery();
			
			while (rs_fragen.next())	{
				Frage newFrage = new Frage();
				newFrage.setF_id(rs_fragen.getInt("F_ID"));
				newFrage.setText(rs_fragen.getString("TEXT"));
				
				//ANTWORT
				Vector<Antwort> antworten = new Vector<Antwort> ();
				PreparedStatement prep_antwort = con.prepareStatement ("SELECT A_ID, TEXT, IS_RIGHT FROM ANTWORT WHERE F_ID = ?");
				prep_antwort.setInt(1, newFrage.getF_id());
				
				ResultSet rs_antwort = prep_fragen.executeQuery();
				
				while(rs_antwort.next()){
					Antwort newAntwort = new Antwort();
					
					newAntwort.setA_id(rs_antwort.getInt("A_ID"));
					newAntwort.setText(rs_antwort.getString("TEXT"));
					newAntwort.setRight(rs_antwort.getInt("IS_RIGHT") == 0 ? false : true);
					
					antworten.add(newAntwort);
				}
				fragen.add (newFrage);
			}
			q.setFragen(fragen);
			quizzes.add(q);
		}
		return quizzes;
	}
	
	public void deleteQuiz(int q_id) throws SQLException{
		PreparedStatement del = con.prepareStatement ("DELETE FROM QUIZ WHERE Q_ID = ?");
		del.setInt(1, q_id);
		del.executeQuery();
	}
	
	public void updateQuiz(Quiz q) throws SQLException	{
		PreparedStatement update = con.prepareStatement ("UPDATE QUIZ SET TITLE = ? WHERE Q_ID = ?");
	
		
		update.setString (1, q.getTitel());
		update.setInt (2, q.getQ_id());
		
		update.executeQuery();
	}
	
	public Quiz getQuiz(int q_id) throws SQLException{
		
		//QUIZ		
		PreparedStatement prep_quiz = con.prepareStatement ("SELECT Q_ID, TITEL FROM QUIZ WHERE Q_ID = ?");
		prep_quiz.setInt(1, q_id);
		ResultSet rs_quiz = prep_quiz.executeQuery();
		
		
		Quiz q = new Quiz();
		q.setQ_id(rs_quiz.getInt("Q_ID"));
		q.setTitel(rs_quiz.getString("TITEL"));
		
		//FRAGEN
		Vector<Frage> fragen = new Vector<Frage>();
		PreparedStatement prep_fragen = con.prepareStatement ("SELECT F_ID, TEXT FROM FRAGE WHERE Q_ID = ?");
		prep_fragen.setInt(1, q.getQ_id());
		
		ResultSet rs_fragen = prep_fragen.executeQuery();
		
		while (rs_fragen.next())	{
			Frage newFrage = new Frage();
			newFrage.setF_id(rs_fragen.getInt("F_ID"));
			newFrage.setText(rs_fragen.getString("TEXT"));
			
			//ANTWORT
			Vector<Antwort> antworten = new Vector<Antwort> ();
			PreparedStatement prep_antwort = con.prepareStatement ("SELECT A_ID, TEXT, IS_RIGHT FROM ANTWORT WHERE F_ID = ?");
			prep_antwort.setInt(1, newFrage.getF_id());
			
			ResultSet rs_antwort = prep_fragen.executeQuery();
			
			while(rs_antwort.next()){
				Antwort newAntwort = new Antwort();
				
				newAntwort.setA_id(rs_antwort.getInt("A_ID"));
				newAntwort.setText(rs_antwort.getString("TEXT"));
				newAntwort.setRight(rs_antwort.getInt("IS_RIGHT") == 0 ? false : true);
				
				antworten.add(newAntwort);
			}
			fragen.add (newFrage);
		}
		q.setFragen(fragen);
		
		return q;
	}
	
	public Vector<GewinnspielDaten> getGewinnspielDatenVonQuiz(int q_id) throws SQLException{
		Vector<GewinnspielDaten> fields = new Vector<GewinnspielDaten>();
		PreparedStatement prep = con.prepareStatement ("SELECT GD_ID, SCORE, VORNAME, NACHNAME, EMAIL, TELEFON FROM GEWINNSPIELDATEN WHERE GD_ID = ?");
		
		prep.setInt(1, q_id);
		
		ResultSet rs = prep.executeQuery();
		
		while (rs.next())	{
			GewinnspielDaten newField = new GewinnspielDaten();
			newField.setGd_id(rs.getInt("GD_ID"));
			newField.setScore(rs.getFloat("SCORE"));
			newField.setVorname(rs.getString("VORNAME"));
			newField.setNachname(rs.getString("NACHNAME"));
			newField.setEmail(rs.getString("EMAIL"));
			newField.setTelefon(rs.getString("TELEFON"));
			
			fields.add (newField);
		}
		
		return fields;
	}
	
	public void addGewinnspielDatenZuQuiz(int id, GewinnspielDaten gd) throws SQLException{
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO GEWINNSPIELDATEN VALUES (seq_gewinnspieldaten_id.nextval, ?, ?, ?, ?, ?, ?");
		
		insertion.setInt(1, id);
		insertion.setFloat(2, gd.getScore());
		insertion.setString(3, gd.getVorname());
		insertion.setString(4, gd.getNachname());
		insertion.setString(5, gd.getEmail());
		insertion.setString(6, gd.getTelefon());
		
		insertion.executeQuery();
	}
	
	//#####QUIZ
	//#####FRAGE
	public void addFrageZuQuiz(int q_id, Frage f) throws SQLException{
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO FRAGE VALUES (seq_frage_id.nextval, ?, ?)");
		
		insertion.setString(1, f.getText());
		insertion.setInt(2, q_id);
		
		insertion.executeQuery();
	}
	
	public void deleteFrage(int f_id) throws SQLException{
		PreparedStatement del = con.prepareStatement ("DELETE FROM FRAGE WHERE F_ID = ?");
		del.setInt(1, f_id);
		del.executeQuery();
	}
	
	public void updateFrage(Frage q) throws SQLException	{
		PreparedStatement update = con.prepareStatement ("UPDATE FRAGE SET TEXT = ? WHERE F_ID = ?");
	
		update.setString (1, q.getText());
		update.setInt (2, q.getF_id());
		
		update.executeQuery();
	}
	//#####FRAGE
	//#####ANTWORT
	public void addAntwortZuFrage(int f_id, Antwort a) throws SQLException{
		PreparedStatement insertion = con.prepareStatement ("INSERT INTO ANTWORT VALUES (seq_antwort_id.nextval, ?, ?, ?)");
		
		insertion.setString(1, a.getText());
		insertion.setInt(1, a.isRight() ? 1 : 0);
		insertion.setInt(2, f_id);
		
		insertion.executeQuery();
	}
	
	public void deleteAntwort(int a_id) throws SQLException{
		PreparedStatement del = con.prepareStatement ("DELETE FROM ANTWORT WHERE A_ID = ?");
		del.setInt(1, a_id);
		del.executeQuery();
	}
	
	public void updateAntwort(Antwort a) throws SQLException	{
		PreparedStatement update = con.prepareStatement ("UPDATE ANTWORT SET TEXT = ? WHERE A_ID = ?");
	
		update.setString (1, a.getText());
		update.setInt (1, a.isRight() ? 1 : 0);
		update.setInt (2, a.getA_id());
		
		update.executeQuery();
	}
	//#####ANTWORT
}
