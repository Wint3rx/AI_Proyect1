package umg.principal.dao;

import umg.principal.db.DatabaseConnection;
import umg.principal.model.Cuestionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CuestionarioDao {
    public void insertUser(Cuestionario cuestionario) throws SQLException {
        String query = "INSERT INTO tb_respuestas(seccion,telegram_id,respuesta_texto,pregunta_id) VALUES (?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cuestionario.getSeccion());
            statement.setLong(2, cuestionario.getTelegramid());
            statement.setString(3, cuestionario.getResponse());
            statement.setInt(4, cuestionario.getPreguntaid());

            statement.executeUpdate();
        }
    }
}
