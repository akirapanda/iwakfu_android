package com.iwakfu.html.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.iwakfu.global.MyConstant;
import com.iwakfu.model.Item;
import com.iwakfu.model.ItemDetail;
import com.iwakfu.model.ItemStat;
import com.iwakfu.util.NetUtil;

public class GetItemStatsService {

	public List<ItemStat> getItems(int id) {
		List<ItemStat> item_stats = new ArrayList<ItemStat>();

		try {
			String response = NetUtil.postAndGetDaet(MyConstant.HOSTNAME
					+ "/api/item_stats/" + id);
			System.out.println(response);
			JSONArray jsonArray = new JSONArray(response);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				ItemStat item_stat = new ItemStat();

				if (!JSONObject.NULL.equals(jsonObject.get("cate"))) {
					String cate = jsonObject.getString("cate");
					if (cate.equals("use")) {
						cate = "使用时";
					}
					if (cate.equals("equipe")) {
						cate = "装备时";
					}
					item_stat.setCate(cate);
				}

				if (!JSONObject.NULL.equals(jsonObject.get("stat_type"))) {
					String stat_type = jsonObject.getString("stat_type");
					if (stat_type.equals("effect")) {
						stat_type = "(效果)";
					} else {

					}
					if (stat_type.equals("critical")) {
						stat_type = "(暴击)";
					}
					item_stat.setStat_type(stat_type);
				}

				if (!JSONObject.NULL.equals(jsonObject.get("content"))) {
					item_stat.setContent(jsonObject.getString("content"));
				}
				if (!JSONObject.NULL.equals(jsonObject.get("value"))) {
					item_stat.setValue(jsonObject.getInt("value"));
				}

				if (!JSONObject.NULL.equals(jsonObject.get("air"))) {
					item_stat.setAir(jsonObject.getBoolean("air"));
				}
				if (!JSONObject.NULL.equals(jsonObject.get("water"))) {
					item_stat.setWater(jsonObject.getBoolean("water"));
				}
				if (!JSONObject.NULL.equals(jsonObject.get("earth"))) {
					item_stat.setEarth(jsonObject.getBoolean("earth"));
				}
				if (!JSONObject.NULL.equals(jsonObject.get("fire"))) {
					item_stat.setFire(jsonObject.getBoolean("fire"));
				}
				item_stats.add(item_stat);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return item_stats;
	}
}
