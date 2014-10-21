package com.android.fukuro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends Activity {

	private DBHelper dbHelper = new DBHelper(this);

	public static SQLiteDatabase db;

		static final int REQUEST_CAPTURE_IMAGE = 100;

		Button button1;
		ImageView imageView1;
		File picFile;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.camera);
			findViews();
			setListeners();

			db = dbHelper.getWritableDatabase();

		}

		@Override
		public void onDestroy(){
			super.onDestroy();
			dbHelper.close();
		}

		protected void findViews(){
			button1 = (Button)findViewById(R.id.button1);
			imageView1 = (ImageView)findViewById(R.id.imageView1);
		}

		protected void setListeners(){
			button1.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Log.i("check","調べています");
					picFile = new File(
							"/storage/sdcard0/DCIM/Camera/Item",System.currentTimeMillis()+".png");
					Log.i("check","fileok?");
					Intent intent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);

					intent.putExtra(
							MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(picFile));
					Log.i("check","putExtraok?");

					startActivityForResult(
						intent,
						REQUEST_CAPTURE_IMAGE);
				}
			});
		}

		@Override
		protected void onActivityResult(
			int requestCode,
			int resultCode,
			Intent data) {
			if(REQUEST_CAPTURE_IMAGE == requestCode
				&& resultCode == Activity.RESULT_OK ){
				try {
					FileInputStream in = new FileInputStream(picFile);
					BitmapFactory.Options options
						= new BitmapFactory.Options();
					options.inSampleSize = 10;
					Bitmap capturedImage
						= BitmapFactory.decodeStream(
							in,
							null,
							options);
					imageView1.setImageBitmap(capturedImage);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		}
	}

}


