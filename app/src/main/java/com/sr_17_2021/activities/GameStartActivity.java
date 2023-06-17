package com.sr_17_2021.activities;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.sr_17_2021.R;
import com.sr_17_2021.fragments.AsocijacijeFragment;
import com.sr_17_2021.fragments.KorakPoKorakFragment;

public class GameStartActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        getSupportFragmentManager().beginTransaction().add(R.id.gameContainer, new AsocijacijeFragment()).commit();
    }
    public void showAsocijacije(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, new AsocijacijeFragment()).commit();
    }
}