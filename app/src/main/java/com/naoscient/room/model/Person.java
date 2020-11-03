package com.naoscient.room.model;

import androidx.room.ColumnInfo;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Person")
public class Person {

	 @ColumnInfo(name = "firstName")
	 private String _firstName;

	 @ColumnInfo(name = "id")
	 @NonNull
	 @PrimaryKey(autoGenerate = true)
	 private int _id;

	 @ColumnInfo(name = "lastName")
	 private String _lastName;

	 public Person(int id, String firstName, String lastName) {
	 	_id = id;
	 	_firstName = firstName;
	 	_lastName = lastName;
	 }

	 public String getFirstName() {
	 	return _firstName;
	 }

	 public String getFullName() {
	 	return getFirstName() + " " + getLastName();
	 }

	 public int getId() {
	 	return _id;
	 }

	 public String getLastName() {
	 	return _lastName;
	 }

}