import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private static DbManager instance;
    private static Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/db_servlet1";
    private final String user = "root";
    private final String password = "";

    private DbManager() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.connection = DriverManager.getConnection(url, user, password);
    }

    public static DbManager getInstance() throws SQLException {
        if (instance == null)
            instance = new DbManager();
        return instance;
    }

    private boolean checkConnection(){
        try{
            if(connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione");
                return false;
            }
        } catch(SQLException e){
            System.err.println("Errore di connessione");
            return false;
        }
        return true;
    }

    public List<Pokemon> selectAll(){
        List<Pokemon> pokemons = new ArrayList<>();
        String query = "SELECT * FROM pokemon";

        if(!checkConnection())
            return null;

        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Pokemon pokemon = new Pokemon();
                pokemon.id = rs.getInt("id");
                pokemon.nome = rs.getString("nome");
                pokemon.tipo = rs.getString("tipo");
                pokemon.livello = rs.getInt("livello");

                pokemons.add(pokemon);
            }
        }catch(SQLException e){
            System.err.println("Errore query: " + e.getMessage());
            return null;
        }

        return pokemons;
    }

    public Pokemon selectById(Integer id){
        String query = "SELECT * FROM pokemon WHERE id = ?";

        if(!checkConnection())
            return null;

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                Pokemon pokemon = new Pokemon();
                pokemon.id = rs.getInt("id");
                pokemon.nome = rs.getString("nome");
                pokemon.tipo = rs.getString("tipo");
                pokemon.livello = rs.getInt("livello");

                return pokemon;
            }
        }catch(SQLException e){
            System.err.println("Errore query: " + e.getMessage());
        }

        return null;
    }

    public Pokemon selectByName(String name){
        String query = "SELECT * FROM pokemon WHERE nome = ?";

        if(!checkConnection())
            return null;

        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                Pokemon pokemon = new Pokemon();
                pokemon.id = rs.getInt("id");
                pokemon.nome = rs.getString("nome");
                pokemon.tipo = rs.getString("tipo");
                pokemon.livello = rs.getInt("livello");

                return pokemon;
            }
        }catch(SQLException e){
            System.err.println("Errore query: " + e.getMessage());
        }

        return null;
    }
}