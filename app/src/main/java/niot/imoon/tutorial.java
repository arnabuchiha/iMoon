package niot.imoon;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.io.InputStream;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class tutorial extends Fragment {


    public View v;
    private View mProgressView;
    private WebView webView;
    private Activity activity;
    private String url = "http://buoytutorial.blogspot.in/2016/05/buoy-tutorial.html";
    private SwipeRefreshLayout swipeRefreshLayout;
/**
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.refresh).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            startWebView(url);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
**/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);
        v = rootView.findViewById(R.id.page);

        setHasOptionsMenu(true);
        swipeRefreshLayout=(SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainer);

        mProgressView = rootView.findViewById(R.id.loadingpr);
        webView = (WebView) v;
        activity = getActivity();

        if(activity!=null && isAdded())
            startWebView(url);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if(null!=swipeRefreshLayout)
                            swipeRefreshLayout.setRefreshing(false);
                        webView.reload();
                    }
                }
        );

        return rootView;
    }

    private void startWebView(String url) {

        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        String userAgent = "Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86 Build/MASTER; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile Safari/537.36";
        webView.getSettings().setUserAgentString(userAgent);
        if(activity!=null && isAdded())
            showProgress(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                if(activity!=null && isAdded())
                    showProgress(false);
            }


        });
        webView.loadUrl(url);
    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            v.setVisibility(show ? View.GONE : View.VISIBLE);
            v.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    v.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            v.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


}
