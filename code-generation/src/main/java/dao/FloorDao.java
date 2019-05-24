package dao;

import model.Floor;

public interface FloorDao {
    Floor selectByPrimaryKey(Integer fid);
}