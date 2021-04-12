package bll;

import bo.Retrait;

import java.util.List;

public interface RetraitManager {

    public List<Retrait> selectAll() throws Exception;

    public Retrait selectById(int id) throws Exception;

    public void delete(int id) throws BLLException;

    public Retrait insert(Retrait retrait) throws BLLException;

    public void update(Retrait retrait) throws BLLException;

}
