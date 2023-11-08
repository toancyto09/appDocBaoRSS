package com.example.appdocbao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayLink;

    ArrayList<Item> arrayList;
    AdapterItem adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        arrayLink = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);


        adapter = new AdapterItem(this, R.layout.customlist, arrayList);
        listView.setAdapter(adapter);

        String rssUrl = "https://vnexpress.net/rss/so-hoa.rss";
        DownloadRssTask downloadRssTask = new DownloadRssTask();
        downloadRssTask.execute(rssUrl);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("linkTinTuc", arrayLink.get(position));
                startActivity(intent);

            }
        });


    }
    public class DownloadRssTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String rssContent = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }

                reader.close();
                connection.disconnect();
                rssContent = content.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return rssContent;
        }

        @Override
        protected void onPostExecute(String rssContent) {
            // Xử lý dữ liệu RSS ở đây và cập nhật giao diện người dùng
            if (!rssContent.isEmpty()) {
                XMLDOMParser parser = new XMLDOMParser();
                Document document = parser.getDocument(rssContent);

                NodeList nodeList = document.getElementsByTagName("item");

                String tieuDe="", moTa="", ngayDang="";

                for(int i=0; i<nodeList.getLength(); i++){
                    Element element = (Element) nodeList.item(i);
                    tieuDe = parser.getValue(element, "title");
                    moTa = parser.getValue(element, "description");
                    ngayDang = parser.getValue(element, "pubDate");

                    arrayList.add(new Item(tieuDe, moTa, ngayDang));
                    arrayLink.add(parser.getValue(element, "link"));

                }
                adapter.notifyDataSetChanged();

            } else {
                // Hiển thị thông báo lỗi nếu không tải được dữ liệu
                Toast.makeText(getApplicationContext(), "Không thể tải dữ liệu RSS", Toast.LENGTH_SHORT).show();
            }
        }
    }



}