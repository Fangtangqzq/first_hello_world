package cn.edu.sicnu.cs.tsy.hellowrold;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int language = 0;
    private boolean clicked = false;
    private ImageView flagImage;
    private TextView messageText;
    private Button clickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.WHITE);
        root.setPadding(dp(16), dp(16), dp(16), dp(16));

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER_HORIZONTAL);

        flagImage = new ImageView(this);
        flagImage.setScaleType(ImageView.ScaleType.FIT_XY);
        flagImage.setImageResource(R.drawable.flagcn);
        flagImage.setContentDescription("China flag");
        content.addView(flagImage, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(220)));

        messageText = new TextView(this);
        messageText.setTextColor(Color.rgb(85, 85, 85));
        messageText.setTextSize(20);
        messageText.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams messageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        messageParams.topMargin = dp(32);
        content.addView(messageText, messageParams);

        clickButton = new Button(this);
        clickButton.setAllCaps(false);
        clickButton.setTextColor(Color.WHITE);
        clickButton.setTextSize(18);
        clickButton.setMinWidth(dp(160));
        clickButton.setMinimumHeight(dp(56));
        clickButton.setPadding(dp(32), 0, dp(32), 0);
        clickButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(126, 87, 194)));
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, dp(56));
        buttonParams.topMargin = dp(48);
        content.addView(clickButton, buttonParams);

        FrameLayout.LayoutParams contentParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        root.addView(content, contentParams);

        Spinner languageSpinner = new Spinner(this);
        String[] languages = {
                "\u4e2d\u6587",
                "English",
                "\u0420\u0443\u0441\u0441\u043a\u0438\u0439"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setContentDescription("Language selector");

        FrameLayout.LayoutParams spinnerParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.START | Gravity.BOTTOM);
        root.addView(languageSpinner, spinnerParams);

        clickButton.setOnClickListener(v -> {
            clicked = true;
            updateTexts();
        });

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                language = position;
                updateLanguageImage();
                updateTexts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Keep the current language when no option is selected.
            }
        });

        setContentView(root);
        updateLanguageImage();
        updateTexts();
    }

    private void updateLanguageImage() {
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
    }

    private void updateTexts() {
        if (language == 1) {
            messageText.setText(clicked ? "I was clicked" : "Hello World");
            clickButton.setText("Click Me");
        } else if (language == 2) {
            messageText.setText(clicked
                    ? "\u042f \u0431\u044b\u043b \u043d\u0430\u0436\u0430\u0442"
                    : "\u041f\u0440\u0438\u0432\u0435\u0442, \u043c\u0438\u0440");
            clickButton.setText("\u041d\u0430\u0436\u043c\u0438 \u043c\u0435\u043d\u044f");
        } else {
            messageText.setText(clicked ? "\u6211\u88ab\u70b9\u51fb\u4e86" : "\u4f60\u597d\u4e16\u754c");
            clickButton.setText("\u70b9\u51fb\u6211");
        }
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
