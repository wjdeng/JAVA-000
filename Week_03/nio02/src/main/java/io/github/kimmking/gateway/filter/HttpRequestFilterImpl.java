package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/4 上午11:27
 * @Version 1.0
 **/
public class HttpRequestFilterImpl implements HttpRequestFilter{
	@Override
	public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
		HttpHeaders headers =  fullRequest.headers();
		headers.add("nio","dengweijun");
	}
}
