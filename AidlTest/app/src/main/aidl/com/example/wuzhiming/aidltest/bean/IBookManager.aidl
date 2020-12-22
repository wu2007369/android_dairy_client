// IBookManager.aidl
package com.example.wuzhiming.aidltest.bean;

import com.example.wuzhiming.aidltest.bean.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBookList();
    void addBook(in Book book);
}
