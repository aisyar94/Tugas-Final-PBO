package beligas.DAOImplement;

import beligas.DAO.DAO_beligas;
import beligas.Model.Model_beligas;
import java.util.ArrayList;
import java.util.List;

public interface Implement_beligas {
    
    public void insert(Model_beligas beligas);
    
    public void update(Model_beligas beligas);
    
    public void delete(int ID_Pesanan);
    
    public List<Model_beligas> getALL();
    
    public List<Model_beligas> getCariNama(String keyword);
    
}