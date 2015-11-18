package com.example.recyclerview;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.utils.StreamTool;





import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
	

public class MainActivity extends Activity {
	private RecyclerView recyclerView;
	private Button btn;
	private EditText ed_city;
	private TextView city_result1;
	private ArrayList<Fruit> frusts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        city_result1 = (TextView) findViewById(R.id.city_result1);
        ed_city = (EditText) findViewById(R.id.ed_city);
        
        check();
        initDatas();
        initViews();
    }
    
    private void initViews(){
    	
    	
    	
    	recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    	
    	MyAdapter adapter = new MyAdapter(this, frusts);
    	
    	recyclerView.setAdapter(adapter);
    	
    	
    	
    	recyclerView.setItemAnimator(new DefaultItemAnimator());
    	//recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    	recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    	//recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));
    	
    }
    private void initDatas(){
    
    }
    
    
	
	
	
    
	public void check(){
		
		
			// TODO Auto-generated method stub
			new Thread(){
				
				/* (non-Javadoc)
				 * @see java.lang.Thread#run()
				 */
				public void run(){
					URL url;
					try {
						url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42");
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setReadTimeout(5000);//读取数据超时
						conn.setConnectTimeout(5000);//网络连接超时
						conn.setRequestMethod("GET");//设置请求方式
						int responseCode = conn.getResponseCode();
						if(responseCode == 200){
							InputStream is = conn.getInputStream();//这是获取字节流？？
							
							BufferedReader reader = new BufferedReader(new InputStreamReader(is));//BufferedReader是对字符流进行封装，但是我现在的是字节流，所以需要转化
							String line = reader.readLine();
							StringBuilder jsonString = new StringBuilder();
							while(line != null){
								jsonString.append(line);
								//继续读取，防止死循环
								line = reader.readLine();
							}
							//UrlBean parseJson = parseJson(jsonString);
							System.out.println(jsonString+"");
							reader.close();//关闭输入流
							conn.disconnect();//断开连接
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			};
		

		
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
