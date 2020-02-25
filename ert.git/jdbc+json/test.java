import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class test {

@SuppressWarnings("deprecation")
public static void main(String[] args) throws ClientProtocolException, IOException {
	DefaultHttpClient demo = new DefaultHttpClient();
	      demo.getParams().setParameter("http.protocol.content-charset", "UTF-8");
	HttpGet httpget=new HttpGet("https://docs.microsoft.com/zh-tw/azure/cognitive-services/text-analytics/quickstarts/java");
	 HttpResponse response = demo.execute(httpget);
	 String responseString = EntityUtils.toString(response.getEntity());
	 if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		             // 如果回傳是 200 OK 的話才輸出
		             System.out.println(responseString);
	 
}

}
}
