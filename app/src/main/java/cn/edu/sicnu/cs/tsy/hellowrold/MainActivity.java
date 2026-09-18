package cn.edu.sicnu.cs.tsy.hellowrold;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int language = 0;
    private boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView messageText = findViewById(R.id.messageText);
        Button clickButton = findViewById(R.id.clickButton);
        ImageView flagImage = findViewById(R.id.flagImage);
        Spinner languageSpinner = findViewById(R.id.languageSpinner);

        clickButton.setOnClickListener(v -> {
            clicked = true;
            updateTexts(messageText, clickButton);
        });

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                language = position;
                if (language == 1) {
                    flagImage.setImageResource(R.drawable.flagus);
                    flagImage.setContentDescription("United States flag");
                } else if (language == 2) {
                    flagImage.setImageResource(R.drawable.t01a98c31070e0f93f6);
                    flagImage.setContentDescription("Russian flag");
                } else {
                    flagImage.setImageResource(R.drawable.flagcn);
                    flagImage.setContentDescription("China flag");
                }
                updateTexts(messageText, clickButton);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Keep the current language when no option is selected.
            }
        });
    }

    private void updateTexts(TextView messageText, Button clickButton) {
        if (language == 1) {
            messageText.setText(clicked ? "I was clicked" : "Hello World");
            clickButton.setText("Click Me");
        } else if (language == 2) {
            messageText.setText(clicked ? "\u042f \u0431\u044b\u043b \u043d\u0430\u0436\u0430\u0442" : "\u041f\u0440\u0438\u0432\u0435\u0442, \u043c\u0438\u0440");
            clickButton.setText("\u041d\u0430\u0436\u043c\u0438 \u043c\u0435\u043d\u044f");
        } else {
            messageText.setText(clicked ? "\u6211\u88ab\u70b9\u51fb\u4e86" : "\u4f60\u597d\u4e16\u754c");
            clickButton.setText("\u70b9\u51fb\u6211");
        }
    }
}
