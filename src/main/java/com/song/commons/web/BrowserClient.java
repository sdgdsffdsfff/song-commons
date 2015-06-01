package com.song.commons.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class BrowserClient {
	
	private static final int TIMEOUT = 1000 * 10;
	
	private static final String TYPE_POST = "POST";
	private static final String TYPE_GET = "GET";
	
	private String URL;
	
	private String type;
	
	private int timeout;
	
	private File file;
	
	private byte[] responseBody;
	
	public BrowserClient(String url) {
		this.type = TYPE_GET;
		this.timeout = TIMEOUT;
		this.setURL(url);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public byte[] getResponseBody() {
		return responseBody;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 请求服务
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public boolean request() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		HttpConnectionManager connMan = client.getHttpConnectionManager();
		HttpConnectionManagerParams params = connMan.getParams();
		params.setConnectionTimeout(getTimeout());
		
		HttpMethod method = null;
		if (TYPE_POST.equals(type)) {
			method = new PostMethod(URL);
			PostMethod postMethod = (PostMethod)method;
			
			// 上传文件
			if (file != null) {
				FilePart filePart = new FilePart(file.getName(), file);
				Part[] parts = new Part[] {filePart};
				postMethod.setRequestEntity(new MultipartRequestEntity(parts,
						postMethod.getParams()));
			}
		} else {
			method = new GetMethod(URL);
		}
		
		int status = client.executeMethod(method);
		if (status == HttpStatus.SC_OK) {
			responseBody = method.getResponseBody();
			return true;
		}
		
		return false;
	}
	
	public Document getXmlDoc() {
		if (responseBody == null) {
			return null;
		}
		
		ByteArrayInputStream binput = new ByteArrayInputStream(responseBody);
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(binput);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (JDOMException ex) {
			ex.printStackTrace();
		}
		return doc;
	}
	
	public String getValue(String key) {
		Document doc = getXmlDoc();
		if (doc == null) {
			return null;
		}
		
		String value = doc.getDocument().
		getRootElement().getChildText(key);
		return value;
	}
}
