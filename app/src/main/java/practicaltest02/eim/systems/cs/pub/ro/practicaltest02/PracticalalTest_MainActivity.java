package practicaltest02.eim.systems.cs.pub.ro.practicaltest02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalalTest_MainActivity extends AppCompatActivity {

    Button changeActivity;
    Button center,topRight,topLeft,bottomLeft,bottomRight;
    EditText sablon;
    boolean serviceStarted = false;
    Integer hits = 0;
    final Integer NR_HITS = 10;
    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicalal_test__main);

        sablon = findViewById(R.id.sablon);
        topLeft = findViewById(R.id.leftTop);
        topRight = findViewById(R.id.rightTop);
        bottomLeft = findViewById(R.id.leftBottom);
        bottomRight = findViewById(R.id.rightBottom);
        center = findViewById(R.id.center);
        changeActivity = findViewById(R.id.changeActivity);
        //Toast.makeText(this, "Number of button hits:" + String.valueOf(hits), Toast.LENGTH_LONG).show();
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sablonText = sablon.getText().toString().trim();
                sablonText = sablonText + ", " + topLeft.getText().toString();
                sablon.setText(sablonText);
                hits++;
            }
        });

        intentFilter.addAction("action");


        topRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sablonText = sablon.getText().toString().trim();
                sablonText = sablonText + ", " + topRight.getText().toString();
                sablon.setText(sablonText);
            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sablonText = sablon.getText().toString().trim();
                sablonText = sablonText + ", " + center.getText().toString();
                sablon.setText(sablonText);
                hits++;

            }
        });


        bottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sablonText = sablon.getText().toString().trim();
                sablonText = sablonText + ", " + bottomRight.getText().toString();
                sablon.setText(sablonText);
                hits++;

            }
        });

        bottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sablonText = sablon.getText().toString().trim();
                sablonText = sablonText + ", " + bottomLeft.getText().toString();
                sablon.setText(sablonText);
                hits++;

            }
        });

        changeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticalalTest_MainActivity.this, PracticalTest_SecondaActivity.class);
                intent.putExtra("hits", String.valueOf(hits));
                PracticalalTest_MainActivity.this.startActivity(intent);
            }
        });

        sablon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(hits > NR_HITS) {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest_Service.class);
                    intent.putExtra("sablon", sablon.getText().toString().trim());
                    PracticalalTest_MainActivity.this.startService(intent);
                    serviceStarted = true;
                    Toast.makeText(PracticalalTest_MainActivity.this, "SERVICE STARTED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(this.getIntent() != null) {
            String response = this.getIntent().getStringExtra("response");
            if(response != null) {
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
                hits = 0;
                sablon.setText("");
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("WOW", "savedInstanceState");
        savedInstanceState.putString("hits", String.valueOf(hits));
        Log.d("WOW", savedInstanceState.get("hits").toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        Log.d("WOW", "onRestoreInstanceState");

        if (savedInstanceState.containsKey("hits")) {
            hits = Integer.valueOf(savedInstanceState.getString("hits").toString());
            Toast.makeText(this, "Number of button hits:" + String.valueOf(hits), Toast.LENGTH_LONG).show();
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
