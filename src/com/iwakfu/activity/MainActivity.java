package com.iwakfu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import com.iwakfu.R;

public class MainActivity extends FragmentActivity {

	private Fragment[] fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frament_main);
		fragments = new Fragment[1];
		fragments[0] = getSupportFragmentManager().findFragmentById(
				R.id.farment_main);

		getSupportFragmentManager().beginTransaction().show(fragments[0])
				.commit();
	}

	public void mainClick(View view) {
		getSupportFragmentManager().beginTransaction().show(fragments[0])
				.commit();
	}
	
	public void phoneClick(View view) {
		getSupportFragmentManager().beginTransaction().hide(fragments[0]).show(fragments[1])
				.commit();
	}

}
