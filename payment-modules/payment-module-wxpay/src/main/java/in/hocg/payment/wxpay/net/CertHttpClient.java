package in.hocg.payment.wxpay.net;


import in.hocg.payment.net.OkHttpClient;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import jdk.internal.joptsimple.internal.Strings;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Objects;

/**
 * @author 李华业
 * @date 2020年03月06日 16:27:54
 */
@Slf4j
public class CertHttpClient extends OkHttpClient {

    public CertHttpClient(WxPayConfigStorage configStorage) {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        SSLSocketFactory sslSocketFactory;
        try {
            InputStream in = null;
            File certFile = configStorage.getCertFile();
            if (Objects.nonNull(certFile)) {
                in = new FileInputStream(certFile);
            }

            String certFileStr = configStorage.getCertFileStr();
            if (!Strings.isNullOrEmpty(certFileStr)) {
                in = new ByteArrayInputStream(certFileStr.getBytes(StandardCharsets.UTF_8));
            }

            if (Objects.nonNull(in)) {
                KeyAndTrustManagers keyAndTrustManagers =
                    trustManagerForCertificates(in, configStorage.getMchId());
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(keyAndTrustManagers.keyManagers, null, null);
                sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory);
            }

            client = builder.addInterceptor(getHttpLoggingInterceptor())
                .hostnameVerifier((String s, SSLSession s1) -> true)
                .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static class KeyAndTrustManagers {
        final KeyManager[] keyManagers;
        final TrustManager[] trustManagers;

        KeyAndTrustManagers(KeyManager[] keyManagers, TrustManager[] trustManagers) {
            this.keyManagers = keyManagers;
            this.trustManagers = trustManagers;
        }
    }

    private KeyAndTrustManagers trustManagerForCertificates(InputStream in, String key) throws Exception {

        char[] password = key.toCharArray();
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(in, password);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
            KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        return new KeyAndTrustManagers(
            keyManagerFactory.getKeyManagers(),
            trustManagerFactory.getTrustManagers());
    }

}
