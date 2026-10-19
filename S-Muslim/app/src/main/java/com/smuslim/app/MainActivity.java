package com.smuslim.app;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final int gold = Color.rgb(218, 180, 92);
    private final int dark = Color.rgb(16, 16, 19);
    private final int panel = Color.rgb(31, 29, 27);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(dark);
        getWindow().setNavigationBarColor(dark);
        getWindow().getDecorView().setLayoutDirection(ViewGroup.LAYOUT_DIRECTION_RTL);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(dark);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(28), dp(22), dp(28));
        scroll.addView(root);

        TextView mark = new TextView(this);
        mark.setText("☪");
        mark.setTextColor(gold);
        mark.setTextSize(42);
        mark.setGravity(Gravity.CENTER);
        root.addView(mark, matchWrap());

        TextView title = new TextView(this);
        title.setText("S-Muslim");
        title.setTextColor(gold);
        title.setTextSize(30);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, matchWrap());

        TextView subtitle = new TextView(this);
        subtitle.setText("رفيقك اليومي للقرآن والذكر");
        subtitle.setTextColor(Color.WHITE);
        subtitle.setTextSize(17);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, dp(8), 0, dp(24));
        root.addView(subtitle, matchWrap());

        addSection(root, "📖", "القرآن الكريم", "قراءة القرآن — سيتم تجهيز النص الموثوق للعمل دون إنترنت.");
        addSection(root, "🤲", "الأذكار والأدعية", "أذكار الصباح والمساء والأدعية اليومية.");
        addSection(root, "🎧", "التلاوات الصوتية", "إضافة تنزيل التلاوات والاستماع إليها دون إنترنت في مرحلة لاحقة.");
        addSection(root, "🌙", "وضع التركيز القرآني", "بعد إعداد الصلاحيات، يعرض آية ويبدأ التحقق الصوتي قبل رفع الحظر.");

        TextView note = new TextView(this);
        note.setText("النسخة الأولية 0.1.0 • سيتم تفعيل الميزات تدريجيًا واختبارها على جهازك");
        note.setTextColor(Color.LTGRAY);
        note.setTextSize(12);
        note.setGravity(Gravity.CENTER);
        note.setPadding(0, dp(22), 0, 0);
        root.addView(note, matchWrap());

        setContentView(scroll);
    }

    private void addSection(LinearLayout root, String icon, String heading, String description) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(18), dp(16), dp(18), dp(16));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(panel);
        bg.setCornerRadius(dp(16));
        bg.setStroke(dp(1), Color.rgb(94, 75, 42));
        card.setBackground(bg);

        TextView title = new TextView(this);
        title.setText(icon + "  " + heading);
        title.setTextColor(gold);
        title.setTextSize(19);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        card.addView(title, matchWrap());

        TextView body = new TextView(this);
        body.setText(description);
        body.setTextColor(Color.rgb(230, 228, 224));
        body.setTextSize(14);
        body.setPadding(0, dp(8), 0, 0);
        card.addView(body, matchWrap());

        LinearLayout.LayoutParams lp = matchWrap();
        lp.setMargins(0, 0, 0, dp(14));
        root.addView(card, lp);
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
