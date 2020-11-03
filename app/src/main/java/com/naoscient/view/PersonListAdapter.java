package com.naoscient.view;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.naoscient.room.model.Person;

public class PersonListAdapter extends ListAdapter<Person, PersonViewHolder> {

	public PersonListAdapter(@NonNull DiffUtil.ItemCallback<Person> diffCallback) {
		super(diffCallback);
	}

	@Override
	public PersonViewHolder onCreateViewHolder(
		ViewGroup viewGroup, int viewType) {

		return PersonViewHolder.create(viewGroup);
	}

	@Override
	public void onBindViewHolder(PersonViewHolder holder, int position) {
		Person person = getItem(position);

		holder.bind(person.getFullName());
	}

	public static class PersonDiff extends DiffUtil.ItemCallback<Person> {

		@Override
		public boolean areItemsTheSame(
			@NonNull Person oldPerson, @NonNull Person newPerson) {

			return oldPerson == newPerson;
		}

		@Override
		public boolean areContentsTheSame(
			@NonNull Person oldPerson, @NonNull Person newPerson) {

			return oldPerson.getId() == newPerson.getId();
		}

	}
}