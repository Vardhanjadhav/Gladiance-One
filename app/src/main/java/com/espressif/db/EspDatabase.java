package com.espressif.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.espressif.AppConstants;
import com.espressif.ui.activities.EspNode;
import com.espressif.ui.models.Group;
import com.espressif.ui.models.NotificationEvent;

@Database(entities = {EspNode.class, Group.class, NotificationEvent.class}, version = 3, exportSchema = false)
@TypeConverters({StringArrayListConverters.class})
public abstract class EspDatabase extends RoomDatabase {

    private static EspDatabase espDatabase;

    public abstract NodeDao getNodeDao();
    public abstract GroupDao getGroupDao();
    public abstract NotificationDao getNotificationDao();

    public static EspDatabase getInstance(Context context) {
        if (null == espDatabase) {
            espDatabase = buildDatabaseInstance(context);
        }
        return espDatabase;
    }

    private static EspDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                        EspDatabase.class,
                        AppConstants.ESP_DATABASE_NAME)
                .addMigrations(MIGRATION_2_3)
                .addMigrations(MIGRATION_1_3)
                .allowMainThreadQueries().build();
    }

    public void cleanUp() {
        espDatabase = null;
    }

    static final Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `" + AppConstants.GROUP_TABLE + "` (`groupId` TEXT NOT NULL, `group_name` TEXT NOT NULL, `node_list` TEXT, PRIMARY KEY(`groupId`))");
            database.execSQL("CREATE TABLE IF NOT EXISTS `" + AppConstants.NOTIFICATION_TABLE + "` (`notificationId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `event_version` TEXT, `event_type` TEXT, `description` TEXT, `id` TEXT, `event_data` TEXT, `timestamp` INTEGER NOT NULL, `notification_msg` TEXT)");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `" + AppConstants.NOTIFICATION_TABLE + "` (`notificationId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `event_version` TEXT, `event_type` TEXT, `description` TEXT, `id` TEXT, `event_data` TEXT, `timestamp` INTEGER NOT NULL, `notification_msg` TEXT)");
        }
    };
}