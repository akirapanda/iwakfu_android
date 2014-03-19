package com.iwakfu.html.util;

import org.json.JSONObject;

import com.iwakfu.global.MyConstant;
import com.iwakfu.model.ItemDetail;
import com.iwakfu.util.NetUtil;

public class GetItemDetailService {

	public ItemDetail getItems(int id) {
		ItemDetail item_detail = new ItemDetail();

		try {
			String response = NetUtil.postAndGetDaet(MyConstant.HOSTNAME
					+ "/api/items/" + id);
			System.out.println(response);
			JSONObject jsonObject = new JSONObject(response);
			if (!JSONObject.NULL.equals(jsonObject.get("hp"))) {
				item_detail.setHp(jsonObject.getInt("hp"));
			}
			if (!JSONObject.NULL.equals(jsonObject.get("ap"))) {
				item_detail.setAp(jsonObject.getInt("ap"));
			}
			if (!JSONObject.NULL.equals(jsonObject.get("mp"))) {
				item_detail.setMp(jsonObject.getInt("mp"));
			}
			if (!JSONObject.NULL.equals(jsonObject.get("initivate"))) {
				item_detail.setMp(jsonObject.getInt("initivate"));
			}
			if (!JSONObject.NULL.equals(jsonObject.get("dodge"))) {
				item_detail.setMp(jsonObject.getInt("dodge"));
			}
			if (!JSONObject.NULL.equals(jsonObject.get("lock"))) {
				item_detail.setMp(jsonObject.getInt("lock"));
			}
			if (!JSONObject.NULL.equals(jsonObject.get("backstab"))) {
				item_detail.setMp(jsonObject.getInt("backstab"));
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return item_detail;
	}
}
