package com.ppui.calculator;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ppui.calculator.R;

public class GeneralSettingsActivity extends AppCompatActivity {

    private AppPreferences preferences;
    private Switch numberFormatterSwitch, smartCalculationSwitch, scientificResultSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferences = AppPreferences.getInstance(this);
        setTheme(Theme.getTheme(preferences.getStringPreference(AppPreferences.APP_THEME)));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String themeName = preferences.getStringPreference(AppPreferences.APP_THEME);

        TypedValue typedValue = new TypedValue();
        TypedArray a = obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimary});
        int color = a.getColor(0, 0);
        a.recycle();

        if (themeName.equals(Theme.DEFAULT)) {
            color = getResources().getColor(R.color.colorMaterialSteelGrey);
            toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        } else if (themeName.equals(Theme.MATERIAL_LIGHT)) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.gray));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        } else {
            toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        }

        //setting toolbar style manually
        toolbar.setBackgroundColor(color);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        //setting preference values
        numberFormatterSwitch.setChecked(preferences.getBooleanPreference(AppPreferences.APP_NUMBER_FORMATTER));
        smartCalculationSwitch.setChecked(preferences.getBooleanPreference(AppPreferences.APP_SMART_CALCULATIONS));
        scientificResultSwitch.setChecked(preferences.getBooleanPreference(AppPreferences.APP_SCIENTIFIC_RESULT));

        numberFormatterSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                preferences.setBooleanPreference(AppPreferences.APP_NUMBER_FORMATTER, isChecked));

        smartCalculationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                //show warning
                AlertDialog.Builder builder = new AlertDialog.Builder(GeneralSettingsActivity.this);
                builder.setTitle(getString(R.string.warning))
                        .setMessage(getString(R.string.smart_calculation_warning))
                        .setPositiveButton(getString(R.string.ok), null);
                builder.show();
            }
            preferences.setBooleanPreference(AppPreferences.APP_SMART_CALCULATIONS, isChecked);
        });

        scientificResultSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                preferences.setBooleanPreference(AppPreferences.APP_SCIENTIFIC_RESULT, isChecked));

    }

    public void smartCalClick(View view) {
        smartCalculationSwitch.setChecked(!smartCalculationSwitch.isChecked());
    }

    public void numFormatClick(View view) {
        numberFormatterSwitch.setChecked(!numberFormatterSwitch.isChecked());
    }

    public void scientificResultClick(View view) {
        scientificResultSwitch.setChecked(!scientificResultSwitch.isChecked());
    }
}
