package com.timbyr.base;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import com.timbyr.base.fragments.SampleListFragment;
import com.timbyr.base.fragments.SampleMenuFragment;
import com.timbyr.base.R;

import android.util.DisplayMetrics;
import android.view.Display;

public class MainActivity extends SherlockFragmentActivity {

	private SlidingMenu menu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);

		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);

		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setMenu(R.layout.menu);
		
		menu.setSecondaryShadowDrawable(R.drawable.shadowright);
		menu.setSecondaryMenu(R.layout.right_menu);

		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		if (findViewById(R.id.fragment_container) != null) {
			if (savedInstanceState != null) {
				return;
			}

			SampleListFragment listFragment = new SampleListFragment();
			listFragment.setArguments(getIntent().getExtras());

			// Add the fragment to the 'fragment_container' FrameLayout
			getSupportFragmentManager().beginTransaction()
			.add(R.id.fragment_container, listFragment).commit();
		}
		
		if (findViewById(R.id.menu) != null) {
			if (savedInstanceState != null) {
				return;
			}

			SampleMenuFragment leftFragment = new SampleMenuFragment();
			leftFragment.setArguments(getIntent().getExtras());

			getSupportFragmentManager().beginTransaction()
			.add(R.id.menu, leftFragment).commit();
		}
		
		if (findViewById(R.id.right_menu) != null) {
			if (savedInstanceState != null) {
				return;
			}

			SampleMenuFragment rightFragment = new SampleMenuFragment();
			rightFragment.setArguments(getIntent().getExtras());

			getSupportFragmentManager().beginTransaction()
			.add(R.id.right_menu, rightFragment).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			menu.toggle();
			return true;
		case R.id.menu_preferences:
			if(menu.isSecondaryMenuShowing()){
				menu.toggle();
			}
			else{
				menu.showSecondaryMenu();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
}
