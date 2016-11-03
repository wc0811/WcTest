package com.chaowang.wctest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue = null;
    private ImageView ivGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivGet = findView(R.id.iv_Get);
        requestQueue = new Volley().newRequestQueue(MainActivity.this);
        findView(R.id.btn_Sring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringRequest stringRequest = new StringRequest("http://www.baidu.com",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(MainActivity.this, "王超太帅成功1", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "王超太帅失败1", Toast.LENGTH_SHORT).show();
                    }
                });
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://m.weather.com.cn/data/101010100.html", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(MainActivity.this, "王超太帅成功2", Toast.LENGTH_SHORT).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "王超太帅失败2", Toast.LENGTH_SHORT).show();

                    }
                });
                ImageRequest imageRequest = new ImageRequest("http://i2.img.969g.com/pub/imgx2016/01/05/284_141211_5a624.jpg",
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                Toast.makeText(MainActivity.this, "王超太帅成功3", Toast.LENGTH_SHORT).show();

                            }
                        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "王超太帅失败3", Toast.LENGTH_SHORT).show();
                    }
                });

                ImageLoader imageLoader = new ImageLoader(requestQueue,new LruImageCache());
                /***
                 * getImageListener()方法接收三个参数，第一个参数指定用于显示图片的ImageView控件，
                 * 第二个参数指定加载图片的过程中显示的图片，第三个参数指定加载图片失败的情况下显示的图片
                 */
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(ivGet,
                        R.drawable.im, R.drawable.im);

                // imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
                imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
                        listener, 200, 200);

                requestQueue.add(stringRequest);
                requestQueue.add(jsonObjectRequest);
                requestQueue.add(imageRequest);
            }
        });
    }

    private <T extends View> T findView(int viewId) {
        return (T) findViewById(viewId);
    }
}
