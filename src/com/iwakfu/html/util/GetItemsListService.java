package com.iwakfu.html.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.iwakfu.global.MyConstant;
import com.iwakfu.model.Item;
import com.iwakfu.util.NetUtil;

public class GetItemsListService {

	public List<Item> getItems(int page, String type_name) {
		System.out.println("");
		List<Item> items = new ArrayList<Item>();
		try {
			String response = NetUtil
					.postAndGetDaet(MyConstant.HOSTNAME+"/api/items?page="
							+ page + "&type_name=" + type_name);
			System.out.println(response);
			JSONArray jsonArray = new JSONArray(response);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Item item = new Item();
				item.setName(jsonObject.getString("name"));
				item.setId(jsonObject.getInt("id"));

				// item.setCate(jsonObject.getJSONObject("item_type").getString(
				// "chinese_name"));
				// item.setUrl(jsonObject.getString(""));
				item.setLevel(jsonObject.getInt("level"));
				item.setPhotoUrl(MyConstant.HOSTNAME
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
