package dal;

import bo.Retrait;

import java.util.List;

public interface RetraitDAO {
    public Retrait insert(Retrait retrait)throws DALException;

    public List<Retrait> selectAll() throws Exception;

    public void delete(Integer id) throws DALException;

    public void update(Retrait retrait) throws DALException;

    public Retrait selectById(Integer id) throws Exception;
}
