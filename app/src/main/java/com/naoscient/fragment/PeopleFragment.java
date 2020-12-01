package com.naoscient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.naoscient.R;
import com.naoscient.room.model.Person;
import com.naoscient.view.PersonListAdapter;
import com.naoscient.view.PersonViewModel;

import java.util.List;

/**
 * @author Amos Fong
 */
public class PeopleFragment extends Fragment {

	public static final int ADD_PERSON_ACTIVITY_REQUEST_CODE = 1;

	/*
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
	}*/

	@Override
	public View onCreateView(
		LayoutInflater layoutInflater, ViewGroup viewGroup,
		Bundle bundle) {

		View view = layoutInflater.inflate(
			R.layout.people_fragment, viewGroup, false);

		// Recycler view

		RecyclerView recyclerView = view.findViewById(R.id.peopleRecyclerView);

		final PersonListAdapter adapter = new PersonListAdapter(
			new PersonListAdapter.PersonDiff());

		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(
			new LinearLayoutManager(view.getContext()));

		// View model

		ViewModelProvider.AndroidViewModelFactory androidViewModelFactory =
			ViewModelProvider.AndroidViewModelFactory.getInstance(
				getActivity().getApplication());

		_personViewModel = new ViewModelProvider(
			getActivity(), androidViewModelFactory
		).get(PersonViewModel.class);

		LiveData<List<Person>> personsLiveData =
			_personViewModel.getAllPersons();

		personsLiveData.observe(
			getActivity(),
			persons -> {
				adapter.submitList(persons);
			});

		return view;
	}

	private PersonViewModel _personViewModel;

}