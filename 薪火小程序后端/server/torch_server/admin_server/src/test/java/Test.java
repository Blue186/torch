import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class Test {

    @org.junit.jupiter.api.Test
    public void test() {
        double v = Math.random() * 100 + 100;
        System.out.println(v);
        System.out.println("测试");
    }

    @org.junit.jupiter.api.Test
    public void test1() throws FileNotFoundException {
        System.out.println(ResourceUtils.getURL("classpath:").getPath());
    }

}
