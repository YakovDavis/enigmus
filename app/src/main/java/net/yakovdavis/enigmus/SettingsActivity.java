package net.yakovdavis.enigmus;

import android.preference.*;
import android.os.*;
import android.content.*;
import android.app.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.content.*;
import android.text.*;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener 
{
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
		ListPreference sTable = (ListPreference)findPreference("pref_symbol_table");
		sTable.setSummary(sTable.getEntry());
		ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));     
        actionBar.show();
    }
	
	@Override
	protected void onResume()
	{
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
        if (key.equals("pref_symbol_table"))
		{
            ListPreference tablePref = (ListPreference)findPreference(key);
			tablePref.setSummary(tablePref.getEntry());
            Encrypter.setSymbolTable(tablePref.getValue());
        }
		if (key.equals("pref_use_fixed_key"))
		{
			CheckBoxPreference uFKey = (CheckBoxPreference)findPreference(key);
			MainActivity.setFixedKeyMode(uFKey.isChecked());
		}
		if(key.equals("pref_fixed_key"))
		{
			EditTextPreference fKey = (EditTextPreference) findPreference(key);
			MainActivity.setFixedKey(fKey.getText());
		}
	}
}
