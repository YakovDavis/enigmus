package net.yakovdavis.enigmus;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.text.*;
import android.graphics.drawable.*;
import android.graphics.*;
import android.content.*;
import android.preference.*;

public class MainActivity extends Activity 
{
	private Encrypter e;//object for encrypter
	
	private static SharedPreferences mPref;//preference holder
	
	//For static methods
	private static EditText keyET;
	private static EditText ioET;
	private static CheckBox hideKeyCB;
	private static TextView keyTV;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//Setting up variables
		e = new Encrypter();
		mPref = PreferenceManager.getDefaultSharedPreferences(this);
		
		keyET = (EditText) findViewById(R.id.editTextKey);
		ioET = (EditText) findViewById(R.id.editTextIO);
		hideKeyCB = (CheckBox) findViewById(R.id.hideCheckbox);
		keyTV = (TextView) findViewById(R.id.keyTextView);
		
		updatePrefs();//Retrieving preferences for symbol tables, fixed keys etc.
		
		//Setting up action bar to display share button and overlay
		ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));     
        actionBar.show();
    }
	
	private void updatePrefs()
	{
		e.setSymbolTable(mPref.getString("pref_symbol_table", "unicode"));
		setFixedKeyMode(mPref.getBoolean("pref_use_fixed_key", false));
	}
	
	public static void setFixedKey(String key)
	{
		keyET.setText(key);
	}
	
	public static void setFixedKeyMode(boolean b)
	{
		keyET.setEnabled(!b);
		hideKeyCB.setEnabled(!b);
		keyTV.setEnabled(!b);
		if(b)
		{
			keyET.setText(mPref.getString("pref_fixed_key", "key"));
			hideKeyCB.setChecked(true);
			keyET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}
		else
		{
			keyET.setText("");
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
	{
		//Link to xml layout for the overlay
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//Overlay handling
		switch (item.getItemId()) {
			case R.id.action_settings:
				//Open settings
				Intent settingsActivity = new Intent(this, SettingsActivity.class);
				startActivity(settingsActivity);
				return true;

			case R.id.action_exit:
				this.finish();
				return true;
			
			case R.id.action_send:
				//Sharing is done here
				if(ioET.getText().toString().equals(""))
					return true;
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, ioET.getText().toString());
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
				return true;
		}
		return false;
	}
	
	//Handling buttons next
	public void onEncryptButtonClick(View view)
		{
			if((ioET.getText().toString() != "")||(keyET.getText().toString() != ""))
			{
				ioET.setText(e.encrypt(ioET.getText().toString(), keyET.getText().toString()));
			}
		}
	
	public void onDecryptButtonClick(View view)
	{
		if((ioET.getText().toString() != "")||(keyET.getText().toString() != ""))
		{
			ioET.setText(e.decrypt(ioET.getText().toString(), keyET.getText().toString()));
		}
	}
	
	public void onHideUpdate(View view)
	{
		if(hideKeyCB.isChecked())
		{
			keyET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}
		else
		{
			keyET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
		}
	}
}
