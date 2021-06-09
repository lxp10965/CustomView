package com.xpl.customview.algorithm;

import android.graphics.Paint;
import android.os.FileUtils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class TestMain {

    @Test
    public void test_sunafa() throws Exception {
        int[] a = {6, 4, 8, 2, 10, 3, 1, 5, 8, 7, 9};
        int[] b = BubbleSort.sort(a);
        for (int c : b) {
            System.out.print(c + "\t");
        }

    }

    @Test
    public void test_shuzu() {

//        Java 实际上没有多维数组，只有一维数组。
//        多维数组可以理解为“数组的数组” 。
//        举例二维数组其实是一个一维数组，数组中每个元素是一个一维数组，从而得到行列的长度为 。
//        int[][] data = new int[2][5];
//        int[][] array = {{1,2,3},{4,5,6},{7,8,9}};
//        System.out.println(" 第一维数组的长度==="+data.length);
//        System.out.println(" 第二维数组的长度==="+data[0].length);
//        System.out.println(" 第二维数组的长度==="+array.length);
//        System.out.println(" 第二维数组的长度==="+array[0].length);


//        int[] arr={10, 20, 30, 40, 50};
//        reverse(arr,arr.length);
//        Integer [] iarr = {10, 20, 30, 40, 50};
//        reverse(iarr); //调用Collections接口反转数组

//        iTerator();//迭代遍历

//        heBinShuZhu();
//        arrayPaddind();
//        arrayExpansion();

//        int[] my_array = {1, 2, 5, 5, 6, 6, 7, 2, 9, 2};
//        findDupicateInArray(my_array); //查找重复元素

//        数组中查找指定元素();
        判断数组是否相等();
    }

    @Test
    public void 查看目录(){
//        File[] roots = File.listRoots();
//        System.out.println("系统所有根目录：");
//        for (int i=0; i < roots.length; i++) {
//            System.out.println(roots[i].toString());
//        }
//
//        String curDir = System.getProperty("user.dir");
//        System.out.println("你当前的工作目录为 :" + curDir);

        System.out.println("遍历目录");
        File dir = new File("F:\\yyb\\CustomView"); //要遍历的目录
        visitAllDirsAndFiles(dir);
    }
    public static void visitAllDirsAndFiles(File dir) {
        System.out.println(dir);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                visitAllDirsAndFiles(new File(dir, children[i]));
            }
        }
    }

    @Test
    public void 筛选目录() throws Exception {
        File file = new File("F:\\edit_1\\portable\\container");
        getAllDirectory(file);
    }
    public void getAllDirectory(File file) {
        //创建过滤器
        File[] f = file.listFiles(new GetAllDirectory());
        for (File f1 : f) {
            System.out.println(f1);
            //判断目录是否为空
            if (f1.length() != 0)
                getAllDirectory(f1);
        }
    }

    static class GetAllDirectory implements FileFilter {
        public boolean accept(File pathname) {
            // 去除所有非文件夹
            if (pathname.isDirectory()) {
                return true;
            }
            return false;
        }
    }

    @Test
    public void 遍历指定目录下的所有目录(){
        File dir = new File("E:");
        File[] files = dir.listFiles();
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        files = dir.listFiles(fileFilter);
        System.out.println(files.length);
        if (files.length == 0) {
            System.out.println("目录不存在或它不是一个目录");
        }
        else {
            for (int i=0; i< files.length; i++) {
                File filename = files[i];
                System.out.println(filename.toString());
            }
        }
    }

    @Test
    public void 在指定目录中查找文件(){
        File dir = new File("F:\\yyb\\CustomView\\app\\src\\test\\java\\com\\xpl\\customview");
        String[] children = dir.list();
        if (children == null) {
            System.out.println("该目录不存在");
        }
        else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
        }

//        long size = FileUtils.sizeOfDirectory(new File("C:/test"));
//        System.out.println("Size: " + size + " bytes");
    }

    @Test
    public void 创建文件() {
        try {
            File file = new File("C:/myfile.txt");
            if (file.createNewFile())
                System.out.println("文件创建成功！");
            else
                System.out.println("出错了，该文件已经存在。");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Test
    public void 获取文件修改时间() {
        File file = new File("runoob1.txt");
        Long lastModified = file.lastModified();
        Date date = new Date(lastModified);
        System.out.println(date);
    }

    @Test
    public void 在指定目录中创建文件() {
        File file = null;
        File dir = new File("C:/");
        try {
            file = File.createTempFile("JavaTemp", "javatemp", dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getPath());
    }

    @Test
    public void 设置文件只读() {
        File file = new File("runoob1.txt");
        System.out.println(file.setReadOnly());
        System.out.println(file.canWrite());
        System.out.println(file.setWritable(true));
        System.out.println(file.canWrite());
        System.out.println(file.exists());//检测文件是否存在
    }

    @Test
    public void 文件重命名() throws IOException {
        // 旧的文件或目录
        File oldName = new File("runoob.txt");
        // 新的文件或目录
        File newName = new File("runoob1.txt");
        if (newName.exists()) {  //  确保新的文件名不存在
            throw new java.io.IOException("file exists");
        }
        if (oldName.renameTo(newName)) { //重命名
            System.out.println("已重命名");
        } else {
            System.out.println("Error");
        }
    }

    @Test
    public void 获取文件大小() {
        long size = getFileSize("runoob.txt");
        System.out.println("java.txt文件大小为: " + size);
    }

    public static long getFileSize(String filename) {
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println("文件不存在");
            return -1;
        }
        return file.length();
    }

    @Test
    public void 修改文件最后的修改日期() throws IOException {
        File fileToChange = new File("C:/myjavafile.txt");
        fileToChange.createNewFile();
        Date filetime = new Date(fileToChange.lastModified());
        System.out.println(filetime.toString());
        System.out.println(fileToChange.setLastModified(System.currentTimeMillis()));
        filetime = new Date(fileToChange.lastModified());
        System.out.println(filetime.toString());
    }

    @Test
    public void 创建临时文件() throws IOException {
        File temp = File.createTempFile("testrunoobtmp", ".txt");
        System.out.println("文件路径: " + temp.getAbsolutePath());
        temp.deleteOnExit();
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        out.write("aString");
        System.out.println("临时文件已创建:");
        out.close();
    }

    @Test
    public void 将文件内容复制到另一个文件() throws IOException {
        BufferedWriter out1 = new BufferedWriter(new FileWriter("srcfile"));
        out1.write("string to be copied\n");
        out1.close();
        InputStream in = new FileInputStream(new File("srcfile"));
        OutputStream out = new FileOutputStream
                (new File("destnfile"));
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        BufferedReader in1 = new BufferedReader(new FileReader("destnfile"));
        String str;
        while ((str = in1.readLine()) != null) {
            System.out.println(str);
        }
        in1.close();
    }

    @Test
    public void 删除文件() {
        try {
            File file = new File("runoob.txt");
            if (file.delete()) {
                System.out.println(file.getName() + " 文件已被删除！");
            } else {
                System.out.println("文件删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void 读取文件内容() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("runoob.txt"));
        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }
        System.out.println(str);
    }

    @Test
    public void 文件写入() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("runoob.txt"));
            out.write("菜鸟教程");
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
        }
    }

    @Test
    public void 打印倒立的三角形() {
        for (int m = 1; m <= 4; m++) {
            //打印空格
            for (int n = 0; n <= m; n++) {
                System.out.print(" ");
            }
            //打印*
            for (int x = 1; x <= 7 - 2 * (m - 1); x++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void vaTest(int... no) {
        System.out.print("vaTest(int ...): "
                + "参数个数: " + no.length + " 内容: ");
        for (int n : no)
            System.out.print(n + " ");
        System.out.println();
    }

    static void vaTest(boolean... bl) {
        System.out.print("vaTest(boolean ...) " +
                "参数个数: " + bl.length + " 内容: ");
        for (boolean b : bl)
            System.out.print(b + " ");
        System.out.println();
    }

    static void vaTest(String msg, int... no) {
        System.out.print("vaTest(String, int ...): " +
                msg + "参数个数: " + no.length + " 内容: ");
        for (int n : no)
            System.out.print(n + " ");
        System.out.println();
    }

    @Test
    public void 可变参数使用() {
        int sum = 0;
        sum = sumvarargs(new int[]{10, 12, 33});
        System.out.println("数字相加之和为: " + sum);

        vaTest(1, 2, 3);
        vaTest("测试: ", 10, 20);
        vaTest(true, false, false);
    }

    private int sumvarargs(int... ints) {
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        return sum;
    }

    @Test
    public void 标签Label() {
        String strSearch = "This is the string in which you have to search for a substring.";
        String substring = "substring";
        boolean found = false;
        int max = strSearch.length() - substring.length();
        testlbl:
        for (int i = 0; i <= max; i++) {
            int length = substring.length();
            int j = i;
            int k = 0;
            while (length-- != 0) {
                if (strSearch.charAt(j++) != substring.charAt(k++)) {
                    continue testlbl;
                }
            }
            found = true;
            break testlbl;
        }
        if (found) {
            System.out.println("发现子字符串。");
        } else {
            System.out.println("字符串中没有发现子字符串。");
        }
    }


    @Test
    public void continue关键字() {
        StringBuffer searchstr = new StringBuffer("hello how are you. ");
        int length = searchstr.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (searchstr.charAt(i) != 'h')
                continue;
            count++;
            searchstr.setCharAt(i, 'h');
        }
        System.out.println("发现 " + count
                + " 个 h 字符");
        System.out.println(searchstr);
    }

    @Test
    public void instanceof关键字用法() {
        Object testObject = new ArrayList();
        displayObjectClass(testObject);
    }

    public static void displayObjectClass(Object o) {
        if (o instanceof Vector)
            System.out.println("对象是 java.util.Vector 类的实例");
        else if (o instanceof ArrayList)
            System.out.println("对象是 java.util.ArrayList 类的实例");
        else
            System.out.println("对象是 " + o.getClass() + " 类的实例");
    }

    /**
     * 0! = 1
     * 1! = 1
     * 2! = 2
     * 3! = 6
     * 4! = 24
     * 5! = 120
     * 6! = 720
     * 7! = 5040
     * 8! = 40320
     * 9! = 362880
     * 10! = 3628800
     */
    public void 阶乘() {
        for (int counter = 0; counter <= 10; counter++) {
            System.out.printf("%d! = %d\n", counter,
                    factorial(counter));
        }
    }

    public static long factorial(long number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    /**
     * 这个数列从第三项开始，每一项都等于前两项之和。
     */
    @Test
    public void 斐波那契数列() {
        for (int counter = 0; counter <= 10; counter++) {
            System.out.printf("Fibonacci of %d is: %d\n", counter, fibonacci(counter));
        }
    }

    public static long fibonacci(long number) {
        if ((number == 0) || (number == 1))
            return number;
        else
            return fibonacci(number - 1) + fibonacci(number - 2);
    }

    @Test
    public void 汉诺塔算法() {
        int nDisks = 3;
        doTowers(nDisks, 'A', 'B', 'C');
    }

    public static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("Disk 1 from " + from + " to " + to);
        } else {
            doTowers(topN - 1, from, to, inter);
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);
        }
    }

    @Test
    public void 泛型输出数组() {
        Integer[] integerArray = {1, 2, 3, 4, 5, 6};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        Character[] characterArray = {'H', 'E', 'L', 'L', 'O'};
        System.out.println("输出整型数组:");
        printArray(integerArray);
        System.out.println("\n输出双精度型数组:");
        printArray(doubleArray);
        System.out.println("\n输出字符型数组:");
        printArray(characterArray);
    }

    //泛型
    private <E> void printArray(E[] characterArray) {
        //输出
        for (E e : characterArray) {
            System.out.println(e);
        }
        System.out.println();
    }


    @Test
    public void 时间戳转换成时间() {
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));      // 时间戳转换成时间
        System.out.println("格式化结果：" + sd);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒");
        String sd2 = sdf2.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        System.out.println("格式化结果：" + sd2);
    }

    @Test
    public void 获取年份月份() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);

        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);
    }

    @Test
    public void 获取当前时间() throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
//        Date date = new Date();// 获取当前时间
//        System.out.println("现在时间：" + sdf.format(date)); // 输出已经格式化的现在时间（24小时制）

        //利用生日获取当前的年龄
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        //字符串变为时间Date类,解析p格式化f,pf
        String birthday = "1997年05月02日";
        Date d = sdf.parse(birthday);
        //获得时间毫秒值
        long myTime = d.getTime();
        //当前日期毫秒值
        long currentTime = new Date().getTime();
        System.out.println((currentTime - myTime) / 1000 / 60 / 60 / 24 / 365);
    }

    @Test
    public void 格式化时间() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date));
    }

    ;

    private void 判断数组是否相等() {
        int[] ary = {1, 2, 3, 4, 5, 6};
        int[] ary1 = {1, 2, 3, 4, 5, 6};
        int[] ary2 = {1, 2, 3, 4};
        System.out.println("数组 ary 是否与数组 ary1相等? ："
                + Arrays.equals(ary, ary1));
        System.out.println("数组 ary 是否与数组 ary2相等? ："
                + Arrays.equals(ary, ary2));
    }

    private void 数组中查找指定元素() {
        ArrayList<String> objArray = new ArrayList<String>();
        ArrayList<String> objArray2 = new ArrayList<String>();
        objArray2.add(0, "common1");
        objArray2.add(1, "common2");
        objArray2.add(2, "notcommon");
        objArray2.add(3, "notcommon1");
        objArray.add(0, "common1");
        objArray.add(1, "common2");
        System.out.println("objArray 的数组元素：" + objArray);
        System.out.println("objArray2 的数组元素：" + objArray2);
        System.out.println("objArray 是否包含字符串common2? ： "
                + objArray.contains("common2"));
        System.out.println("objArray2 是否包含数组 objArray? ："
                + objArray2.contains(objArray));
    }


    /**
     * 查找重复元素
     *
     * @param my_array
     */
    private void findDupicateInArray(int[] my_array) {
        int count = 0;
        for (int i = 0; i < my_array.length; i++) {
            for (int j = 0; j < my_array.length; j++) {  //不重复元素 //int k =j+1;k<a.length;k++改成int k=0;k<a.length;k++
                if (my_array[i] == my_array[j]) {
                    count++;
                }
            }
            if (count == 1) {
                System.out.println("不重复元素 : " + my_array[i]);
            }
            count = 0;
        }

//        int count=0;
//        for(int j=0;j<a.length;j++) {
//            for(int k =j+1;k<a.length;k++) {
//                if(a[j]==a[k]) {
//                    count++;
//                }
//            }
//            if(count==1)
//                System.out.println( "重复元素 : " +  a[j] );
//            count = 0;
//        }
    }

    /*
    数组扩容
     */
    public void arrayExpansion() {
        String[] names = new String[]{"A", "B", "C"};
        String[] extended = new String[5];
        extended[3] = "D";
        extended[4] = "E";
        System.arraycopy(names, 0, extended, 0, names.length);
        for (String str : extended) {
            System.out.println(str);
        }
    }

    /*
    数组填充
     */
    public void arrayPaddind() {
        int array[] = new int[6];
        Arrays.fill(array, 100); //array数组都填充100
        for (int i = 0, n = array.length; i < n; i++) {
            System.out.println(array[i]);
        }
        System.out.println();
        Arrays.fill(array, 3, 6, 50); //3-6位填充50
        for (int i = 0, n = array.length; i < n; i++) {
            System.out.println(array[i]);
        }
    }

    /*
    数组合并
     */
    public void heBinShuZhu() {
        String a[] = {"A", "E", "I"};
        String b[] = {"O", "U"};
        List list = new ArrayList(Arrays.asList(a));
        list.addAll(Arrays.asList(b));
        Object[] c = list.toArray();
        System.out.println(Arrays.toString(c));
    }

    /**
     * 反转数组
     *
     * @param a
     * @param n
     */
    public void reverse(int[] a, int n) {
//        int[] b = new int[n];
//        int j = n;
//        for (int i=0;i<n;i++){
//            b[j-1] = a[i];
//            j--;
//        }
//        System.out.println("反转后的数组：");
//        for (int k=0;k<n;k++){
//            System.out.println(b[k]);
//        }

//        int i, k, t;
//        for (i = 0; i < n / 2; i++) {
//            t = a[i];
//            a[i] = a[n - i - 1]; //第一个与最后一个交互，第二个与倒数第二个交换，以此类推
//            a[n - i - 1] = t;
//        }
//
//        System.out.println("反转后的数组是: \n");
//        for (k = 0; k < n; k++) {
//            System.out.println(a[k]);
//        }


    }

    /**
     * 调用Collections接口反转数组
     *
     * @param a
     */
    public void reverse(Integer a[]) {
        Collections.reverse(Arrays.asList(a));
        System.out.println(Arrays.asList(a));
    }

    /**
     * 迭代遍历
     * iterator.hasNext() 判断下一个位置还有没有元素
     */
    public void iTerator() {
        ArrayList<String> runoobs = new ArrayList<String>();
        runoobs.add("www.");
        runoobs.add("runoob");
        runoobs.add(".com");
        for (Iterator<String> iterator = runoobs.iterator(); iterator.hasNext(); System.out.println(iterator.next())) {

        }
    }


    @Test
    public void testList() {
        arrayList();
    }


    public void arrayList() {

        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        myNumbers.add(33);
        myNumbers.add(15);
        myNumbers.add(20);
        myNumbers.add(34);
        myNumbers.add(8);
        myNumbers.add(12);
        Collections.sort(myNumbers);  // 数字排序

        for (int i : myNumbers) {
            System.out.println(i);
        }

        /**
         *  频繁访问列表中的某一个元素。
         * 只需要在列表末尾进行添加和删除元素操作
         */
//        ArrayList<String> sites = new ArrayList<String>();
//        sites.add("Google");
//        sites.add("Runoob");
//        sites.add("Taobao");
//        sites.add("Weibo");
//        sites.set(2, "Wiki"); // 第一个参数为索引位置，第二个为要修改的值
//        System.out.println(sites);
    }


    public void linkedList() {

        /**
         *
         你需要通过循环迭代来访问列表中的某些元素。
         需要频繁的在列表开头、中间、末尾等位置进行添加和删除元素操作。
         */
        LinkedList<String> sites = new LinkedList<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        for (String i : sites) {
            System.out.println(i);
        }
    }

}
