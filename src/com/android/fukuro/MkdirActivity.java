package com.android.fukuro;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MkdirActivity extends Activity implements View.OnClickListener{

	private DBHelper dbHelper = new DBHelper(this);

	public static SQLiteDatabase db;

	  public static void main(String args[]){
		  Log.i("テスト","02");


	  }

	  @Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);

				setContentView(R.layout.mkdir);

				db = dbHelper.getWritableDatabase();

		        Button btn1 = (Button)findViewById(R.id.button1);
		        btn1.setOnClickListener(this);

	  }

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

		this.dbHelper.InsertItem(db,"2014_12_20_15_30.png","category","memo");

	}

}
