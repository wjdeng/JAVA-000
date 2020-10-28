package week02;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @Author Wayne.Deng
 * @Date 2020/10/28 上午11:06
 * @Version 1.0
 **/
public class HttpClientWeek02 {

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


	public static void main(String[] args){

		try {
			String result = get("http://localhost:8801", null);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String get(String url, String parameter) throws Exception{
		if (StringUtils.isBlank(url)){
			return "";
		}

		if (StringUtils.isNotEmpty(parameter)){
			url = url + "?" + parameter;
		}

		GetMethod method = new GetMethod(url);
		method.getParams()
				.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));

		ByteArrayOutputStream baos = null;

		String result = null;
		try {
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != 200){
				System.out.println("execute http get request error, url:" + url + ", parameters:" + parameter + ", statusCode:" + statusCode);
				throw new RuntimeException("statusCode=" + statusCode);
			}

			InputStream is = method.getResponseBodyAsStream();
			baos = new ByteArrayOutputStream();
			int count = 0;
			byte[] c = new byte[2048];
			while ((count = is.read(c)) != -1){
				baos.write(c,0,count);
			}

			result = new String(baos.toByteArray(), "UTF-8");
			return result;
		} catch (Exception e) {
			throw e;
		}finally {
			method.releaseConnection();;
			if (baos != null){
				try {
					baos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
