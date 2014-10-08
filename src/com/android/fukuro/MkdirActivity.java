package com.android.fukuro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MkdirActivity extends Activity {


	  public static void main(String args[]){
		  Log.i("テスト","02");


	  }

	  @Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);

				FileOutputStream fo;

				File newfile = new File("/data/data/com.android.fukuro/maketest");

			    if (newfile.mkdir()){
			      //System.out.println("ディレクトリの作成に成功しました");
			      Log.d("ファイル作成","ディレクトリの作成に成功しました");

			    }else{
			      //System.out.println("ディレクトリの作成に失敗しました");
			      Log.d("ファイル作成","ディレクトリの作成に失敗しました");
			    }

			    try{
			    File f = new File("/data/data/com.android.fukuro/maketest/test2.txt");
		        File parent = f.getParentFile();
		        if (parent != null && parent.canWrite()) { parent.mkdirs(); }
		        fo = new FileOutputStream(f);

			    } catch (IOException e) {
			        Log.e("text作成","text作成に失敗しました");
			    }

				setContentView(R.layout.mkdir);

	  }

}
