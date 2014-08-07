package com.unearthedgems.communityapp;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;


public class GpsClass extends Activity{
	// Acquire a reference to the system Location Manager
private	LocationManager locationManager;
public double lontitude;
public double latitude ;
public long timestamp;
public LocationListener locationListener;
Context mContext;
private boolean isConnected =false;
//constructor
public GpsClass(Context mContext) {
	
	 this.mContext = mContext;
	 


  
  locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

	// Define a listener that responds to location updates
	
	locationListener= new LocationListener() {
	    public void onLocationChanged(Location location) {
	    	
	      // Called when a new location is found by the network location provider.
	    //  makeUseOfNewLocation(location);
	    	
	    	lontitude= location.getLongitude();
	    	latitude=location.getLatitude();
	    	timestamp = location.getTime();
	    	Log.d("Lsietener", lontitude+"");
	    	
	    	
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {}

	    public void onProviderEnabled(String provider) {}

	    public void onProviderDisabled(String provider) {}
	  };
	   // Register the listener with the Location Manager to receive location updates
	  registerListener();
}	  
	
	  public void registerListener(){
		  locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, locationListener);
		  Log.d("registerListener", lontitude+"");
	  }

	  public void checkConnectivity(){
		if (locationManager!=null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)&& locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
			setConnected(true);
			Log.d("check conectivity", lontitude+"");
			
		}
		else
		{
			setConnected(false);
						
					
			
		}
	  }
	  public void showDialog(){
			 Builder dialog = new AlertDialog.Builder(mContext);
			 
		        dialog.setMessage(mContext.getResources().getString(R.string.gps_network_not_enabled));
		        dialog.setPositiveButton(mContext.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {

		            @Override
		            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
		                // TODO Auto-generated method stub
		                Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
		                mContext.startActivity(myIntent);
		                //get gps
		            }

				
		        });
		        dialog.setNegativeButton(mContext.getString(R.string.cancel), new DialogInterface.OnClickListener() {

		            @Override
		            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
		                // TODO Auto-generated method stub

		            }
		        });
		        dialog.show();
					
	  }
	 public double getCods(int x){

		  if (x==1){
			  //1 == longitude values
			  
			  
			 return lontitude; 
		  }
		  else{
			  return latitude;
		  }
		
	 }
	  public double getCoordinates(int x){
		  
		  checkConnectivity();
	
		  if (x==1){
			  //1 == longitude values
			  
			  
			 return lontitude; 
		  }
		  else{
			  return latitude;
		  }
		
		  
	  }
	public void stopListening(){
		locationManager.removeUpdates(locationListener);
	}
	
	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

}
