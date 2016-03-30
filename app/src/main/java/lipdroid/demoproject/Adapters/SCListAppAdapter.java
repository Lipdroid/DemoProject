package lipdroid.demoproject.Adapters;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
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
import lipdroid.demoproject.MainActivity;
import lipdroid.demoproject.R;
import lipdroid.demoproject.library.CustomizeDialogOk;
import lipdroid.demoproject.library.SCMultipleScreen;


/**
 * Created by VNCCO on 6/30/2015.
 */
public class SCListAppAdapter extends BaseAdapter {
    private SCListAppHolder mListAppHolder = null;
    private Activity mActivity = null;
    private ArrayList<Integer> mListApp = null;
    String popUpContents[];
    PopupWindow popupWindowDogs;
    int item_pressed;
    CustomizeDialogOk customizeDialogok = null;


    public SCListAppAdapter(Activity activity, ArrayList<Integer> listApp) {
        this.mActivity = activity;
        this.mListApp = listApp;
    }

    @Override
    public int getCount() {
        return mListApp.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = mActivity.getLayoutInflater().inflate(R.layout.item_bottom_list, null);
        mListAppHolder = new SCListAppHolder();
        mListAppHolder.imgApp = (ImageView) convertView.findViewById(R.id.item_app_img);
        mListAppHolder.tvName = (TextView) convertView.findViewById(R.id.item_app_name);
        //  mListAppHolder.vPaddingRight = convertView.findViewById(R.id.v_padding_right);
        //mListAppHolder.vPaddingLeft = convertView.findViewById(R.id.v_padding_left);


        new SCMultipleScreen(mActivity);
        SCMultipleScreen.resizeAllView((ViewGroup) convertView);

        // convertView.setTag(mListAppHolder);


        // mListAppHolder.resetView();


        if (position == 0) {
            // mListAppHolder.vPaddingLeft.setVisibility(View.VISIBLE);
        }


        /*
         * initialize pop up window
         */

        mListAppHolder.imgApp.setImageResource(mListApp.get(position));
        mListAppHolder.imgApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowDogs = popupWindowDogs(position);
                // popupWindowDogs.showAsDropDown(v, 0, -125);
                item_pressed = position;
                popupWindowDogs.showAtLocation(v, Gravity.BOTTOM, -5, v.getHeight());
            }
        });
        return convertView;
    }


    public PopupWindow popupWindowDogs(int position) {

        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(mActivity);

        // the drop down list is a list view
        ListView listViewDogs = new ListView(mActivity);
        listViewDogs.setVerticalScrollBarEnabled(false);
        listViewDogs.setHorizontalScrollBarEnabled(false);
        ArrayList<Integer> mListApp = new ArrayList<Integer>();
        ArrayList<String> mListApp_Str = new ArrayList<String>();
        if (position == 2) {

            mListApp.add(R.drawable.coins);
            mListApp.add(R.drawable.console);
            mListApp.add(R.drawable.settings);

            mListApp_Str.add("Coins");
            mListApp_Str.add("Console");
            mListApp_Str.add("Setting");
        } else if (position == 0) {

            mListApp.add(R.drawable.coins);
            mListApp.add(R.drawable.console);

            mListApp_Str.add("Coins");
            mListApp_Str.add("Console");
        } else {

            mListApp.add(R.drawable.coins);
            mListApp.add(R.drawable.console);
            mListApp.add(R.drawable.chart);
            mListApp.add(R.drawable.settings);
            mListApp_Str.add("Coins");
            mListApp_Str.add("Console");
            mListApp_Str.add("Chart");
            mListApp_Str.add("Setting");
        }
        SubListAdapter mListAppAdapter = new SubListAdapter(mActivity, mListApp, mListApp_Str);
        listViewDogs.setAdapter(mListAppAdapter);

        // set our adapter and pass our pop up window contents
        // listViewDogs.setAdapter(dogsAdapter(popUpContents));

        // set the item click listener
        listViewDogs.setOnItemClickListener(new DogsDropdownOnItemClickListener());

        // some other visual settings
        Drawable d = new ColorDrawable(Color.WHITE);

//        d.setAlpha(130);


        popupWindow.setBackgroundDrawable(d);


        popupWindow.setFocusable(true);
        popupWindow.setWidth(500);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(listViewDogs);

        return popupWindow;
    }


    /*
    * adapter where the list values will be set
    */
    private ArrayAdapter<String> dogsAdapter(String dogsArray[]) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, dogsArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list

                String text = getItem(position);

                // visual settings for the list item
                TextView listItem = new TextView(mActivity);

                listItem.setText(text);
                listItem.setTag(position);
                listItem.setTextSize(22);
                listItem.setPadding(10, 10, 10, 10);
                listItem.setTextColor(Color.WHITE);

                return listItem;
            }
        };

        return adapter;
    }

    public class DogsDropdownOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

            // get the context and main activity to access variables
            Context mContext = v.getContext();
            MainActivity mainActivity = ((MainActivity) mContext);

            // add some animation when a list item was clicked
            Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
            fadeInAnimation.setDuration(10);
            v.startAnimation(fadeInAnimation);

            // dismiss the pop up
            popupWindowDogs.dismiss();

            // get the text and set it as the button text


            //dialog
            customizeDialogok = new CustomizeDialogOk(mContext,
                    m_OnSuccessOkDialogHandler);
            customizeDialogok.setTitle("Response");
            customizeDialogok.setMessage("You Have pressed bottom list item" + (item_pressed+1) + "  and sublist item" + (arg2+1));
            customizeDialogok.show();

        }
        private CustomizeDialogOk.OnSuccessOkDialogHandler m_OnSuccessOkDialogHandler = new CustomizeDialogOk.OnSuccessOkDialogHandler() {

            public void onSuccessMessage(Boolean iSSuccess, long id) {
                // TODO Auto-generated method stub
                Log.e("finish","finish");
            }
        };

    }
}
