package org.ifies.android.webkit;

import org.ifies.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class ExampleWebkit extends Activity {

	public class MyChromeClient extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			ExampleWebkit.this.setProgress(newProgress * 100);
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.webview_example);
		
		setButtonGo((Button)findViewById(R.id.button_go));
		setEnterUri((EditText)findViewById(R.id.enter_uri));
		
		setWebView((WebView)findViewById(R.id.wv1));
		setWebViewClient(new WebViewClient());
		getWebView().setWebViewClient(getWebViewClient());
		getWebView().setWebChromeClient(new MyChromeClient());
	}
	
	@Override
	public void onStart() {
		super.onStart();
		getWebView().setHorizontalScrollBarEnabled(true);
		getWebView().getSettings().setJavaScriptEnabled(true);
		getWebView().getSettings().setLoadsImagesAutomatically(true);
		
		getWebView().loadUrl("http://android.ifies.org");
		
		getButtonGo().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = getEnterUri().getText().toString();
				getWebView().loadUrl(url);
			}
		});
		
	}
	
	public void setWebView(WebView webView) {
		this.webView = webView;
	}
	public WebView getWebView() {
		return webView;
	}
	
	public void setWebViewClient(WebViewClient webViewClient) {
		this.webViewClient = webViewClient;
	}
	
	public WebViewClient getWebViewClient() {
		return webViewClient;
	}

	public void setButtonGo(Button buttonGo) {
		this.buttonGo = buttonGo;
	}

	public Button getButtonGo() {
		return buttonGo;
	}

	public void setEnterUri(EditText enterUri) {
		this.enterUri = enterUri;
	}

	public EditText getEnterUri() {
		return enterUri;
	}

	private WebView webView;
	private WebViewClient webViewClient;
	
	private Button buttonGo;
	private EditText enterUri;
	
}
