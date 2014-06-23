package james.toy.rain1;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	SimpleAdapter adapter;
	public Button btn;
	public ArrayList<RainData> rainDataList;	
	public ProgressBar bar;
	
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = new SimpleAdapter(
				this,
				list,
				android.R.layout.simple_list_item_2,
				new String[] { "Station", "Status" },
				new int[] { android.R.id.text1, android.R.id.text2 });
		
		ListView lv = (ListView)findViewById(R.id.listView1);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				RainData rd = rainDataList.get(position);
				
				AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
				b.setTitle(rd.station);
				b.setMessage(rd.getDetail());
				b.create().show();
			}
		});
		
		bar = (ProgressBar)findViewById(R.id.progressBar1);
		btn = (Button)findViewById(R.id.button1);
		btn.setText("Busy");
		btn.setEnabled(false);
		
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new AsyncParser(MainActivity.this).execute();
				btn.setText("Busy");
				btn.setEnabled(false);
				bar.setVisibility(View.VISIBLE);
			}
		});
		
		new AsyncParser(this).execute();
		bar.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onResume() {
		super.onResume();

		new AsyncParser(this).execute();
		bar.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

class AsyncParser extends AsyncTask<Void, Integer, RainParser> {
	
	MainActivity activity;

	public AsyncParser(MainActivity a) {
		activity = a;
	}
	
	@Override
	protected RainParser doInBackground(Void... params) {
		return new RainParser();
	}
	
    protected void onPostExecute(RainParser rainParser) {
    	activity.bar.setVisibility(View.GONE);
    	
    	String[] target = new String[] {
    			"關渡",
    			"淡水",
//    			"白河",
    	};
    	
    	activity.rainDataList = new ArrayList<RainData>();
    	activity.list.clear();
    	
    	for (int i = 0; i < target.length; i++) {
    		String s = target[i];
    		HashMap<String, String> m = new HashMap<String, String>();
    		RainData rd;
    		
	    	if (rainParser.rainDataMap.containsKey(s)) {
	    		rd = rainParser.rainDataMap.get(s);	    		
	    		m.put("Station", rd.station);
	    		m.put("Status", rd.getStatus());
	    	}
	    	else {
	    		rd = new RainData();
	    		m.put("Station", s);
	    		m.put("Status", "沒讀到資料");	    		
	    	}
	    	activity.rainDataList.add(rd);
	    	activity.list.add(m);
    	}
		
		activity.adapter.notifyDataSetChanged();
		activity.btn.setText("Reload");
		activity.btn.setEnabled(true);
    }
}