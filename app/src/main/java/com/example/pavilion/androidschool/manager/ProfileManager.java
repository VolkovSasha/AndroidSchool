package com.example.pavilion.androidschool.manager;

import com.example.pavilion.androidschool.db.Profile;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pavilion on 07.06.2015.
 */
public class ProfileManager {
    private Dao<Profile, Integer> profilesDao = null;

    public Dao<Profile, Integer> getContactDao() {
        return this.profilesDao;
    }

    public void setContactDao(Dao<Profile, Integer> dao) {
        this.profilesDao = dao;
    }

    public void create(Profile profile) throws SQLException {
        this.profilesDao.create(profile);
    }

    public void update(Profile profile) throws SQLException {
        this.profilesDao.update(profile);
    }

    public void delete(Profile profile) throws SQLException {
        this.profilesDao.delete(profile);
    }

    public List<Profile> getAll() throws SQLException {
        return profilesDao.queryForAll();
    }

    public Profile getLast() throws SQLException {
        List<Profile> list = this.getAll();
        return list.get(list.size() - 1);
    }
}
