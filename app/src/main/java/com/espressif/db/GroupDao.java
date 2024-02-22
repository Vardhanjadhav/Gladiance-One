package com.espressif.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.espressif.AppConstants;
import com.espressif.ui.models.Group;

import java.util.List;

@Dao
public interface GroupDao {
    @Query("SELECT * FROM " + AppConstants.GROUP_TABLE)
    List<Group> getGroupsFromStorage();

    /**
     * Update group if it exist in database, insert group otherwise.
     *
     * @param group Group to be inserted / updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(Group group);

    /**
     * Delete the group from database.
     *
     * @param group Group to be deleted.
     */
    @Delete
    void delete(Group group);

    /**
     * Delete all groups from group table.
     */
    @Query("DELETE FROM " + AppConstants.GROUP_TABLE)
    void deleteAll();
}
