package com.seiffamily;

import android.app.Activity;
import android.media.projection.MediaProjectionManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int REQUEST_SCREEN_CAPTURE = 4102;
    private TextView status;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(28, 48, 28, 28);
        root.setBackgroundColor(Color.rgb(246, 248, 252));

        TextView title = new TextView(this);
        title.setText("SEIF Family");
        title.setTextSize(28);
        title.setTextColor(Color.rgb(20, 45, 80));
        title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(-1, -2));

        TextView explanation = new TextView(this);
        explanation.setText("مشاركة الشاشة بموافقتك\n\nعند المتابعة، سيعرض Android نافذة نظام توضّح مشاركة الشاشة. لا تبدأ المشاركة إلا إذا فهمت ما سيُعرض، ويمكنك الإلغاء.");
        explanation.setTextSize(18);
        explanation.setTextColor(Color.DKGRAY);
        explanation.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams ep = new LinearLayout.LayoutParams(-1, -2);
        ep.topMargin = 32;
        root.addView(explanation, ep);

        Button approve = new Button(this);
        approve.setText("متابعة إلى موافقة Android");
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, -2);
        bp.topMargin = 28;
        root.addView(approve, bp);
        approve.setOnClickListener(v -> {
            MediaProjectionManager manager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            startActivityForResult(manager.createScreenCaptureIntent(), REQUEST_SCREEN_CAPTURE);
        });

        Button cancel = new Button(this);
        cancel.setText("إلغاء");
        root.addView(cancel, new LinearLayout.LayoutParams(-1, -2));
        cancel.setOnClickListener(v -> finish());

        status = new TextView(this);
        status.setText("الحالة: لم تبدأ مشاركة الشاشة.");
        status.setTextSize(15);
        status.setTextColor(Color.rgb(80, 80, 80));
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, -2);
        sp.topMargin = 24;
        root.addView(status, sp);
        setContentView(root);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SCREEN_CAPTURE) {
            if (resultCode == RESULT_OK) {
                status.setText("تمت الموافقة من نافذة Android. هذه النسخة الأولية لا ترسل الشاشة إلى الكمبيوتر بعد.");
            } else {
                status.setText("أُلغيت الموافقة. لم تبدأ مشاركة الشاشة.");
            }
        }
    }
}
