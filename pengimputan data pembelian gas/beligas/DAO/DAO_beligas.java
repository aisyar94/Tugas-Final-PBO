package beligas.DAO;

import beligas.DAOImplement.Implement_beligas;
import beligas.Koneksi.Koneksi_beligasDB;
import beligas.Model.Model_beligas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_beligas implements Implement_beligas {

    Connection connection;
    final String insert = "INSERT INTO pesanan (ID_Pesanan, Nama_Pelanggan, Alamat_Pelanggan, Jumlah_Gas) VALUES (?,?,?,?);";
    final String update = "UPDATE pesanan SET Nama_Pelanggan=?, Alamat_Pelanggan=?, Jumlah_Gas=? WHERE ID_Pesanan=? ;";
    final String delete = "DELETE FROM pesanan WHERE ID_Pesanan=? ;";
    final String selectAll = "SELECT * FROM pesanan;";
    final String searchByNama = "SELECT * FROM pesanan WHERE Nama_Pelanggan LIKE ?;";

    private List<Model_beligas> beligasList;

    public DAO_beligas() {
        connection = Koneksi_beligasDB.getConnection();
        beligasList = new ArrayList<>();
    }

    @Override
    public void insert(Model_beligas beligas) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setInt(1, beligas.getId());
            statement.setString(2, beligas.getNama());
            statement.setString(3, beligas.getAlamat());
            statement.setInt(4, beligas.getJumlah());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(Model_beligas beligas) {
    PreparedStatement statement = null;
    try {
        statement = connection.prepareStatement(update);
        statement.setString(1, beligas.getNama());
        statement.setString(2, beligas.getAlamat());
        statement.setInt(3, beligas.getJumlah()); // Menggunakan nilai jumlah gas dari model beligas
        statement.setInt(4, beligas.getId());
        statement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Model_beligas> getALL() {
        List<Model_beligas> beligasList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAll);
            while (resultSet.next()) {
                int ID_Pesanan = resultSet.getInt("ID_Pesanan");
                String NamaPelanggan = resultSet.getString("Nama_Pelanggan");
                String AlamatPelanggan = resultSet.getString("Alamat_Pelanggan");
                int JumlahGas = resultSet.getInt("Jumlah_Gas");
                Model_beligas beligas = new Model_beligas();
                beligas.setId(ID_Pesanan);
                beligas.setNama(NamaPelanggan);
                beligas.setAlamat(AlamatPelanggan);
                beligas.setJumlah(JumlahGas);
                beligasList.add(beligas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_beligas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beligasList;
    }

    @Override
    public List<Model_beligas> getCariNama(String keyword) {
    List<Model_beligas> beligasList = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
        String query = "SELECT * FROM pesanan WHERE Nama_Pelanggan LIKE ? OR Alamat_Pelanggan LIKE ? OR Jumlah_Gas LIKE ?;";
        statement = connection.prepareStatement(query);
        String searchKeyword = "%" + keyword + "%";
        statement.setString(1, searchKeyword);
        statement.setString(2, searchKeyword);
        statement.setString(3, searchKeyword);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int ID_Pesanan = resultSet.getInt("ID_Pesanan");
            String NamaPelanggan = resultSet.getString("Nama_Pelanggan");
            String AlamatPelanggan = resultSet.getString("Alamat_Pelanggan");
            int JumlahGas = resultSet.getInt("Jumlah_Gas");
            Model_beligas beligas = new Model_beligas();
            beligas.setId(ID_Pesanan);
            beligas.setNama(NamaPelanggan);
            beligas.setAlamat(AlamatPelanggan);
            beligas.setJumlah(JumlahGas);
            beligasList.add(beligas);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
        return beligasList;
    }
}
