package shekar.com.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class RandomNumberService extends Service {
  @Nullable @Override public IBinder onBind(Intent intent) {
    return null;
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d("====","TestService Started");
    stopSelf();
    return super.onStartCommand(intent, flags, startId);
  }

  @Override public void onDestroy() {
    Log.d("====","TestService Stoped");
    super.onDestroy();
  }
}