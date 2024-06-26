package model;

import javax.persistence.OrderBy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {
    public enum OrderBy{
        DEFAULT(""), PREZZO_ASC("ORDER BY prezzo ASC"), PREZZO_DESC("ORDER BY prezzo DESC");
        public final String sql;

        private OrderBy(String sql){
            this.sql=sql;
        }
    }
    public List<Prodotto>doRetrieveAll(int offset,int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto.id, prodotto.nome, prodotto.descrizione, prodotto.marca, prodotto.prezzo, prodotto.foto_copertina FROM prodotto limit ?,?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();

            ArrayList<Prodotto> prodotti = new ArrayList<>();
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setMarca((rs.getString(4)));
                p.setPrezzo(rs.getFloat(5));
                p.setFotoCopertina((rs.getString(6)));

                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Prodotto doRetrieveById(int id){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT prodotto.id, prodotto.nome, prodotto.descrizione, prodotto.marca, prodotto.prezzo, prodotto.foto_copertina FROM prodotto WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            Prodotto p=new Prodotto();
            if(rs.next()){
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setMarca((rs.getString(4)));
                p.setPrezzo(rs.getFloat(5));
                p.setFotoCopertina(rs.getString(6));
            }
            ps=con.prepareStatement("SELECT foto_prodotto.link1, foto_prodotto.link2, foto_prodotto.link3, foto_prodotto.link4 FROM foto_prodotto WHERE id_prodotto=?");
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if(rs.next()) {
                p.getListaFoto().add(rs.getString(1));
                p.getListaFoto().add(rs.getString(2));
                p.getListaFoto().add(rs.getString(3));
                p.getListaFoto().add(rs.getString(4));

            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Prodotto> doRetrieveByNome(String against,int offset,int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto.id, prodotto.nome, prodotto.descrizione, prodotto.marca, prodotto.prezzo, prodotto.categoria, prodotto.foto_copertina FROM prodotto WHERE MATCH (nome) AGAINST (? IN BOOLEAN MODE) LIMIT ?,?");
            ps.setString(1, against);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Prodotto p=new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setMarca((rs.getString(4)));
                p.setPrezzo(rs.getFloat(5));
                p.setFotoCopertina(rs.getString(6));
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countByCategoria(int categoria){
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(prodotto.nome) FROM prodotto JOIN categoria ON prodotto.categoria= categoria.id WHERE categoria.id =?");
            ps.setInt(1,categoria);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrieveByDescrizione(String against,int offset,int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto.id,prodotto.nome,prodotto.descrizione,prodotto.marca,prodotto.prezzo,prodotto.categoria FROM prodotto WHERE MATCH (descrizione)AGAINST(? IN BOOLEAN MODE) LIMIT ?,?");
            ps.setString(1, against);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Prodotto p=new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setMarca((rs.getString(4)));
                p.setPrezzo(rs.getFloat(5));
                p.setFotoCopertina(rs.getString(6));
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Prodotto> doRetrieveByCategoria(int categoria, OrderBy orderBy, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT prodotto.id, prodotto.nome, prodotto.descrizione, prodotto.marca, prodotto.prezzo FROM prodotto, categoria WHERE prodotto.categoria=categoria.id AND categoria.id=? "+orderBy.sql+" LIMIT ?,?");
            ps.setInt(1, categoria);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setMarca((rs.getString(4)));
                p.setPrezzo(rs.getFloat(5));
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Prodotto prodotto, int idCategoria){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT  INTO prodotto(nome, descrizione, marca, prezzo, categoria, categoria) VALUES(?,?,?,?,?,?)");
            ps.setString(1,prodotto.getNome());
            ps.setString(2,prodotto.getDescrizione());
            ps.setString(3,prodotto.getMarca());
            ps.setFloat(4,prodotto.getPrezzo());
            ps.setInt(5,idCategoria);
            ps.setString(6,prodotto.getFotoCopertina());
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("INSERT error,");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            ps = con.prepareStatement("INSERT  INTO foto_prodotto(link1, link2, link3, link4, id_prodotto) VALUES(?,?,?,?,?)");
            ps.setString(1,prodotto.getListaFoto().get(1));
            ps.setString(1,prodotto.getListaFoto().get(2));
            ps.setString(1,prodotto.getListaFoto().get(3));
            ps.setString(1,prodotto.getListaFoto().get(4));
            ps.setInt(5,id);
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("INSERT error,");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Prodotto prodotto,int idCategoria){
        try(Connection con = ConPool.getConnection()){
             PreparedStatement ps=con.prepareStatement("UPDATE prodotto SET nome=?, descrizione=?, marca=?, prezzo=?, categoria=?, foto_copertina=? WHERE id =?");
             ps.setString(1,prodotto.getNome());
             ps.setString(2,prodotto.getDescrizione());
             ps.setString(3,prodotto.getMarca());
             ps.setFloat(4,prodotto.getPrezzo());
             ps.setInt(5,idCategoria);
             ps.setString(6,prodotto.getFotoCopertina());
             ps.setInt(7,prodotto.getId());
        if (ps.executeUpdate() != 1){
            throw new RuntimeException("UPDATE error.");
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String id){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM prodotto WHERE id=?");
            ps.setString(1, id);
            if(ps.executeUpdate() != 1){
                throw new RuntimeException("UPDATE error .");
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
