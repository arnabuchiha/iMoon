package niot.imoon;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;


public class tutorial extends Fragment {


    public View v;
    private WebView webView;
    private Activity activity;
    private String url = "http://buoytutorial.blogspot.in/2016/05/buoy-tutorial.html";
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    public tutorial() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);
        v = rootView.findViewById(R.id.page);



        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) rootView.findViewById(R.id.main_swipeweb);

        webView = (WebView) v;
        activity = getActivity();
        if(activity!=null && isAdded())
            startWebView(url);


        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                startWebView(url);

                mWaveSwipeRefreshLayout.setRefreshing(false);



            }
        });


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
        mWaveSwipeRefreshLayout.isRefreshing();

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
                    mWaveSwipeRefreshLayout.setRefreshing(false);

            }


        });
        webView.loadUrl(url);


    }






}
