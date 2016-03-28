package ro.pub.cs.systems.eim.practicaltest01;

import java.util.Random;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import ro.pub.cs.systems.eim.practicaltest01.Constants;

public class StartedService extends Service {

	public class ProcessingThread extends Thread {
		 
		  private Context context;
		 private int ma;
		 private int mb;
		 
		  public ProcessingThread(Context context, int ma, int mb) {
		    this.context = context;
		    this.ma = ma;
		    this.mb = mb;
		  }
		 
		  @Override
		  public void run() {
		    while(true){ 
		      sendMessage(ma, mb);
		      sleep();

		    }
		  }
		 
		  private void sleep() {
		    try {
		      Thread.sleep(Constants.SLEEP_TIME);
		    } catch (InterruptedException interruptedException) {
		      interruptedException.printStackTrace();
		    }
		  }
		 
		  private void sendMessage(int ma, int mb) {
		    Intent intent = new Intent();
		    Random randX = new Random();

		         intent.setAction(Constants.actions[randX.nextInt(Constants.actions.length)]);
		         intent.putExtra(Constants.DATA1, ma);
		         intent.putExtra(Constants.DATA2, mb);
		         Log.d("dg", "s-a trimis serviciul" + ma +" si " + mb);
		    context.sendBroadcast(intent);
		  }


		}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	  @Override
	  public int onStartCommand(Intent intent, 
	                            int flags,
	                            int startId) {

		  int nr1 = Integer.parseInt(intent.getStringExtra(Constants.key1));
		  int nr2 = Integer.parseInt(intent.getStringExtra(Constants.key2));
		  int ma = (nr1+nr2)/2;
		  int mb = (int) Math.sqrt(nr1*nr2);
		  Log.d("dg", "a porni serviciul");
		  ProcessingThread th = new ProcessingThread(this, ma, mb);
		  th.start();
		  return Service.START_REDELIVER_INTENT;
	  }
}
