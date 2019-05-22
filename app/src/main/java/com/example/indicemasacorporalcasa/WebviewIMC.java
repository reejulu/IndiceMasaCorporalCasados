package com.example.indicemasacorporalcasa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewIMC extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_imc);

        this.webView = findViewById(R.id.webview);
        // con esta pagina sale dentro del webview
        //webView.loadUrl("https://dentaris-sa.com");

        // pero con esta de wikipedia no
        //webView.loadUrl("https://es.wikipedia.org/wiki/%C3%8Dndice_de_masa_corporal");

        // la razon es:
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            //este método se llama cada vez que cambiamos de página
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("MIAPP", "Vistando ... " + url);
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("https://es.wikipedia.org/wiki/%C3%8Dndice_de_masa_corporal");
    }




    }

