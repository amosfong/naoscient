package com.naoscient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.naoscient.room.model.Person;
import com.naoscient.view.PersonListAdapter;
import com.naoscient.view.PersonViewModel;

import java.util.List;

/**
 * @author Amos Fong
 */
public class MainActivity extends AppCompatActivity {

	public static final int ADD_PERSON_ACTIVITY_REQUEST_CODE = 1;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// Add person button

		FloatingActionButton addPersonButton = findViewById(
			R.id.addPersonButton);

		addPersonButton.setOnClickListener(
			new View.OnClickListener() {
				public void onClick(View view) {
					Intent myIntent = new Intent(
						view.getContext(), AddPersonActivity.class);

					startActivityForResult(
						myIntent, ADD_PERSON_ACTIVITY_REQUEST_CODE);
				}
			}
		);

		// Recycler view

		RecyclerView recyclerView = findViewById(R.id.personRecyclerView);

		final PersonListAdapter adapter = new PersonListAdapter(
			new PersonListAdapter.PersonDiff());

		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		// View model

		ViewModelProvider.AndroidViewModelFactory factory =
			ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

		_personViewModel = new ViewModelProvider(
			this, factory
		).get(PersonViewModel.class);

		LiveData<List<Person>> personsLiveData =
			_personViewModel.getAllPersons();

		personsLiveData.observe(
			this,
			persons -> {
				adapter.submitList(persons);
			});

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if ((requestCode == ADD_PERSON_ACTIVITY_REQUEST_CODE) &&
			(resultCode == RESULT_OK)) {

			Person person = new Person(
                0, data.getStringExtra(AddPersonActivity.FIRST_NAME),
                data.getStringExtra(AddPersonActivity.LAST_NAME));

			_personViewModel.insert(person);
		}
        else {
			Toast.makeText(
				getApplicationContext(),
				R.string.there_was_an_unexpected_error,
				Toast.LENGTH_LONG
			).show();
		}
	}

	private PersonViewModel _personViewModel;

}