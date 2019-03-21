package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

public class NavigationActivity extends AppCompatActivity {
    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        String tab_1="Add Expense";
        String tab_2="Today's Expenditure";
        String tab_3="View Expense";
        String tab_4="Logout";
        fragment=new addexpense();
        loadFragment(fragment);



        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(tab_1, R.drawable.ic_maps_add);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(tab_2, R.drawable.ic_maps_today);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(tab_3, R.drawable.ic_maps_view);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(tab_4, R.drawable.ic_maps_profile);

//// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
//
//// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
//
//// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

//// Enable the translation of the FloatingActionButton
//        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
//
//// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
//
//// Force to tint the drawable (useful for font with icon for example)
//        bottomNavigation.setForceTint(true);
////
//        // Display color under navigation bar (API 21+)
////// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

//// Manage titles
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
//
//// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);
//
//// Set current item programmatically
        bottomNavigation.setCurrentItem(0);
//
//// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
//
//// Add or remove notification for each item
//        bottomNavigation.setNotification("1", 3);
//// OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_back))
//                .setTextColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_text))
//                .build();
//        bottomNavigation.setNotification(notification, 1);
//
//// Enable / disable item & set disable color
//        bottomNavigation.enableItemAtPosition(2);
//        bottomNavigation.disableItemAtPosition(2);
//        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));
////
//// Set listeners

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...


                switch (position) {
                    case 0:
                        fragment = new addexpense();
                        break;
//                    case 1:
//                        NotificationFragment notificationFragment = new NotificationFragment();
//                        Bundle notifArgs = new Bundle();
//                        // Put observable int (That why ObservableInteger implements Serializable)
//                        notifArgs.putSerializable(NotificationFragment.PARAM, notificationData);
//                        notificationFragment.setArguments(notifArgs);
//                        fragment = notificationFragment;
//                        break;
//                    case 2:
//                        ContactsFragment contactFragment = new ContactsFragment();
//                        Bundle contactsArgs = new Bundle();
//                        // Put observable int (That why ObservableInteger implements Serializable)
//                        contactsArgs.putSerializable(ContactsFragment.PARAM, contactData);
//                        contactFragment.setArguments(contactsArgs);
//                        fragment = contactFragment;
//                        break;
//                    case 3:
//                        fragment = new SettingsFragment();
//                        break;
                    default:
                        break;
                }
                return loadFragment(fragment);
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });

    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
