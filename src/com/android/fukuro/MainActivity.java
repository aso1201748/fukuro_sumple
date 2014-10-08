package com.android.fukuro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		Intent intent = null;
		Log.e("テスト","05");

		switch(v.getId()){

			case R.id.button1:

				//インテントに、この画面と、遷移する別の画面を指定する
				intent = new Intent(MainActivity.this, CameraActivity.class);

				//インテントで指定した別の画面に遷移する
				startActivity(intent);

				break;

			case R.id.button2:

				//インテントに、この画面と、遷移する別の画面を指定する
				intent = new Intent(MainActivity.this, MkdirActivity.class);

				//インテントで指定した別の画面に遷移する
				startActivity(intent);

				break;

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);

        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
