import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFunction {
    public static void main(String args[]) throws Exception {
        ArrayList<String> files = getFiles("*");
        for (int i = 0; i < files.size(); i++) {
            String file = readFile(files.get(i));
            String name = files.get(i);
            name = name.substring(name.lastIndexOf("\\")+1,name.length()-3);
            String regex = "spider[List|Item].*:function";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(file);
            while (matcher.find()) {
                String s = matcher.group();//得到匹配的结果
                s = s.substring(0, s.length() - 9);
                DB db = new DB();
                db.DBHelper(name,s);
                System.out.println(name + " " + s);
            }
        }
    }
    public static String readFile(String filename) throws Exception {
        StringBuffer buffer = new StringBuffer("");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
            buffer = new StringBuffer();
            while (br.ready())
                buffer.append((char) br.read());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(br!=null) br.close();
        }
        return buffer.toString();
    }
    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
//              System.out.println("文     件：" + tempList[i]);
              files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
//              System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;
    }
}
