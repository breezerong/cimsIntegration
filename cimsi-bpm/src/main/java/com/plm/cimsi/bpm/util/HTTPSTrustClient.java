package com.plm.cimsi.bpm.util;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HTTPSTrustClient extends HTTPSClient {

    public HTTPSTrustClient() {

    }

    @Override
    public void prepareCertificate() throws Exception {
        // 跳过证书验证
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        // 设置成已信任的证书
        ctx.init(null, new TrustManager[] { tm }, null);
        this.connectionSocketFactory = new SSLConnectionSocketFactory(ctx);
    }
}
