package shekar.com.serviceexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import shekar.com.serviceexample.databinding.ActivityMainBinding;

public class BoundServiceActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private Intent serviceIntent;
  private ServiceConnection serviceConnection;
  private RandomNumberService randomNumberService;
  private boolean isServiceBound;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    serviceIntent=new Intent(getApplicationContext(),RandomNumberService.class);
    binding.startService.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startService(serviceIntent);
      }
    });
    binding.stopService.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        stopService(serviceIntent);
      }
    });
    binding.bindService.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        bindService();
      }
    });
    binding.unbindService.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        unbindService();
      }
    });
    binding.getRandomNumber.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        setRandomNumber();
      }
    });

  }

  private void unbindService() {
    Log.d("====", "Service Unbind");
    if(isServiceBound){
      unbindService(serviceConnection);
      isServiceBound=false;
    }
  }

  private void bindService() {
    Log.d("====", "Service bind");
    if(serviceConnection==null){
      serviceConnection=new ServiceConnection() {
        @Override public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
          RandomNumberService.RandNumBinder binderClass=(RandomNumberService.RandNumBinder)iBinder;
          randomNumberService=binderClass.getService();
          isServiceBound=true;
        }

        @Override public void onServiceDisconnected(ComponentName componentName) {
          isServiceBound=false;
        }
      };
    }
    bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
  }
  private void setRandomNumber(){
    if(isServiceBound){
      binding.randomNumber.setText("Random number: "+randomNumberService.getRandNum());
    }else{
      binding.randomNumber.setText("Service not bound");
    }
  }
}
