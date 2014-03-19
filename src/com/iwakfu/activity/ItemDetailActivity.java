package com.iwakfu.activity;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iwakfu.R;
import com.iwakfu.html.util.GetItemStatsService;
import com.iwakfu.model.ItemDetail;
import com.iwakfu.model.ItemStat;
import com.iwakfu.util.GetBitmapUtil;

public class ItemDetailActivity extends Activity {
	ImageView item_image;
	String photo_url;
	ItemDetail item_detail;
	List<ItemStat> item_stats;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_item_detail);
		TextView item_name = (TextView) findViewById(R.id.text_item_name);
		TextView item_level = (TextView) findViewById(R.id.text_item_level);

		item_image = (ImageView) findViewById(R.id.item_image);
		item_name.setText(getIntent().getStringExtra("item_name"));
		item_level.setText("µÈ¼¶:" + getIntent().getStringExtra("item_level"));
		photo_url = getIntent().getStringExtra("photo_url");
		Log.d("Image", photo_url);

		new MyAsyncTaskGetBitmap().execute("");

		// Bitmap bitmap = getBitmapFromUrl(photo_url);

		// item_image.setImageBitmap(bitmap);
		// item_image.invalidate();
	}

	public class MyAsyncTaskGetBitmap extends AsyncTask<String, String, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = new GetBitmapUtil().getBitmapByUrl(photo_url);
			item_stats = new GetItemStatsService().getItems(getIntent()
					.getIntExtra("id", 0));

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			item_image.setImageBitmap(bitmap);
			LinearLayout layout = (LinearLayout) findViewById(R.id.layout_stats);
			for (ItemStat stat : item_stats) {
				RelativeLayout relativeLayout = (RelativeLayout) View.inflate(
						ItemDetailActivity.this, R.layout.item_stat, null);

				TextView tv1 = (TextView) relativeLayout
						.findViewById(R.id.text_stat_content);
				tv1.setText(stat.getCate() + stat.getStat_type() + ":"
						+ stat.getValue() + " " + stat.getContent());

				if (stat.isAir()) {
					ImageView element = (ImageView) relativeLayout
							.findViewById(R.id.stat_air);

					element.setVisibility(1);
				}

				if (stat.isWater()) {
					ImageView element = (ImageView) relativeLayout
							.findViewById(R.id.stat_water);

					element.setVisibility(1);
				}

				if (stat.isFire()) {
					ImageView element = (ImageView) relativeLayout
							.findViewById(R.id.stat_fire);

					element.setVisibility(1);
				}

				if (stat.isEarth()) {
					ImageView element = (ImageView) relativeLayout
							.findViewById(R.id.stat_earth);

					element.setVisibility(1);
				}

				
				layout.addView(relativeLayout);
			}

		}
	}

}
