package org.IO.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞I/O的实现
 * @author adv
 * @date 2020/10/14 15:42
 */
public class BioServerSingle {
    public static void main(String[] args) {
        int port = 8080;
        ServerSocket server = null;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            server = new ServerSocket(port);
            while (true){
                socket = server.accept(); // 未接收到数据则阻塞 进行三次握手
                inputStream = socket.getInputStream();

                int length = 0;
                byte[] req = new byte[1024];
                while ((length = inputStream.read(req)) > 0){ //读不到数据进入阻塞
                    System.out.println("req:"+new String(req,0,length));
                    outputStream = socket.getOutputStream();
                    outputStream.write("res".getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (server != null) {
                    server.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
