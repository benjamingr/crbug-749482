package reproduce.android.bug.memorybugtest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class TestActivity extends Activity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_actibity);
        wv = new WebView(getApplicationContext());
        String fileName = "<5MB FILENAME URL HERE>";
        wv.getSettings().setJavaScriptEnabled(true);
        wv.evaluateJavascript(
                "function req() {\n" +
                "  return new Promise(resolve => {\n" +
                "    var xhr = new XMLHttpRequest;\n" +
                "    xhr.open(\"GET\", \""+fileName+"?r=\"+Date.now());\n" +
                "    xhr.onload = resolve;\n" +
                "    xhr.responseType = 'arraybuffer';\n" +
                "    xhr.send();\n" +
                "  });\n" +
                "}\n" +
                "(async () => {\n" +
                "  //while(true) await req();\n" +
                "})();", null);
    }
}