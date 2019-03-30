package practicaltest02.eim.systems.cs.pub.ro.practicaltest02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

    public class PracticalTest_Service extends Service {

        private ProcessingThread processingThread = null;


        @Override
        public void onCreate() {
            super.onCreate();
            Log.d("SERVICE", "onCreate() method was invoked");
        }

        @Override
        public IBinder onBind(Intent intent) {
            Log.d("SERVICE", "onBind() method was invoked");
            return null;
        }

        @Override
        public boolean onUnbind(Intent intent) {
            Log.d("SERVICE", "onUnbind() method was invoked");
            return true;
        }

        @Override
        public void onRebind(Intent intent) {
            Log.d("SERVICE", "onRebind() method was invoked");
        }

        @Override
        public void onDestroy() {
            Log.d("SERVICE", "onDestroy() method was invoked");
            processingThread.stopThread();
        }



        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            String sablon = intent.getStringExtra("sablon");
            processingThread = new ProcessingThread(this, sablon);
            processingThread.start();
            return Service.START_REDELIVER_INTENT;
        }


    }