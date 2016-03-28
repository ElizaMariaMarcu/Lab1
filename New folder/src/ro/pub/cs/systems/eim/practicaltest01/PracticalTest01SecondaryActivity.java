package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;

public class PracticalTest01SecondaryActivity extends Activity {
	private EditText calc;
	private Button ok, cancel;
	private Click list = new Click();
	class Click implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ok:
				setResult(RESULT_OK);
				finish();
				break;

			default:
				setResult(RESULT_CANCELED);
				finish();
				break;
			}
			
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_secondary);
		calc = (EditText)findViewById(R.id.edit1);
		ok = (Button)findViewById(R.id.ok);
		cancel = (Button)findViewById(R.id.canc);
		Intent intent = getIntent();
		if (intent.getExtras() != null) {
			String t1 = intent.getStringExtra(Constants.key1);
			String t2 = intent.getStringExtra(Constants.key2);
			int sum = Integer.parseInt(t1) + Integer.parseInt(t2);
			calc.setText(sum+"");
		}
		
		ok.setOnClickListener(list);
		cancel.setOnClickListener(list);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_secondary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
