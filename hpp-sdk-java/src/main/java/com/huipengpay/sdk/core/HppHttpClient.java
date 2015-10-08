package com.huipengpay.sdk.core;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/**
 * 发送http请求的助手
 */
public class HppHttpClient {
    private static final int TIMEOUT_SECONDS = 20;//默认超时时间
    private static final int POOL_SIZE = 20;//http池
    private static final Logger LOGGER = LoggerFactory.getLogger(HppHttpClient.class);
    private static CloseableHttpClient httpClient;
    private static ErrorResponseHandler errorHandler;
    private static volatile HppHttpClient sInstance;

    private HppHttpClient() {
        try {
            init();
        } catch (GeneralSecurityException e) {
            LOGGER.error("Huipeng Pay SDK INIT error:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static HppHttpClient getInstance() {
        if (null == sInstance) {
            synchronized (HppHttpClient.class) {
                if (null == sInstance) {
                    sInstance = new HppHttpClient();
                }
            }
        }
        return sInstance;
    }

    /**
     * 将请求封装成HttpClient的标准格式
     */
    private List<NameValuePair> paramsToList(Map<String, ?> params) {
        List<NameValuePair> result = Lists.newArrayList();

        if (!MapUtils.isEmpty(params)) {
            for (String key : params.keySet()) {
                result.add(new BasicNameValuePair(key, MapUtils.getString(params, key)));
            }
        }

        return result;
    }


    /**
     * 构造body请求
     */
    private Request prepareBodyRequest(String url, HttpRequestMethod method, String payload, Charset charset, String contentType) throws IOException {

        Request request;
        switch (method) {
            default:
                StringEntity entity = new StringEntity(payload, charset);
                entity.setContentType(contentType);

                request = Request.Post(url).body(entity);
                break;
        }

        return request;
    }

    /**
     * 发送Body请求
     *
     * @param url         地址
     * @param payload     body内容
     * @param charset     字符集
     * @param contentType 类型
     */
    public String sendHttpRequest(String url, String payload, Map<String, String> headers, Charset charset, String contentType) {

        try {
            Executor executor = Executor.newInstance(httpClient);
            Request request = prepareBodyRequest(url, HttpRequestMethod.POST, payload, charset, contentType);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }

            return executor.execute(request).handleResponse(errorHandler);
        } catch (Exception ex) {
            String msg = String.format("请求[%s]发生错误[%s][%s]", url, ex.getClass(), ex.getLocalizedMessage());
            LOGGER.error(msg);

            throw new ServiceException(msg);
        }
    }


    private void init() throws GeneralSecurityException {

        //信任所有ssl证书,并跳过域名验证
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, new TrustStrategy() {
                    public boolean isTrusted(X509Certificate[] chain,
                                             String authType) throws CertificateException {
                        return true;
                    }
                }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(TIMEOUT_SECONDS * 1000)
                .setConnectTimeout(TIMEOUT_SECONDS * 1000)
                .build();

        httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(POOL_SIZE)
                .setSSLSocketFactory(sslsf)
                .setMaxConnPerRoute(POOL_SIZE)
                .setDefaultRequestConfig(requestConfig)
                .build();

        errorHandler = new ErrorResponseHandler();
    }

    private void destroy() {
        try {
            httpClient.close();
        } catch (IOException e) {
            LOGGER.error("释放HttpClient资源失败:", e);
        }
    }
}
