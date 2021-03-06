package com.android.fukuro;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MkdirActivity extends Activity implements DownloadListTaskCallback{

	ListView myListView;
	URL url = null;
	String result = null;
	String basepath = "http://koyoshi.php.xdomain.jp/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mkdir);
    }

    @Override
    protected void onResume(){
        super.onResume();
        DownloadImage();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }


	public void DownloadImage(){
		DownloadListTask task = new DownloadListTask(this, this);
	    task.execute("");
	}

	@Override
	public void onSuccessDownloadList(String result) {
		// TODO 自動生成されたメソッド・スタブ
		TextView textView = (TextView)findViewById(R.id.textView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		ArrayList<String> itemList = new ArrayList<String>();

		try {
			JSONObject rootObject = new JSONObject(result);
			JSONArray itemArray = rootObject.getJSONArray("item");
			for (int i = 0; i < itemArray.length(); i++) {
			    JSONObject jsonObject = itemArray.getJSONObject(i);
			 // アイテムを追加します
		        adapter.add(jsonObject.getString("ranking_item"));
		        itemList.add(jsonObject.getString("ranking_item"));
			    Log.d("json",jsonObject.getString("ranking_item"));
			    String str = itemList.get(i);

			    HttpURLConnection connection = null;

			    try {
					url = new URL("http://koyoshi.php.xdomain.jp/item/" + str);
					connection = (HttpURLConnection) url.openConnection();

					Log.d("path", url.toString());

					InputStream in = url.openStream();


					BitmapFactory.Options options
					= new BitmapFactory.Options();
					options.inSampleSize = 10;
					Bitmap capturedImage
					= BitmapFactory.decodeStream(
						in,
						null,
						options);

					((ImageView)findViewById(R.id.imageview)).setImageBitmap(capturedImage);

				} catch (MalformedURLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} finally{
					connection.disconnect();
				}


			}
			System.out.print(rootObject);
		} catch (JSONException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		ListView listView = (ListView) findViewById(R.id.listView1);
	    // アダプターを設定します
	    listView.setAdapter(adapter);



	}

	@Override
	public void onFailedDownloadList() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
