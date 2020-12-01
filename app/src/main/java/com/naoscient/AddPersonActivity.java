package com.naoscient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Amos Fong
 */
public class AddPersonActivity extends AppCompatActivity {

	public static final String FIRST_NAME =
		"com.naoscient.model.Person.FIRST_NAME";

	public static final String LAST_NAME =
		"com.naoscient.model.Person.LAST_NAME";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.people_add_person_activity);

		_firstNameEditText = findViewById(R.id.firstName);
		_lastNameEditText = findViewById(R.id.lastName);

		final Button button = findViewById(R.id.saveButton);

		button.setOnClickListener(
			view -> {
				Intent intent = new Intent();

				intent.putExtra(
					FIRST_NAME, _firstNameEditText.getText().toString());
				intent.putExtra(
					LAST_NAME, _lastNameEditText.getText().toString());

			   setResult(RESULT_OK, intent);

			   finish();
			});
	}

	 private EditText _firstNameEditText;
	 private EditText _lastNameEditText;

}
