package com.naoscient.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.naoscient.R;

class PersonViewHolder extends RecyclerView.ViewHolder {

	private PersonViewHolder(View itemView) {
		super(itemView);

		_personTextView = itemView.findViewById(R.id.textView);
	}

	public void bind(String text) {
		_personTextView.setText(text);
	}

	static PersonViewHolder create(ViewGroup viewGroup) {
		View view = LayoutInflater.from(
			viewGroup.getContext()
		).inflate(
			R.layout.people_view_item, viewGroup, false
		);

		return new PersonViewHolder(view);
	}

	private final TextView _personTextView;

}