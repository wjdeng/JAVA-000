package io.github.kimmking.gateway.outbound.httpclient;

import com.sun.java.browser.net.ProxyService;
import io.github.kimmking.gateway.inbound.HttpInboundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/4 上午10:52
 * @Version 1.0
 **/
public class HttpClientOutboundHandler {

	private static Logger logger = LoggerFactory.getLogger(HttpClientOutboundHandler.class);
	private static MultiThreadedHttpConnectionManager httpConnectionManager;
	private static HttpClient httpClient;

	static {

		try {
			httpConnectionManager = new MultiThreadedHttpConnectionManager();
			httpClient = new HttpClient(httpConnectionManager);
			// 每个host的最大连接数
			httpClient.getHttpConnectionManager().getParams().setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION,1024);
			//总连接数
			httpClient.getHttpConnectionManager().getParams().setMaxTotalConnections(1024);
			// 读超时
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 60 * 1000);
			// 连接超时时间
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handle(String URL, final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) throws Exception{
		System.out.println("即将访问:url="+URL);
		FullHttpResponse response = null;
		try {
			GetMethod method = new GetMethod(URL);
			method.getParams()
					.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));

			httpClient.executeMethod(method);
			response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(method.getResponseBody()));
			response.headers().set("Content-Type", "application/json");
			response.headers().setInt("Content-Length", (int)method.getResponseContentLength());
		} catch (Exception e) {
			e.printStackTrace();
			response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
			exceptionCaught(ctx, e);
		}finally {

			if (fullRequest != null) {
				if (!HttpUtil.isKeepAlive(fullRequest)) {
					ctx.write(response).addListener(ChannelFutureListener.CLOSE);
				} else {
					//response.headers().set(CONNECTION, KEEP_ALIVE);
					ctx.write(response);
				}
			}
			ctx.flush();
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
