package beligas.Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class Tabel_Model_beligas extends AbstractTableModel {
    
    private List<Model_beligas> beligasList;
    
    public Tabel_Model_beligas(List<Model_beligas> beligasList) {
        this.beligasList = beligasList;
    }

    @Override
    public int getRowCount() {
        return beligasList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID Pesanan";
            case 1:
                return "Nama Pelanggan";
            case 2:
                return "Alamat Pelanggan";
            case 3:
                return "Jumlah Gas";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
    Model_beligas beligas = beligasList.get(row);
    switch (column) {
        case 0:
            return beligas.getId();
        case 1:
            return beligas.getNama();
        case 2:
            return beligas.getAlamat();
        case 3:
            return beligas.getJumlah();
        default:
            return null;
    }
}
}
