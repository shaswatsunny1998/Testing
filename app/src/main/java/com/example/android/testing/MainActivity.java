package com.example.android.testing;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.nkzawa.engineio.client.Socket;
import com.github.nkzawa.socketio.client.IO;

import org.w3c.dom.Text;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends Activity {
    private TextView toshow;
    private Button btn;
    private com.github.nkzawa.socketio.client.Socket socket;
    private  String Nickname;

    public class User
    {
        private String username;
        private String password;
        private float lat;
        private float log;

        public User(String username, String password, float lat, float log) {
            this.username = username;
            this.password = password;
            this.lat = lat;
            this.log = log;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }

        public float getLog() {
            return log;
        }

        public void setLog(float log) {
            this.log = log;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toshow=(TextView)findViewById(R.id.Show);
        btn=(Button) findViewById(R.id.start1);
    }
    public void click_btn(View v)
    {
        try{
            socket= IO.socket("http://192.168.43.23:3000/");
            socket.connect();
            socket.emit("data","Hello");
            toshow.setText(getCompleteAddressString(12.971062, 79.158467).toString());
        }
        catch (URISyntaxException e)
        {
            toshow.setText(e.getMessage());
        }
    }
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("Current loction address", "Canont get Address!");
        }
        return strAdd;
    }
}
