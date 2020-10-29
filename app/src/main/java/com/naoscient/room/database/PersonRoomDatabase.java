package com.naoscient.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.naoscient.room.dao.PersonDao;
import com.naoscient.room.model.Person;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PersonRoomDatabase extends RoomDatabase {

	public static volatile PersonRoomDatabase INSTANCE;

	public static final int NUMBER_OF_THREADS = 4;

	public static final ExecutorService databaseWriteExecutor =
		Executors.newFixedThreadPool(NUMBER_OF_THREADS);

	public static PersonRoomDatabase getDatabase(final Context context) {
		if (INSTANCE == null) {
			synchronized (PersonRoomDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(
						context.getApplicationContext(),
						PersonRoomDatabase.class, "NaoscientDB"
					).build();
				}
			}
		}
		return INSTANCE;
	}

	public abstract PersonDao personDao();

}