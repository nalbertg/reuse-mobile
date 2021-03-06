package br.ufrn.reuse.activity;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.support.v7.app.AppCompatActivity;
import br.ufrn.reuse.R;
import br.ufrn.reuse.facade.ReuseFacadeImpl;
import br.ufrn.reuse.remote.rest.ApiConfig;

public class APISinfoLoginActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apisinfo_login);

        webView = (WebView) findViewById(R.id.login_sinfo_web_view);
        webView.requestFocus(View.FOCUS_DOWN);

        pd = ProgressDialog.show(this, "", "Carregando...", true);

        webView.setWebViewClient(getAuthorizationWebViewClient());
        webView.loadUrl(ApiConfig.getAuthorizationUrl());
    }

    @NonNull
    private WebViewClient getAuthorizationWebViewClient() {
        return new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (pd != null && pd.isShowing()) {
                    pd.dismiss();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String authorizationUrl) {
                if (authorizationUrl.startsWith(ApiConfig.REDIRECT_URI)) {
                    Uri uri = Uri.parse(authorizationUrl);

                    String stateToken = uri.getQueryParameter(ApiConfig.STATE_PARAM);
                    if (stateToken == null || !stateToken.equals(ApiConfig.STATE)) {
                        return true;
                    }

                    String authorizationToken = uri.getQueryParameter(ApiConfig.RESPONSE_TYPE_VALUE);
                    if (authorizationToken == null) {
                        return true;
                    }

                    new PostRequestAsyncTask().execute(authorizationToken);

                } else {
                    Log.i("Authorize", "Redirecionando para a autorização: " + authorizationUrl);
                    webView.loadUrl(authorizationUrl);
                }

                return true;
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private class PostRequestAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... authroizationCodes) {
            if (authroizationCodes.length > 0) {
                try {
                    return new ReuseFacadeImpl(APISinfoLoginActivity.this).autenticar(authroizationCodes[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return false;
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean status) {
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }

            if (status) {
                Intent startProfileActivity = new Intent(APISinfoLoginActivity.this, VitrineActivity.class);
                APISinfoLoginActivity.this.startActivity(startProfileActivity);
            }
        }

    }

}
