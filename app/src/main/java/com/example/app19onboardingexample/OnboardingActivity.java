package com.example.app19onboardingexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.tabs.TabLayout;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button buttonPrevious, buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Initialize ViewPager view
        viewPager = findViewById(R.id.viewPagerOnBoarding);
        // create ViewPager adapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Add All Fragments to ViewPager
        viewPagerAdapter.addFragment(new StepOneFragment());
        viewPagerAdapter.addFragment(new StepTwoFragment());
        viewPagerAdapter.addFragment(new StepThreeFragment());
        viewPagerAdapter.addFragment(new StepFourFragment());

        // Set Adapter for ViewPager
        viewPager.setAdapter(viewPagerAdapter);
        // Setup dot's indicator
        tabLayout = findViewById(R.id.tabLayoutIndicator);
        tabLayout.setupWithViewPager(viewPager);

        //bu arkadaş sadece son fragmente gelindiğini anlayıp butonu nextten finişe çeviriyor
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == viewPagerAdapter.getCount()-1) {
                    buttonNext.setText("Finish");
                }else {
                    buttonNext.setText("Next");
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPagerAdapter.getCount() == viewPager.getCurrentItem()+1) {
                    //son fragmenttayız, main activitye yönlendir.
                    SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstStartBoarding", false);
                    editor.apply();
                    Intent i = new Intent(OnboardingActivity.this, MainActivity.class);
                    startActivity(i);
                }else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    //Toast.makeText(getApplicationContext(), String.valueOf(viewPagerAdapter.getCount()) + "+" + String.valueOf(viewPager.getCurrentItem()+1), Toast.LENGTH_SHORT).show();

                }
            }
        });

        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() == 0){
                    //ilk fragmentta iken geri tuşuna basıldı, uygulamadan çıkmalıyız.
                    android.os.Process.killProcess(android.os.Process.myPid());
                }else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                }
            }
        });

    }
}