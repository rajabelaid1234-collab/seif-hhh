package com.smuslim.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final int gold = Color.rgb(218, 180, 92);
    private final int dark = Color.rgb(16, 16, 19);
    private final int panel = Color.rgb(31, 29, 27);
    private LinearLayout root;
    private int currentTab = 0;
    private String city = "اختر مدينتك";
    private int tasbeehCount = 0;
    private int tasbeehGoal = 33;
    private boolean adhanEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(dark);
        getWindow().setNavigationBarColor(dark);
        getWindow().getDecorView().setLayoutDirection(ViewGroup.LAYOUT_DIRECTION_RTL);
        render();
    }

    private void render() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(dark);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(18), dp(22), dp(18), dp(14));
        scroll.addView(page);

        root = page;
        addHeader(page);
        if (currentTab == 0) showHome(page);
        else if (currentTab == 1) showQuran(page);
        else if (currentTab == 2) showAdhkar(page);
        else if (currentTab == 3) showPrayer(page);
        else showLibrary(page);
        addNavigation(page);
        setContentView(scroll);
    }

    private void addHeader(LinearLayout page) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setOrientation(LinearLayout.HORIZONTAL);
        TextView title = text("☪  S-Muslim", 25, gold, true);
        row.addView(title, new LinearLayout.LayoutParams(0, dp(48), 1));
        Button settings = smallButton("⚙ الإعدادات");
        settings.setOnClickListener(v -> showSettings());
        row.addView(settings);
        page.addView(row);
        TextView subtitle = text("رفيقك اليومي للقرآن والذكر", 14, Color.LTGRAY, false);
        subtitle.setPadding(0, 0, 0, dp(14));
        page.addView(subtitle);
    }

    private void showHome(LinearLayout page) {
        card(page, "📍 الموقع الجغرافي", city + " • اضغط لاختيار المدينة", () -> chooseCity());
        card(page, "🗓 التاريخ والمناسبة", "يُعرض التاريخ الصحيح بعد إضافة مصدر تقويم موثوق.", () ->
            toast("سيُضاف التقويم الهجري والمناسبات في مرحلة لاحقة"));
        card(page, "🕌 الصلاة القادمة", "وقت الصلاة والعدّ التنازلي يظهران بعد تفعيل حساب المواقيت.", () -> {
            currentTab = 3; render();
        });
        card(page, "🔊 الأذان", adhanEnabled ? "التنبيه الصوتي: مفعّل في إعداد الواجهة" : "التنبيه الصوتي: مكتوم", () -> {
            adhanEnabled = !adhanEnabled; render();
        });
        card(page, "🕋 القبلة والمواقيت", "مواقيت الصلاة • اتجاه القبلة • سجل الصلاة", () -> {
            currentTab = 3; render();
        });
        card(page, "📖 المصحف الشريف", "قراءة • استماع • فواصل • بحث", () -> {
            currentTab = 1; render();
        });
        card(page, "📿 الأذكار والمسبحة", "أذكار اليوم ومسبحة إلكترونية", () -> {
            currentTab = 2; render();
        });
        card(page, "📚 المكتبة والتعلم", "الحديث • السيرة • قصص الأنبياء • التعلم", () -> {
            currentTab = 4; render();
        });
        card(page, "🎯 وضع التركيز القرآني", "ميزة مستقبلية تتطلب إعداد صلاحيات Android والتحقق الصوتي.", () ->
            toast("وضع التركيز القرآني لم يُفعّل بعد"));
    }

    private void showQuran(LinearLayout page) {
        heading(page, "📖 المصحف الشريف");
        card(page, "📜 القراءة العادية", "قارئ القرآن بالرسم العثماني — يحتاج إلى إضافة نص موثوق.", () ->
            toast("قارئ القرآن سيُفعّل بعد إضافة النص القرآني الموثوق"));
        card(page, "🎧 الاستماع والتلاوة", "إدارة القرّاء وتنزيل الصوت للاستماع دون إنترنت.", () ->
            toast("قائمة التلاوات غير مفعّلة بعد"));
        card(page, "🔖 الفواصل والمفضلة", "حفظ موضع القراءة والرجوع إليه.", () ->
            toast("سيُضاف حفظ الفواصل مع قارئ القرآن"));
        card(page, "🔍 بحث في القرآن", "البحث عن كلمة أو آية محددة.", () ->
            toast("البحث يحتاج إلى قاعدة نص قرآني موثوقة"));
        card(page, "💡 التفسير والترجمة", "الميسر • السعدي • ابن كثير • ترجمات متعددة.", () ->
            toast("سيُضاف المحتوى مع توثيق المصدر"));
        card(page, "📤 مشاركة آية", "مشاركة نص أو بطاقة تصميم بعد توفر النص الموثوق.", () ->
            toast("مشاركة الآيات ستُتاح بعد إضافة المصحف"));
    }

    private void showAdhkar(LinearLayout page) {
        heading(page, "📿 الأذكار والتسابيح");
        card(page, "🌅 أذكار الصباح", "قائمة أذكار موثقة ومناسبة للقراءة دون إنترنت.", () -> toast("سيُضاف محتوى الأذكار الموثوق"));
        card(page, "🏙️ أذكار المساء", "أذكار المساء.", () -> toast("سيُضاف محتوى الأذكار الموثوق"));
        card(page, "🌙 أذكار النوم والاستيقاظ", "أذكار النوم والاستيقاظ.", () -> toast("سيُضاف محتوى الأذكار الموثوق"));
        card(page, "🕌 أذكار الصلاة", "أذكار ما قبل الصلاة وبعدها.", () -> toast("سيُضاف محتوى الأذكار الموثوق"));
        card(page, "🛡️ الرقية الشرعية", "نصوص موثقة مع مصادرها.", () -> toast("سيُضاف محتوى الرقية الموثوق"));
        card(page, "🤲 الأدعية المأثورة", "أدعية مع المصدر والتخريج عند الحاجة.", () -> toast("سيُضاف محتوى الأدعية الموثوق"));

        TextView count = text("العداد: " + tasbeehCount + " / " + tasbeehGoal, 25, gold, true);
        count.setGravity(Gravity.CENTER);
        count.setPadding(0, dp(16), 0, dp(12));
        page.addView(count);
        Button increment = actionButton("🔴 اضغط للتسبيح");
        increment.setTextSize(20);
        increment.setMinHeight(dp(72));
        increment.setOnClickListener(v -> {
            tasbeehCount++;
            if (tasbeehCount >= tasbeehGoal) {
                toast("أكملت الهدف المحدد. تقبّل الله منك");
            }
            render();
        });
        page.addView(increment, matchWrap());
        Button reset = smallButton("🔄 إعادة الضبط");
        reset.setOnClickListener(v -> {
            tasbeehCount = 0; render();
        });
        page.addView(reset, matchWrap());
        Button goal = smallButton("🎯 تحديد الهدف");
        goal.setOnClickListener(v -> chooseGoal());
        page.addView(goal, matchWrap());
        Button custom = smallButton("➕ إضافة ذكر خاص");
        custom.setOnClickListener(v -> {
            EditText input = new EditText(this);
            input.setHint("اكتب الذكر الخاص بك");
            new AlertDialog.Builder(this).setTitle("إضافة ذكر").setView(input)
                .setPositiveButton("حفظ", (d, w) -> {
                    if (input.getText().toString().trim().length() > 0)
                        toast("تم إدخال الذكر لهذه الجلسة؛ الحفظ الدائم سيُضاف لاحقًا");
                }).setNegativeButton("إلغاء", null).show();
        });
        page.addView(custom, matchWrap());
    }

    private void showPrayer(LinearLayout page) {
        heading(page, "🕋 القبلة ومواقيت الصلاة");
        card(page, "📍 المدينة", city + " • تغيير المدينة يدويًا", () -> chooseCity());
        card(page, "🕰️ جدول المواقيت", "الفجر • الشروق • الظهر • العصر • المغرب • العشاء\nلا توجد أوقات محسوبة حتى الآن.", () ->
            toast("لن نعرض أوقاتًا تقديرية على أنها مواقيت صحيحة"));
        card(page, "🧭 بوصلة القبلة", "تحتاج إلى حساس الاتجاه ومعايرة الجهاز.", () ->
            toast("بوصلة القبلة لم تُفعّل بعد"));
        card(page, "🔔 تنبيهات الصلاة", adhanEnabled ? "التنبيه مضبوط على وضع التشغيل داخل الواجهة." : "التنبيه مكتوم داخل الواجهة.", () -> {
            adhanEnabled = !adhanEnabled; render();
        });
        card(page, "🔕 الوضع الصامت تلقائيًا", "اختياري ويتطلب إذن الوصول إلى وضع عدم الإزعاج؛ لن يُفعّل دون موافقتك.", () ->
            toast("هذه الميزة غير مفعّلة بعد"));
        card(page, "📊 سجل الصلوات", "تتبع الصلاة في المنزل أو المسجد أو التي فاتتك.", () ->
            toast("سيُضاف سجل محلي يحفظ اختياراتك"));
        card(page, "🕌 أقرب مسجد", "يتطلب اتصالًا بالإنترنت أو بيانات خرائط محفوظة.", () ->
            toast("البحث عن المساجد غير مفعّل بعد"));
    }

    private void showLibrary(LinearLayout page) {
        heading(page, "📚 المكتبة والتعلم الإسلامي");
        card(page, "📖 صحيح البخاري ومسلم", "تصفح وبحث مع توثيق الكتاب والحديث.", () -> toast("المكتبة الحديثية لم تُضف بعد"));
        card(page, "📜 السيرة النبوية", "محتوى مرتب زمنيًا مع مصادر موثوقة.", () -> toast("قسم السيرة سيُضاف لاحقًا"));
        card(page, "👶 قصص الأنبياء للأطفال", "قصص مناسبة للأطفال مع خيار السرد الصوتي.", () -> toast("قصص الأطفال غير مفعّلة بعد"));
        card(page, "💡 فتاوى وأحكام", "بحث في مصادر شرعية موثوقة؛ لا يصدر التطبيق فتوى مستقلة.", () -> toast("محرك البحث الشرعي لم يُضف بعد"));
        card(page, "💬 اسأل رفيق", "مساعد مقترح يعتمد على مصادر محددة، ويبيّن حدود إجاباته.", () -> toast("المساعد الذكي يحتاج إلى خدمة إنترنت ومصادر موثوقة"));
        card(page, "🧮 حاسبة الزكاة", "حساب أولي مع توضيح النصاب والافتراضات.", () -> toast("حاسبة الزكاة لم تُفعّل بعد"));
        card(page, "🌙 وضع رمضان", "إمساكية وتنبيهات وميزات موسمية.", () -> toast("وضع رمضان سيُضاف بعد مواقيت الصلاة"));
    }

    private void addNavigation(LinearLayout page) {
        TextView label = text("التنقّل", 12, Color.LTGRAY, false);
        label.setGravity(Gravity.CENTER);
        label.setPadding(0, dp(12), 0, dp(6));
        page.addView(label);
        LinearLayout nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);
        String[] names = {"⌂\nالرئيسية", "📖\nالمصحف", "📿\nالأذكار", "🕋\nالقبلة", "📚\nالمكتبة"};
        for (int i = 0; i < names.length; i++) {
            final int index = i;
            Button b = new Button(this);
            b.setText(names[i]);
            b.setTextSize(10);
            b.setAllCaps(false);
            b.setTextColor(currentTab == i ? gold : Color.WHITE);
            b.setBackgroundColor(currentTab == i ? Color.rgb(58, 49, 31) : dark);
            b.setPadding(dp(2), dp(4), dp(2), dp(4));
            nav.addView(b, new LinearLayout.LayoutParams(0, dp(58), 1));
            b.setOnClickListener(v -> { currentTab = index; render(); });
        }
        page.addView(nav);
    }

    private void showSettings() {
        String[] options = {"اختيار المدينة", "كتم/تشغيل تنبيه الأذان", "طريقة حساب المواقيت (ستُضاف)", "حجم الخط (ستُضاف)", "اللغة (ستُضاف)"};
        new AlertDialog.Builder(this).setTitle("⚙ الإعدادات")
            .setItems(options, (dialog, which) -> {
                if (which == 0) chooseCity();
                else if (which == 1) { adhanEnabled = !adhanEnabled; render(); }
                else toast("هذا الخيار لم يُفعّل بعد");
            }).setNegativeButton("إغلاق", null).show();
    }

    private void chooseCity() {
        EditText input = new EditText(this);
        input.setText(city.equals("اختر مدينتك") ? "" : city);
        input.setHint("مثال: تونس أو سوسة");
        new AlertDialog.Builder(this).setTitle("اختيار المدينة").setMessage("اكتب اسم المدينة يدويًا")
            .setView(input).setPositiveButton("حفظ", (d, w) -> {
                String value = input.getText().toString().trim();
                if (!value.isEmpty()) city = value;
                render();
                toast("تم تحديث اسم المدينة. حساب المواقيت الفعلية سيُضاف لاحقًا.");
            }).setNegativeButton("إلغاء", null).show();
    }

    private void chooseGoal() {
        String[] goals = {"33", "99", "100", "500", "1000"};
        new AlertDialog.Builder(this).setTitle("تحديد هدف التسبيح")
            .setItems(goals, (d, which) -> {
                tasbeehGoal = Integer.parseInt(goals[which]);
                tasbeehCount = 0;
                render();
            }).show();
    }

    private void heading(LinearLayout page, String title) {
        TextView t = text(title, 23, gold, true);
        t.setPadding(0, dp(8), 0, dp(14));
        page.addView(t);
    }

    private void card(LinearLayout page, String title, String description, Runnable action) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(16), dp(14), dp(16), dp(14));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(panel);
        bg.setCornerRadius(dp(16));
        bg.setStroke(dp(1), Color.rgb(94, 75, 42));
        box.setBackground(bg);
        TextView t = text(title, 17, gold, true);
        box.addView(t, matchWrap());
        TextView d = text(description, 13, Color.rgb(230, 228, 224), false);
        d.setPadding(0, dp(7), 0, 0);
        box.addView(d, matchWrap());
        box.setClickable(true);
        box.setFocusable(true);
        box.setOnClickListener(v -> action.run());
        LinearLayout.LayoutParams lp = matchWrap();
        lp.setMargins(0, 0, 0, dp(11));
        page.addView(box, lp);
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setGravity(Gravity.CENTER_VERTICAL);
        return t;
    }

    private Button actionButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(gold);
        b.setAllCaps(false);
        b.setBackgroundColor(Color.rgb(58, 49, 31));
        return b;
    }

    private Button smallButton(String value) {
        Button b = actionButton(value);
        b.setTextSize(12);
        return b;
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
