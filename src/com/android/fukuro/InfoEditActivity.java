package com.android.fukuro;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class InfoEditActivity extends Activity {

	private DBHelper dbHelper = new DBHelper(this);

	public static SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infoedit);

		Log.d("テスト","05");

		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		dbHelper.close();
	}

}
