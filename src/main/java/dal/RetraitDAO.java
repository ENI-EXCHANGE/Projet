package dal;

import bo.Retrait;

import java.util.List;

public interface RetraitDAO {
    public Retrait insert(Retrait Retrait)throws DALException;

    public List<Retrait> selectAll() throws DALException;

    public void delete(Integer id) throws DALException;

    public void update(Retrait user) throws DALException;

    public Retrait selectById(Integer id) throws DALException;
}
