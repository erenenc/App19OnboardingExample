package com.example.app19onboardingexample;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

// ViewPager Adapter class
class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mList = new ArrayList<>();
    private final List<String> textList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int i) {
        return mList.get(i);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
    public void addFragment(Fragment fragment) {
        mList.add(fragment);
    }

}