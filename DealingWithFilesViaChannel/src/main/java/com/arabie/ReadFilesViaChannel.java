package com.arabie;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFilesViaChannel {
    public static void main(String args[]) {
        int count;
        Path filepath= null;
        try{
            filepath= Paths.get("test.txt");
        } catch(InvalidPathException e) {
            System.out.println("Path Error "+ e);
            return;
        }
        try(SeekableByteChannel fChan= Files.newByteChannel(filepath)) {
            ByteBuffer mBuf= ByteBuffer.allocate(128);
            do{
                count= fChan.read(mBuf);
                if(count!= -1) {
                    mBuf.rewind();
                    for(int i= 0; i< count; i++)
                        System.out.print((char) mBuf.get());
                }
            } while(count!= -1);
            System.out.println();
        } catch(IOException e) {
            System.out.println("I/O Error "+ e);
        }
    }
}
