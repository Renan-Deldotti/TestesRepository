package br.com.renandeldotti.reciclerviewteste;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;
import android.widget.Toast;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fragment_settings,rootKey);
        Preference preference = findPreference(getString(R.string.size_key));
        if (preference != null) {
            preference.setOnPreferenceChangeListener(this);
        }
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        String sizeKey = getString(R.string.size_key);
        if (preference.getKey().equals(sizeKey)) {
            String stringSize = ((String) (newValue)).trim();
            if (stringSize.equals("")) stringSize = "1";
            try {
                float size = Float.parseFloat(stringSize);
                if (size > 3 || size <= 0) {
                    Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } catch (NumberFormatException nfe) {
                Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}

