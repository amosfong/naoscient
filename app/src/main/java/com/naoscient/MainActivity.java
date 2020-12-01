package com.naoscient;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.navigation.NavigationView;
import com.naoscient.fragment.HomeFragment;
import com.naoscient.fragment.PeopleFragment;
import com.naoscient.room.model.Person;
import com.naoscient.view.PersonListAdapter;
import com.naoscient.view.PersonViewModel;

import java.util.List;

/**
 * @author Amos Fong
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_activity);

		_drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
 		_navigationView = (NavigationView)findViewById(R.id.navigation_view);
		_toolbar = (Toolbar)findViewById(R.id.toolbar);

		setSupportActionBar(_toolbar);

		_setupDrawerContent();

		_setupDrawerToggle();
	}

	@Override
	protected void onPostCreate(Bundle bundle) {
		super.onPostCreate(bundle);

		_actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration configuration) {
		super.onConfigurationChanged(configuration);

		_actionBarDrawerToggle.onConfigurationChanged(configuration);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (_actionBarDrawerToggle.onOptionsItemSelected(menuItem)) {
			return true;
		}

		return super.onOptionsItemSelected(menuItem);
	}

	private void _setupDrawerToggle() {
		_actionBarDrawerToggle = new ActionBarDrawerToggle(
			this, _drawerLayout, _toolbar, R.string.open_navigation_drawer,
			R.string.close_navigation_drawer);

		_actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
		_actionBarDrawerToggle.syncState();

		_drawerLayout.addDrawerListener(_actionBarDrawerToggle);
	}

 	private void _setupDrawerContent() {
		_navigationView.setNavigationItemSelectedListener(
			new NavigationView.OnNavigationItemSelectedListener() {

				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem) {
					_selectDrawerItem(menuItem);

					return true;
				}

			});

	   	_navigationView.bringToFront();
	}

	private void _selectDrawerItem(MenuItem menuItem) {
		try {
			Class fragmentClass = null;

			switch (menuItem.getItemId()) {
				case R.id.navigation_home_fragment:
					fragmentClass = HomeFragment.class;

					break;
				case R.id.navigation_people_fragment:
					fragmentClass = PeopleFragment.class;

					break;
				default:
					fragmentClass = HomeFragment.class;
			}

			Fragment fragment = (Fragment)fragmentClass.newInstance();

			FragmentManager fragmentManager = getSupportFragmentManager();

			fragmentManager.beginTransaction().replace(
				R.id.navHostFragment, fragment).commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		menuItem.setChecked(true);

		setTitle(menuItem.getTitle());

		_drawerLayout.closeDrawers();
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);

		return true;
	}
	*/

	private ActionBarDrawerToggle _actionBarDrawerToggle;
	private AppBarConfiguration _appBarConfiguration;
	private DrawerLayout _drawerLayout;
	private NavigationView _navigationView;
	private Toolbar _toolbar;

}