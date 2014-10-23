package com.android.fukuro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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

		Intent i = getIntent();
		String path = i.getStringExtra("Fpath");
		Log.d("check",path);

		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		BitmapFactory.Options options
			= new BitmapFactory.Options();
		options.inSampleSize = 10;
		Bitmap capturedImage
			= BitmapFactory.decodeStream(
				in,
				null,
				options);

		((ImageView)findViewById(R.id.imageView1)).setImageBitmap(capturedImage);

	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		dbHelper.close();
	}

}
