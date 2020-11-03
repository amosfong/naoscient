package com.naoscient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naoscient.view.PersonListAdapter;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		Button button = findViewById(R.id.button);

		button.setOnClickListener(
			new View.OnClickListener() {
				public void onClick(View view) {
					Intent myIntent = new Intent(
						view.getContext(), Activity2.class);

					startActivityForResult(myIntent, 0);
				}
			}
		);

		RecyclerView recyclerView = findViewById(R.id.personRecyclerView);

		final PersonListAdapter adapter = new PersonListAdapter(
			new PersonListAdapter.PersonDiff());

		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

}