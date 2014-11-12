package com.android.fukuro;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends Activity {

	static final int REQUEST_CAPTURE_IMAGE = 100;

	Button button1;
	ImageView imageView1;
	File picFile;
	File lookFile = new File("/data/data/com.android.fukuro/Item");
	String picname = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		findViews();
		setListeners();

		FileOutputStream fo;

		File newfile = new File("/data/data/com.android.fukuro/Item");
		File newfile2 = new File("/data/data/com.android.fukuro/Thambnail");

	    if (newfile.mkdir()){
	      //System.out.println("ディレクトリの作成に成功しました");
	      Log.d("ファイル作成","ディレクトリの作成に成功しました");

	    }else{
	      //System.out.println("ディレクトリの作成に失敗しました");
	      Log.d("ファイル作成","ディレクトリの作成に失敗しました");
	    }

	    if (newfile2.mkdir()){
		      //System.out.println("ディレクトリの作成に成功しました");
		      Log.d("ファイル作成","ディレクトリの作成に成功しました");

		    }else{
		      //System.out.println("ディレクトリの作成に失敗しました");
		      Log.d("ファイル作成","ディレクトリの作成に失敗しました");
		    }

	    File f = new File("/data/data/com.android.fukuro/Item/test3.txt");
        	if(f.mkdirs()){
    	        Log.e("text作成","text作成にseikouしました");
        	}else{

	        Log.e("text作成","text作成に失敗しました");

        	}
}

	protected void findViews(){
		button1 = (Button)findViewById(R.id.button1);
		imageView1 = (ImageView)findViewById(R.id.imageview);
	}

	protected String getPicFileName(){
		Calendar c = Calendar.getInstance();
		String s = c.get(Calendar.YEAR)
			+ "_" + (c.get(Calendar.MONTH)+1)
			+ "_" + c.get(Calendar.DAY_OF_MONTH)
			+ "_" + c.get(Calendar.HOUR_OF_DAY)
			+ "_" + c.get(Calendar.MINUTE)
			+ "_" + c.get(Calendar.SECOND)
			+ ".png";
		return s;
	}

	protected void setListeners(){
		button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {

				picname = getPicFileName();
				picFile = new File(
					Environment.getExternalStorageDirectory() + "/Item",
					picname);

				 Intent i = new Intent(getApplicationContext(),InfoEditActivity.class);
				 i.putExtra("Fpath", picFile.toString());
				 i.putExtra("Fname", picname);
				 startActivity(i);

				Intent intent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);


				intent.putExtra(
					MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(picFile));

				startActivityForResult(
					intent,
					REQUEST_CAPTURE_IMAGE);
	            Log.e("test",picFile.toString());
				Log.e("test",getPicFileName());
				Log.e("test",picname);
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

            Intent intent = new Intent(CameraActivity.this,InfoEditActivity.class);

			startActivity(intent);

		}
	}

}

