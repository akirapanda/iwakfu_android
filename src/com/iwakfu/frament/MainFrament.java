package com.iwakfu.frament;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.iwakfu.R;
import com.iwakfu.activity.ItemDetailActivity;
import com.iwakfu.adapter.MainListViewAdapter;
import com.iwakfu.component.MyListView;
import com.iwakfu.html.util.GetItemsListService;
import com.iwakfu.model.Item;

public class MainFrament extends Fragment {
	private FragmentActivity activity;
	private MyListView listView;
	private MainListViewAdapter adapter;
	private List<Item> items;
	private static final String[] m = { "����", "˫�ֽ�", "ħ��", "���ֽ�", "��", "ذ��",
			"������", "��", "��", "��", "˫�ֽ�", "˫����", "����" };

	private Spinner spinner;
	private ArrayAdapter<String> drop_adapter;

	// ��ǰҳ��
	private int pageNow = 0;
	private String current_type_name = "����";
	// �ж��Ƿ����ڼ��ظ���
	private boolean isLoading = false;
	View footView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frament_main, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		super.onActivityCreated(savedInstanceState);
		activity = getActivity();
		listView = (MyListView) activity.findViewById(R.id.main_listview);
		footView = View.inflate(activity, R.layout.foot, null);
		listView.addFooterView(footView);

		spinner = (Spinner) activity.findViewById(R.id.Spinner01);

		drop_adapter = new ArrayAdapter<String>(activity,
				android.R.layout.simple_spinner_item, m);
		drop_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(drop_adapter);
		spinner.setPrompt(" ��ѡ��װ�����ͣ�");

		items = new ArrayList<Item>();

		adapter = new MainListViewAdapter(items, activity);
		// listView.addFooterView(View.inflate(activity, R.layout.foot, null));
		listView.setAdapter(adapter);
		listView.setOnScrollListener(new MyScrollListener());
		spinner.setVisibility(View.VISIBLE);
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

		new MyAsyncTask().execute("");
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position != 0 && position != items.size() + 1) {
					Intent intent = new Intent(activity,
							ItemDetailActivity.class);
					intent.putExtra("id", items.get(position - 1).getId());
					intent.putExtra("photo_url", items.get(position - 1)
							.getPhotoUrl());
					intent.putExtra("item_name", items.get(position - 1)
							.getName());
					intent.putExtra("item_level",
							String.valueOf(items.get(position - 1).getLevel()));

					startActivity(intent);
				}
			}
		});

	}

	class SpinnerSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			current_type_name = m[arg2];
			pageNow = 1;
			items.clear();
			adapter.clearAll();

			isLoading = false;
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	public class MyAsyncTask extends AsyncTask<String, String, List<Item>> {
		@Override
		protected List<Item> doInBackground(String... params) {
			List<Item> tempNews = new GetItemsListService().getItems(pageNow,
					current_type_name);

			return tempNews;
		}

		@Override
		protected void onPostExecute(List<Item> tempNews) {
			// �ж��Ƿ��ǵ�һ�μ��أ��ǵĻ��������ݿ���ȡ�������滻�ɴ������ȡ�����ݣ������ʾ������һҳ������һҳ���ݼ��뵱ǰ��������
			if (pageNow != 0) {
				items.addAll(tempNews);
			} else {
				items = tempNews;
				// ����һҳ���ݼ������ݿ�
			}
			if (items != null) {
				adapter.setNews(items);
				adapter.notifyDataSetChanged();
			}

			if (tempNews.size() == 0) {
				Toast.makeText(getActivity(), "������������", Toast.LENGTH_LONG)
						.show();
				listView.removeFooterView(footView);

			} else {
				isLoading = false;
			}

		}
	}

	public class MyScrollListener implements OnScrollListener {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {

		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// System.out.println(firstVisibleItem+"   "+visibleItemCount+"    "+totalItemCount);
			if (firstVisibleItem + visibleItemCount == totalItemCount
					&& isLoading == false) {
				isLoading = true;
				pageNow++;

				new MyAsyncTask().execute("");
			}
		}

	}
}
