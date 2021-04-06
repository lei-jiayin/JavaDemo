package utils;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import utils.vto.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 噢噢噢噢 jxl 对Excel表的读写操作
 * @author adv
 * @date 2021/4/2 16:13
 */
public class JxlExcelUtil {
    public static void main(String[] args) {
        String fileName = "student.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        // EasyExcel.write(fileName, Student.class).sheet("模板").doWrite(data());

        List<Student> list = EasyExcel.read(fileName).head(Student.class).sheet().doReadSync();
        // Student student = students.get(0);
        // System.out.println(student.toString());
        for (Student s:list) {
            System.out.println(JSON.toJSONString(s));
        }
    }
    public static List<Student> data(){
        List<Student> s = new ArrayList<>();
        for(int i =0;i< 10;i++){
            Student student = new Student(i+1,"张"+i,i % 2 == 0 ? "男":"女");
            student.setJoinTime(new Date());
            s.add(student);
        }
        return s;
    }

}
