package com.iwakfu.html.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.iwakfu.model.Item;
import com.iwakfu.util.NetUtil;

public class GetItemsListService {

	public List<Item> getItems(int page) {
		System.out.println("");
		List<Item> items = new ArrayList<Item>();
		try {
			String response = NetUtil
					.postAndGetDaet("http://10.168.1.200:3000/api/items?page="
							+ page);
			System.out.println(response);
			JSONArray jsonArray = new JSONArray(response);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Item item = new Item();
				item.setName(jsonObject.getString("name"));
				// item.setCate(jsonObject.getJSONObject("item_type").getString(
				// "chinese_name"));
				// item.setUrl(jsonObject.getString(""));
				item.setPhotoUrl("http://10.168.1.200:3000"
						+ jsonObject.getJSONObject("icon").getString("url"));
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				// item.setDate(dateFormat.format(new Date(jsonObject
				// .getLong("sortTime") * 1000)));
				items.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
}
