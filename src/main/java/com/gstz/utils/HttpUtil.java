package com.gstz.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

@Component
public class HttpUtil {
	static Logger logger = Logger.getLogger("HttpUtil");

	@Value("${env}")
	String envStr;

	private static String env;

	public static void setEnv(String value) {
		env = value;
	}

	@Value("${proxyHost}")
	String proxyHostStr;

	private static String proxyHost;

	public static void setProxyHost(String value) {
		proxyHost = value;
	}

	@Value("${proxyPort}")
	String proxyPortStr;

	private static String proxyPort;

	public static void setProxyPort(String value) {
		proxyPort = value;
	}

	@PostConstruct
	public void init() {
		setEnv(envStr);
		setProxyHost(proxyHostStr);
		setProxyPort(proxyPortStr);
	}

	/*
	 * 通过httpGet的方法去请求url 2018年12月6日15:28:50：更改了net_in,net_out使用完成之后的释放close()
	 */
	public static String httpGet(String url) {
		String result = "";
		BufferedReader in_buf = null;
		InputStream net_in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接

			// 正向代理（生产专用）
			URLConnection connection = null;
			if (env!=null&&"production".equals(env)&&url.startsWith("https")) {// 如果是生产环境，则需要使用正向代理
				logger.info(">>>>>命中正向代理！"+url+proxyHost+":"+proxyPort);
				Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
				connection = realUrl.openConnection(proxy);
			} else {
				connection = realUrl.openConnection();
			}
			connection.setConnectTimeout(30000);//设置60s超时
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			logger.info(map);// 遍历所有的响应头字段

			// 定义 BufferedReader输入流来读取URL的响应
			net_in = connection.getInputStream();
			in_buf = new BufferedReader(new InputStreamReader(net_in));
			String line;
			while ((line = in_buf.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.info("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in_buf != null) {
					in_buf.close();
					net_in.close();
					logger.info("释放net_in资源...");
				}
			} catch (Exception e2) {
				logger.info(e2);
			}
		}
		return result;
	}

	public static String httpDHRGet(String url,String uKey) {
		String location = "";
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 正向代理（生产专用）
			HttpURLConnection connection = null;
			if (env!=null&&"production".equals(env)&&url.startsWith("https")) {// 如果是生产环境，则需要使用正向代理
				logger.info(">>>>>命中正向代理！"+url+proxyHost+":"+proxyPort);
				Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
				connection = (HttpURLConnection) realUrl.openConnection(proxy);
			} else {
				connection = (HttpURLConnection) realUrl.openConnection();
			}
			connection.setConnectTimeout(30000);//设置60s超时
			// 设置通用的请求属性
			connection.setRequestProperty("UKEY", uKey);
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36 Edg/114.0.1823.43");
			connection.setInstanceFollowRedirects(false);
			// 建立实际的连接
			connection.connect();
			location = connection.getHeaderField("Location");
			logger.info("Location==>"+location);
		} catch (Exception e) {
			logger.info("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		return location;
	}

	/*
	 * http的Post方法，params是post中存放的 的参数，比如：“id=123&name=‘sdf’”
	 */
	public static String sendPost(String url, String params) {
		PrintWriter out = null;
		BufferedReader in = null;
		OutputStream net_out = null;
		InputStream net_in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = null;
			if (env!=null&&"production".equals(env)&&url.startsWith("https")) {// 如果是生产环境，则需要使用正向代理
				logger.info(">>>>>命中正向代理！"+url+proxyHost+":"+proxyPort);
				Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
				connection = realUrl.openConnection(proxy);
			} else {
				connection = realUrl.openConnection();
			}
			connection.setConnectTimeout(30000);//设置30s超时
			// 设置通用的请求属性
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			net_out = connection.getOutputStream();
			out = new PrintWriter(net_out);
			// 发送请求参数
			out.print(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			net_in = connection.getInputStream();
			in = new BufferedReader(new InputStreamReader(net_in));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.info("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
				if (net_in != null)
					net_in.close();
				if (net_out != null)
					net_out.close();
				logger.info("释放net_in,net_out资源...");
			} catch (IOException ex) {
				logger.info(ex);
			}
		}
		return result;
	}
}
