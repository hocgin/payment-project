package in.hocg.payment.wxpay.net;


import in.hocg.payment.net.OkHttpClient;
import in.hocg.payment.wxpay.v2.WxPayConfigStorage;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * @author 李华业
 * @date 2020年03月06日 16:27:54
 */
@Slf4j
public class CertHttpClient extends OkHttpClient {

    public CertHttpClient(WxPayConfigStorage configStorage) {
        SSLSocketFactory sslSocketFactory;
        try {
            FileInputStream in = new FileInputStream(configStorage.getCertFile());
            KeyAndTrustManagers keyAndTrustManagers =
                trustManagerForCertificates(in, configStorage.getMchId());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyAndTrustManagers.keyManagers, null, null);
            sslSocketFactory = sslContext.getSocketFactory();

            client = new okhttp3.OkHttpClient.Builder()
                .addInterceptor(getHttpLoggingInterceptor())
                .hostnameVerifier((String s, SSLSession s1) -> true)
                .sslSocketFactory(sslSocketFactory)
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
