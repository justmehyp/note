package com.hyp.proxy;

import com.hyp.proxy.util.ConfigUtil;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Date;

public class Request {

    private org.apache.http.client.fluent.Request request;

    public static Request Get(final URI uri) {
        return new Request(org.apache.http.client.fluent.Request.Get(uri));
    }

    public static Request Get(final String uri) {
        return new Request(org.apache.http.client.fluent.Request.Get(uri));
    }

    public static Request Head(final URI uri) {
        return new Request(org.apache.http.client.fluent.Request.Head(uri));
    }

    public static Request Head(final String uri) {
        return new Request(org.apache.http.client.fluent.Request.Head(uri));
    }

    public static Request Post(final URI uri) {
        return new Request(org.apache.http.client.fluent.Request.Post(uri));
    }

    public static Request Post(final String uri) {
        return new Request(org.apache.http.client.fluent.Request.Post(uri));
    }

    public static Request Patch(final URI uri) {
        return new Request(org.apache.http.client.fluent.Request.Patch(uri));
    }

    public static Request Patch(final String uri) {
        return new Request(org.apache.http.client.fluent.Request.Patch(uri));
    }

    public static Request Put(final URI uri) {
        return new Request(org.apache.http.client.fluent.Request.Put(uri));
    }

    public static Request Put(final String uri) {
        return new Request(org.apache.http.client.fluent.Request.Put(uri));
    }

    public static Request Trace(final URI uri) {
        return new Request(org.apache.http.client.fluent.Request.Trace(uri));
    }

    public static Request Trace(final String uri) {
        return new Request(org.apache.http.client.fluent.Request.Trace(uri));
    }

    public static Request Delete(final URI uri) {
        return new Request(org.apache.http.client.fluent.Request.Delete(uri));
    }

    public static Request Delete(final String uri) {
        return new Request(org.apache.http.client.fluent.Request.Delete(uri));
    }

    public static Request Options(final URI uri) {
        return new Request(org.apache.http.client.fluent.Request.Options(uri));
    }

    public static Request Options(final String uri) {
        return new Request(org.apache.http.client.fluent.Request.Options(uri));
    }

    Request(org.apache.http.client.fluent.Request request) {
        this.request = request;
    }

    public Response execute() throws ClientProtocolException, IOException {
        return this.request.execute();
    }

    public void abort() throws UnsupportedOperationException {
        this.request.abort();
    }

    //// HTTP header operations

    public Request addHeader(final Header header) {
        this.request.addHeader(header);
        return this;
    }

    /**
     * @since 4.3
     */
    public Request setHeader(final Header header) {
        this.request.setHeader(header);
        return this;
    }

    public Request addHeader(final String name, final String value) {
        this.request.addHeader(name, value);
        return this;
    }

    /**
     * @since 4.3
     */
    public Request setHeader(final String name, final String value) {
        this.request.setHeader(name, value);
        return this;
    }

    public Request removeHeader(final Header header) {
        this.request.removeHeader(header);
        return this;
    }

    public Request removeHeaders(final String name) {
        this.request.removeHeaders(name);
        return this;
    }

    public Request setHeaders(final Header... headers) {
        this.request.setHeaders(headers);
        return this;
    }

    public Request setCacheControl(final String cacheControl) {
        this.request.setCacheControl(cacheControl);
        return this;
    }

    public Request setDate(final Date date) {
        this.request.setDate(date);
        return this;
    }

    public Request setIfModifiedSince(final Date date) {
        this.request.setIfModifiedSince(date);
        return this;
    }

    public Request setIfUnmodifiedSince(final Date date) {
        this.request.setIfUnmodifiedSince(date);
        return this;
    }

    //// HTTP protocol parameter operations

    public Request version(final HttpVersion version) {
        this.request.version(version);
        return this;
    }

    public Request useExpectContinue() {
        this.request.useExpectContinue();
        return this;
    }

    public Request userAgent(final String agent) {
        this.request.userAgent(agent);
        return this;
    }

    //// HTTP connection parameter operations

    public Request socketTimeout(final int timeout) {
        this.request.socketTimeout(timeout);
        return this;
    }

    public Request connectTimeout(final int timeout) {
        this.request.connectTimeout(timeout);
        return this;
    }

    //// HTTP connection route operations

    public Request viaProxy(final HttpHost proxy) {
        if(ConfigUtil.proxyEnabled()) {
            this.request.viaProxy(proxy);
        }
        return this;
    }

    /**
     * @since 4.4
     */
    public Request viaProxy(final String proxy) {
        if(ConfigUtil.proxyEnabled()) {
            this.request.viaProxy(proxy);
        }
        return this;
    }

    //// HTTP entity operations

    public Request body(final HttpEntity entity) {
        this.request.body(entity);
        return this;
    }

    public Request bodyForm(final Iterable <? extends NameValuePair> formParams, final Charset charset) {
        this.request.bodyForm(formParams, charset);
        return this;
    }

    public Request bodyForm(final Iterable <? extends NameValuePair> formParams) {
        this.request.bodyForm(formParams);
        return this;
    }

    public Request bodyForm(final NameValuePair... formParams) {
        this.request.bodyForm(formParams);
        return this;
    }

    public Request bodyString(final String s, final ContentType contentType) {
        this.request.bodyString(s, contentType);
        return this;
    }

    public Request bodyFile(final File file, final ContentType contentType) {
        this.request.bodyFile(file, contentType);
        return this;
    }

    public Request bodyByteArray(final byte[] b) {
        this.request.bodyByteArray(b);
        return this;
    }

    /**
     * @since 4.4
     */
    public Request bodyByteArray(final byte[] b, final ContentType contentType) {
        this.request.bodyByteArray(b, contentType);
        return this;
    }

    public Request bodyByteArray(final byte[] b, final int off, final int len) {
        this.request.bodyByteArray(b, off, len);
        return this;
    }

    /**
     * @since 4.4
     */
    public Request bodyByteArray(final byte[] b, final int off, final int len, final ContentType contentType) {
        this.request.bodyByteArray(b, off, len, contentType);
        return this;
    }

    public Request bodyStream(final InputStream instream) {
        this.request.bodyStream(instream);
        return this;
    }

    public Request bodyStream(final InputStream instream, final ContentType contentType) {
        this.request.bodyStream(instream, contentType);
        return this;
    }

    @Override
    public String toString() {
        return this.request.toString();
    }
}
