package model;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public Utente doRetrieveByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username, email, password, nome, cognome, moto FROM cliente WHERE username =?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente utente = new Utente();
                utente.setUsername(rs.getString(1));
                utente.setEmail(rs.getString(2));
                utente.setPassword(rs.getString(3));
                utente.setNome(rs.getString(4));
                utente.setCognome(rs.getString(5));
                utente.setMoto(rs.getString(6));
                return utente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Utente utente) {
        try (Connection c = ConPool.getConnection()) {

            PreparedStatement ps = c.prepareStatement("insert into cliente(username, email, password, nome, cognome, moto) values(?, ?, ?, ?, ?, ?)");
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getEmail());
            ps.setString(3, utente.getPassword());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
            ps.setString(6, utente.getMoto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error");
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Utente> doRetrieveAll(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username, email, password, nome, cognome, moto FROM cliente LIMIT ?,?  ");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            List<Utente> utenti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utente u = new Utente();
                u.setUsername(rs.getString(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setCognome(rs.getString(5));
                u.setMoto(rs.getString(6));
                utenti.add(u);
            }
            return utenti;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Utente doRetrieveByUsernamePassword(String username, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT username, email, password, nome, cognome, moto FROM cliente WHERE username=? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente utente = new Utente();
                utente.setUsername(rs.getString(1));
                utente.setEmail(rs.getString(2));
                utente.setPassword(rs.getString(3));
                utente.setNome(rs.getString(4));
                utente.setCognome(rs.getString(5));
                utente.setMoto(rs.getString(6));
                return utente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDeleteByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM cliente WHERE email = ?;");
            ps.setString(1, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
