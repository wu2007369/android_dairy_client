package com.example.wuzhiming.aidltest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.wuzhiming.aidltest.bean.Book;
import com.example.wuzhiming.aidltest.bean.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends Service {

    CopyOnWriteArrayList<Book> bookList=new CopyOnWriteArrayList<>();
    private Binder mBinder=new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            bookList.add(book);
        }
    };

    public BookManagerService() {
        bookList.add(new Book(1,"<你好哇，李银河>"));
        bookList.add(new Book(1,"<你好哇，季丹滢>"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
}
