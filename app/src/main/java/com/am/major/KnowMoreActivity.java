package com.am.major;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class KnowMoreActivity extends AppCompatActivity {
    private WebView wikiKnowMore;
    private ProgressBar progressBar;
    private String base_url = "https://en.wikipedia.org/wiki/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_more);

        Bundle bundle = getIntent().getExtras();
        final String detectedItem = bundle.getString("keyword");

        String newUA = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        progressBar = findViewById(R.id.progressBar);
        wikiKnowMore = findViewById(R.id.webview);
        wikiKnowMore.getSettings().setJavaScriptEnabled(true);
        wikiKnowMore.setWebViewClient(new WebViewClient());
        wikiKnowMore.clearHistory();
        wikiKnowMore.clearCache(true);
        // wikiKnowMore.getSettings().setUserAgentString(newUA);
        wikiKnowMore.loadUrl(base_url + detectedItem);


        wikiKnowMore.canGoBack();
        wikiKnowMore.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && wikiKnowMore.canGoBack()) {
                    wikiKnowMore.goBack();
                    return true;
                }
                return false;
            }
        });

    }
    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            wikiKnowMore.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
