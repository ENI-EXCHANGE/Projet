package bll;

import bo.Retrait;

import java.util.List;

public interface RetraitManager {

    public List<Retrait> selectAll() throws BLLException;

    public Retrait selectById(int id) throws BLLException;

    public void delete(int id) throws BLLException;

    public Retrait insert(Retrait retrait) throws BLLException;

    public void update(Retrait retrait) throws BLLException;

}
