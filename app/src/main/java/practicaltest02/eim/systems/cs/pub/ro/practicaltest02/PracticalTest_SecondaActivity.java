package practicaltest02.eim.systems.cs.pub.ro.practicaltest02;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest_SecondaActivity extends AppCompatActivity {

    Button verifyButton;
    Button cancelButton;
    EditText hitsText;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test__seconda);

        verifyButton = findViewById(R.id.verifyButton);
        cancelButton = findViewById(R.id.cancelButton);
        hitsText = findViewById(R.id.hitsText);

        activity = this;

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(PracticalTest_SecondaActivity.this,PracticalalTest_MainActivity.class);
                newIntent.putExtra("response", "VERIFY");
                activity.startActivity(newIntent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(PracticalTest_SecondaActivity.this,PracticalalTest_MainActivity.class);
                newIntent.putExtra("response", "CANCEL");
                activity.startActivity(newIntent);
            }
        });

        Intent intent = this.getIntent();
        String value = intent.getStringExtra("hits").toString();
        hitsText.setText(value);
    }
}
