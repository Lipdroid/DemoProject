package lipdroid.demoproject.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import lipdroid.demoproject.Adapters.Holders.SCListAppHolder;
import lipdroid.demoproject.R;
import lipdroid.demoproject.library.SCMultipleScreen;

public class SubListAdapter extends BaseAdapter {
    private SCListAppHolder mListAppHolder = null;
    private Activity mActivity = null;
    private ArrayList<Integer> mListApp = null;
    private ArrayList<Integer> mListApp_sub = new ArrayList<Integer>();
    private ArrayList<String> mListApp_sub_str = new ArrayList<String>();
//    private DisplayImageOptions mImageLoaderOpts = new DisplayImageOptions.Builder()
//            .showImageOnLoading(R.drawable.common_loading_app_icon)
//            .showImageForEmptyUri(R.drawable.common_loading_app_icon)
//            .showImageOnFail(R.drawable.common_loading_app_icon)
//            .bitmapConfig(Bitmap.Config.RGB_565)
//            .cacheInMemory(true).cacheOnDisk(false).considerExifParams(true)
//            .imageScaleType(ImageScaleType.EXACTLY)
//            .resetViewBeforeLoading(true)
//            .build();

    public SubListAdapter(Activity activity, ArrayList<Integer> listApp, ArrayList<String> mListApp_Str) {
        this.mActivity = activity;
        this.mListApp = listApp;
        this.mListApp_sub_str = mListApp_Str;
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
        if (convertView == null) {
            convertView = mActivity.getLayoutInflater().inflate(R.layout.item_sublist, null);
            mListAppHolder = new SCListAppHolder();
            mListAppHolder.imgApp = (ImageView) convertView.findViewById(R.id.item_app_img);
            mListAppHolder.tvName = (TextView) convertView.findViewById(R.id.item_app_name);
            mListAppHolder.vPaddingRight = convertView.findViewById(R.id.v_padding_right);
            mListAppHolder.vPaddingLeft = convertView.findViewById(R.id.v_padding_left);
            new SCMultipleScreen(mActivity);
            SCMultipleScreen.resizeAllView((ViewGroup) convertView);

            convertView.setTag(mListAppHolder);
        } else {
            mListAppHolder = (SCListAppHolder) convertView.getTag();
        }

        mListAppHolder.resetView();


//        if (position == 0) {
//            mListAppHolder.vPaddingLeft.setVisibility(View.VISIBLE);
//        }


        mListAppHolder.imgApp.setImageResource(mListApp.get(position));
        mListAppHolder.tvName .setText(mListApp_sub_str.get(position));

        return convertView;
    }

}
