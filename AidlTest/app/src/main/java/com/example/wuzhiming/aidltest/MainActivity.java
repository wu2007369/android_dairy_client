package com.example.wuzhiming.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wuzhiming.aidltest.bean.Book;
import com.example.wuzhiming.aidltest.bean.IBookManager;
import com.example.wuzhiming.aidltest.service.BookManagerService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager iBookManager=IBookManager.Stub.asInterface(service);

            try {
                List<Book> list=iBookManager.getBookList();
                logList(list);
                list.add(new Book(3,"<i love you>"));
                logList(list);

            }catch (android.os.RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this, BookManagerService.class);
        bindService(intent,mServiceConnection, BIND_AUTO_CREATE);
    }

    private void logList(List<Book> lists){
        for (Book book:lists)
            Log.i(this.toString(),"book="+book);

    }
}
