package com.example.vuelveaqui;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.vuelveaqui.databinding.ActivityMapsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private LatLng currentLocation;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Boolean menuButton = false;
    private boolean locationPermissionGranted;
    private Location lastKnownLocation;
    private static final int DEFAULT_ZOOM = 15;

    private static final int ALL_PERMISSIONS_RESULT = 1100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FloatingActionButton menu = findViewById(R.id.idMenuFloating);
        FloatingActionButton settings = findViewById(R.id.idSettingsFloating);
        FloatingActionButton search = findViewById(R.id.idSearchFloating);
        FloatingActionButton allPlaces = findViewById(R.id.idAllPlacesFloating);
        FloatingActionButton addPlace = findViewById(R.id.idAddPlacesFloating);
        settings.hide();
        search.hide();
        allPlaces.hide();
        addPlace.hide();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuButton) {
                    menu.setImageResource(R.drawable.outline_menu_black_18);
                    settings.hide();
                    search.hide();
                    allPlaces.hide();
                    addPlace.hide();
                    menuButton = false;
                } else {
                    menu.setImageResource(R.drawable.outline_close_black_18);
                    settings.show();
                    search.show();
                    allPlaces.show();
                    addPlace.show();
                    menuButton = true;
                }
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("New Place Add"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        //getDeviceLocation();
    }

    /**
     * Prompts the user for permission to use the device location.
     */
    // [START maps_current_place_location_permission]
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    ALL_PERMISSIONS_RESULT);
        }
    }
    // [END maps_current_place_location_permission]

    /**
     * Handles the result of the request for location permissions.
     */
    // [START maps_current_place_on_request_permissions_result]
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationPermissionGranted = false;
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }
    // [END maps_current_place_on_request_permissions_result]

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    // [START maps_current_place_update_location_ui]
    @SuppressLint("ServiceCast")
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                currentLocation = getLocation();
                //System.out.println("latitude: "+currentLocation.latitude + "Longitude: "+currentLocation.longitude);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
                updateLocationUI();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    // [END maps_current_place_update_location_ui]

    private LatLng getLocation(){
        LatLng latLng = null;
        if (locationPermissionGranted) {
            @SuppressLint("MissingPermission")
            Location locationManager = ((LocationManager) getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
            latLng = new LatLng(locationManager.getLatitude(),locationManager.getLongitude());
        }
        return latLng;
    }


}