package com.naoscient.view;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.naoscient.repository.PersonRepository;
import com.naoscient.room.dao.PersonDao;
import com.naoscient.room.database.PersonRoomDatabase;
import com.naoscient.room.model.Person;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

	public PersonViewModel (Application application) {
	   super(application);

	   _personRepository = new PersonRepository(application);

	   _allPersons = _personRepository.getAllPersons();
	}

	public LiveData<List<Person>> getAllWords() {
		return _allPersons;
	}

	public void insert(Person person) {
		_personRepository.insert(person);
	}

	private PersonRepository _personRepository;

	private final LiveData<List<Person>> _allPersons;

}