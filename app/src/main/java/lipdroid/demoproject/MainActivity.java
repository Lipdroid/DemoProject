package lipdroid.demoproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import lipdroid.demoproject.Adapters.Holders.SCListAppHolder;
import lipdroid.demoproject.Adapters.SCListAppAdapter;
import lipdroid.demoproject.library.HorizontalListView;
import lipdroid.demoproject.library.SCMultipleScreen;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private HorizontalListView mHorizontalListView = null;
    private SCListAppAdapter mListAppAdapter = null;
    private ArrayList<Integer> mListApp = new ArrayList<Integer>();
    GoogleMap mGoogleMap;
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


        // Getting Google Play availability status
        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getBaseContext());

        if (status != ConnectionResult.SUCCESS) { // Google Play Services are
            // not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
                    requestCode);
            dialog.show();

        } else { // Google Play Services are available

            // Getting reference to the SupportMapFragment
            SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);

            // Getting Google Map
            mGoogleMap = fragment.getMap();

            // Enabling MyLocation in Google Map
            mGoogleMap.setMyLocationEnabled(true);


            /////----------------------------------Zooming camera to position user-----------------

            Location location = getLastKnownLocation();
            if (location != null) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            }

         /////----------------------------------Zooming camera to position user-----------------

        }

        new SCMultipleScreen(this);
        SCMultipleScreen.resizeAllView(this);
    }

    private Location getLastKnownLocation() {
        LocationManager mlocationManager = (LocationManager) this.getApplicationContext()
                .getSystemService(this.LOCATION_SERVICE);
        List<String> providers = mlocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mlocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
