package com.ascra.rebelfoods

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AddressActivity : AppCompatActivity(), OnMapReadyCallback {

    private var lat: Double = 0.toDouble()
    private var lng: Double = 0.toDouble()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val place = findViewById<TextView>(R.id.place)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val mapFragment = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        mapFragment.getMapAsync(this)

        val placeIntent = intent
        val location = placeIntent.getStringExtra(ApplicationConstant.PLACE)
        lat = placeIntent.getDoubleExtra(ApplicationConstant.LATITUDE, 0.0)
        lng = placeIntent.getDoubleExtra(ApplicationConstant.LONGITUDE, 0.0)

        if (!TextUtils.isEmpty(location)) {
            place!!.text = location
            place.visibility = View.VISIBLE
        } else {
            place!!.visibility = View.GONE
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location1 = LatLng(lat, lng)
        googleMap.addMarker(MarkerOptions().position(location1))
        val cameraPosition = CameraPosition.Builder()
                .target(location1)
                .zoom(20f)
                .build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2500, null)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }
}
