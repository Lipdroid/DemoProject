package lipdroid.demoproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lipdroid.demoproject.Adapters.Holders.SCListAppHolder;
import lipdroid.demoproject.Adapters.SCListAppAdapter;
import lipdroid.demoproject.library.HorizontalListView;
import lipdroid.demoproject.library.SCMultipleScreen;

public class MainActivity extends AppCompatActivity {
    private HorizontalListView mHorizontalListView = null;
    private SCListAppAdapter mListAppAdapter = null;
    private ArrayList<Integer> mListApp = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHorizontalListView = (HorizontalListView) findViewById(R.id.scmain_lv_list_app);
        mListApp.add(R.drawable.coins);
        mListApp.add(R.drawable.chart);
        mListApp.add(R.drawable.console);
        mListApp.add(R.drawable.email);
        mListApp.add(R.drawable.marker);
        mListApp.add(R.drawable.watch);
        mListApp.add(R.drawable.settings);
        mListApp.add(R.drawable.phone);
        mListAppAdapter = new SCListAppAdapter(this, mListApp);
        mHorizontalListView.setAdapter(mListAppAdapter);

        new SCMultipleScreen(this);
        SCMultipleScreen.resizeAllView(this);
    }



}
