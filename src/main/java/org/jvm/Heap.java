package org.jvm;

import javax.sound.midi.SoundbankResource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xw
 * @date 2020/7/3 11:07
 */
public class Heap {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<byte[]>();
        int i = 0;
        boolean flag = true;
        while (flag){
            try{
                i++;
                list.add(new byte[1024*1024]);// 每次增加1M大小的数组对象
                Thread.sleep(40);
            }catch (Throwable e){
                e.printStackTrace();
                flag = false;
                System.out.println("Count = " + i);// 记录运行次数
            }
        }
    }
}
