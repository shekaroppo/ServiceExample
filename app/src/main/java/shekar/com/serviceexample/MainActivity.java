package shekar.com.serviceexample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import shekar.com.serviceexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private Intent serviceIntent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    serviceIntent=new Intent(getApplicationContext(),RandomNumberService.class);
    binding.startService.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startService(serviceIntent);
      }
    });

  }
}
