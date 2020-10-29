package com.naoscient.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.naoscient.room.model.Person;

import java.util.List;

@Dao
public interface PersonDao {

	@Insert()
	void insert(Person person);

	@Query("delete from Person")
	void deleteAll();

	@Query("select * from Person order by firstName asc")
	LiveData<List<Person>> getPersons();

}