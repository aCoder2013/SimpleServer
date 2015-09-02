import org.junit.Test;

/**
 * Created by Song on 2015/9/1.
 */
public class StringTest {

    @Test
    public void indexTest(){
        String str = "http://www.acoder2013.com";
        System.out.println(str.indexOf("://"));
    }

    @Test
    public void test02(){
        String str = "PUT tieba.baidu.com/f?ie=utf-8&kw=美食的俘虏 HTTP/1.1";
        int index = str.indexOf(" ");
        print(index);
        System.out.println(str.substring(0,index));
        int index1  = str.indexOf("/",index);
        int index2  = str.indexOf(" ",index1);
        print(str.substring(index1,index2));
        //解析请求参数
        int question = str.indexOf("?");
        print(str.substring(question+1,index2));


    }

    public void print(Object str){
        System.out.println(str);
    }


}
