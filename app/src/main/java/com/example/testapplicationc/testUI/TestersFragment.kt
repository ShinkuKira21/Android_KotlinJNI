package com.example.testapplicationc.testUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.testapplicationc.R
import kotlin.text.contains
import kotlin.toString


class TestersFragment : Fragment() {
    // TODO: Add Survey URL
    private val formUrl = "https://forms.gle/?"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_testers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = view.findViewById<WebView>(R.id.wvGoogleForms)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true


        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()

                return if (url.contains("docs.google.com")) {
                    false
                } else {
                    Toast.makeText(requireContext(), "External links are blocked", Toast.LENGTH_SHORT).show()
                    true
                }
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return if (url?.contains("docs.google.com") == true) {
                    false
                } else {
                    Toast.makeText(requireContext(), "External links are blocked", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }


        webView.loadUrl(formUrl)


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        // Optional: reload form or navigate back
                        webView.loadUrl(formUrl)
                        // Or: findNavController().popBackStack()
                    }
                }
            })
    }
}