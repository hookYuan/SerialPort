package com.yuan.serialport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuan.serialport.lib.BaudRate;
import com.yuan.serialport.lib.OnSerialReadListener;
import com.yuan.serialport.lib.SerialPortFinder;
import com.yuan.serialport.lib.SerialPortHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.tv_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_allSerialPort:
                String[] str = new SerialPortFinder().getAllDevicesPath();
                String result = "本机的所有串口：\r\n";
                for (String path : str) {
                    result = result + path + "\r\n";
                }
                tvContent.setText(result);
                break;
            case R.id.btn_openSerialPort:
                String[] str2 = new SerialPortFinder().getAllDevicesPath();
                int i = 0;
                for (String path : str2) {
                    if (i == 2) {
                        SerialPortHelper helper = new SerialPortHelper(path, BaudRate.b115200);
                        helper.setOnSerialReadListener(new OnSerialReadListener() {
                            @Override
                            public void onRead(byte[] buffer, int length) {

                            }
                        });
                    }
                    i++;
                }
                tvContent.setText("串口开启成功");
                break;
        }
    }
}
