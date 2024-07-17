package com.example.template.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.template.object.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);
    @Update
    void updateUser(User user);
    @Query("SELECT * FROM user")
    User getUser();
    @Query("SELECT * FROM user WHERE userId = :userId")
    User getUserById(int userId);
}
