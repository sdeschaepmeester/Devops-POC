package medhead;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        //String url = System.getenv("DATABASE_URL"); // Assurez-vous que DATABASE_URL est configuré dans votre environnement
        String url = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres";
		String user = "postgres.ueqjqptqgpomxlznebun";
        String password = "viorne99!-drew";

        try {
			System.out.println("salut !");
            Class.forName("org.postgresql.Driver");
            
            Connection conn = DriverManager.getConnection(url, user, password);
            
            Statement stmt = conn.createStatement();
            
            String query = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(query);
            
            // Afficher les résultats en console
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String createdAt = rs.getString("created_at");
                
                System.out.println("ID: " + id + ", Username: " + username + ", Created At: " + createdAt);
            }
            
            // Fermer les ressources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}