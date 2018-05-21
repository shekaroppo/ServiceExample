package shekar.com.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Random;

public class RandomNumberService extends Service {
  private static final int MAX = 100;
  private static final int MIN = 0;
  private boolean isRandomGeneratorOn;
  private int randNum;
  private IBinder binder= new RandNumBinder();

  public class RandNumBinder extends Binder {
    public RandomNumberService getService(){
      return RandomNumberService.this;
    }
  }

  @Nullable @Override public IBinder onBind(Intent intent) {
    Log.d("====", "RandomNumberService onBind");
    return binder;
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d("====", "RandomNumberService Started");
    isRandomGeneratorOn = true;
    new Thread(new Runnable() {
      @Override public void run() {
        startRandNumGen();
      }
    }).start();
    return START_STICKY;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    Log.d("====", "RandomNumberService Stopped");
    stopRanNumGen();
  }

  private void startRandNumGen() {
    while (isRandomGeneratorOn) {
      try {
          Thread.sleep(1000);
        if(isRandomGeneratorOn) {
          randNum = new Random().nextInt(MAX) + MIN;
          Log.d("====", "Random Number: " + randNum);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void stopRanNumGen() {
    isRandomGeneratorOn = false;
  }

  public int getRandNum() {
    return randNum;
  }
}