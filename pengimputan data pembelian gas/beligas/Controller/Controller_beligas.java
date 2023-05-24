/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beligas.Controller;

import beligas.DAO.DAO_beligas;
import beligas.DAOImplement.Implement_beligas;
import beligas.Model.Model_beligas;
import beligas.Model.Tabel_Model_beligas;
import beligas.View.View_beligas;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Aisyah Safira 
 */
public class Controller_beligas {
    
     View_beligas frame_beligas;
     Implement_beligas implement_beligas;
     List<Model_beligas> beligasList;
     
    public Controller_beligas(View_beligas frame_beligas) {
    this.frame_beligas = frame_beligas;
    implement_beligas = new DAO_beligas();
    beligasList = implement_beligas.getALL();
}
// Tombol Reset
public void reset(){
    frame_beligas.getTxtAlamat().setText("");
    frame_beligas.getTxtCari().setText("");
    frame_beligas.getTxtId().setText("");
    frame_beligas.getTxtJumlah().setText("");
    frame_beligas.getTxtNama().setText("");
}
// Tampil Data Ke Tabel
public void isiTable(){
    beligasList = implement_beligas.getALL();
    Tabel_Model_beligas tmb = new Tabel_Model_beligas(beligasList);
    frame_beligas.getTabelGas().setModel(tmb);
}

// Menampilkan Data Ke Form Ketika Data Di Klik
public void isiField(int row) {
    frame_beligas.getTxtId().setText(String.valueOf(beligasList.get(row).getId()));
    frame_beligas.getTxtNama().setText(beligasList.get(row).getNama());
    frame_beligas.getTxtJumlah().setText(String.valueOf(beligasList.get(row).getJumlah()));
    frame_beligas.getTxtAlamat().setText(beligasList.get(row).getAlamat());
}

// Insert Data Dari Form Ke Database
public void insert() {
    if (!frame_beligas.getTxtNama().getText().trim().isEmpty() &&
            !frame_beligas.getTxtAlamat().getText().trim().isEmpty() &&
            !frame_beligas.getTxtJumlah().getText().trim().isEmpty()) {
        
        Model_beligas beligas = new Model_beligas();
        beligas.setNama(frame_beligas.getTxtNama().getText());
        beligas.setAlamat(frame_beligas.getTxtAlamat().getText());
        
        try {
            int jumlahGas = Integer.parseInt(frame_beligas.getTxtJumlah().getText());
            beligas.setJumlah(jumlahGas);
            
            implement_beligas.insert(beligas);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame_beligas, "Jumlah Gas harus berupa angka");
        }
    } else {
        JOptionPane.showMessageDialog(frame_beligas, "Data tidak boleh kosong");
    }
}

// Update Data Dari Tabel Ke Database
public void update() {
    if (!frame_beligas.getTxtId().getText().trim().isEmpty()) {
        Model_beligas beligas = new Model_beligas();
        beligas.setId(Integer.parseInt(frame_beligas.getTxtId().getText()));
        beligas.setNama(frame_beligas.getTxtNama().getText());
        beligas.setAlamat(frame_beligas.getTxtAlamat().getText());
         beligas.setJumlah(Integer.parseInt(frame_beligas.getTxtJumlah().getText()));

        implement_beligas.update(beligas);
        JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
    } else {
        JOptionPane.showMessageDialog(frame_beligas, "Silahkan pilih data yang akan diupdate");
    }
}

// Hapus Data Pada Tabel berdasarkan ID
public void delete() {
    if (!frame_beligas.getTxtId().getText().trim().isEmpty()) {
        int id = Integer.parseInt(frame_beligas.getTxtId().getText());
        implement_beligas.delete(id);
        
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    } else {
        JOptionPane.showMessageDialog(frame_beligas, "Silahkan pilih data yang akan dihapus");
    }
}
// Cari Data
public void isiTableCariNama() {
    String keyword = frame_beligas.getTxtCari().getText();
    beligasList = implement_beligas.getCariNama(keyword);
    Tabel_Model_beligas tmb = new Tabel_Model_beligas(beligasList);
    frame_beligas.getTabelGas().setModel(tmb);
}

public void Cari() {
    if (!frame_beligas.getTxtCari().getText().trim().isEmpty()) {
        implement_beligas.getCariNama(frame_beligas.getTxtCari().getText());
        isiTableCariNama();
    } else {
        JOptionPane.showMessageDialog(frame_beligas, "Silahkan masukkan kata kunci pencarian");
    }
}











}


