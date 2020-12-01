package com.naoscient.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.naoscient.R;

/**
 * @author Amos Fong
 */
public class HomeFragment extends Fragment {

	@Override
	public View onCreateView(
		LayoutInflater layoutInflater, ViewGroup viewGroup,
		Bundle bundle) {

		return layoutInflater.inflate(R.layout.home_fragment, viewGroup, false);
	}

}