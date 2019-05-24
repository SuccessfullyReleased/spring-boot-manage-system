package dao;

import model.Note;

public interface NoteDao {
    Note selectByPrimaryKey(Integer nid);
}