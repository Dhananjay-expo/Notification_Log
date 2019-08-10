package org.hcilab.projects.nlogx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.hcilab.projects.nlogx.ui.MainActivity;

import java.util.Locale;

public class splash extends AppCompatActivity {
    Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_splash);
        button = (Button)findViewById(R.id.clickme);
        button1=(Button)findViewById(R.id.clickme2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"English","Deutsche","हिंदी","اردو"};
        AlertDialog.Builder mBuilder =new AlertDialog.Builder(splash.this);
        mBuilder.setTitle("Choose language(Beta)");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("en");
                    recreate();
                }
                if(i==1){
                    setLocale("de");
                    recreate();
                }
                if(i==2){
                    setLocale("hi");
                    recreate();
                }
                if(i==3){
                    setLocale("ur");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog =mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale =new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration =new Configuration();
        configuration.locale =locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor =getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs =getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }
}
