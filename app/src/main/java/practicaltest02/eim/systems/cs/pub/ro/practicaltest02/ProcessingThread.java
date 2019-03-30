package practicaltest02.eim.systems.cs.pub.ro.practicaltest02;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;
    private String sablon;
    private String[] sablonArray;
    private Random random = new Random();


    public ProcessingThread(Context context, String sablon) {
        this.context = context;
        this.sablon = sablon;
        this.sablonArray = this.sablon.split(", ");
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            for(int i = 1; i < sablonArray.length; i++) {
                sendMessage(sablonArray[i]);
            }
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

    private void sendMessage(String message) {
            Intent intent = new Intent();
            intent.setAction("action");
            intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + message);
        context.sendBroadcast(intent);
    }

}