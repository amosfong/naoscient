package com.naoscient.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.naoscient.room.dao.PersonDao;
import com.naoscient.room.database.PersonRoomDatabase;
import com.naoscient.room.model.Person;

import java.util.List;

public class PersonRepository {

	public PersonRepository(Application application) {
		PersonRoomDatabase personRoomDatabase = PersonRoomDatabase.getDatabase(
			application);

		_personDao = personRoomDatabase.personDao();

		_allPersons = _personDao.getPersons();
	}

	public LiveData<List<Person>> getAllPersons() {
		return _allPersons;
	}

	public void insert(Person person) {
		PersonRoomDatabase.databaseWriteExecutor.execute(
			() -> {
				_personDao.insert(person);
			}
		);
	}

	private PersonDao _personDao;

	private LiveData<List<Person>> _allPersons;

}