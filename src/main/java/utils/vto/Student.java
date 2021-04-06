package utils.vto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author adv
 * @date 2021/4/2 16:34
 */
@Data
@ToString
public class Student {
    @ExcelProperty(value = "序号")
    Integer id;
    @ExcelProperty(value = "名称")
    String name;
    @ExcelProperty(value = "性别")
    String sex;
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "入学时间")
    Date joinTime;

    public Student() {
    }

    public Student(Integer id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
