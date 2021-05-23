package com.wei.utils;

import com.wei.dto.ApiMessage;
import com.wei.utils.excel.ExcelUtils;
import com.wei.utils.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.stream.Collectors;

/**
 * @Describe Utils
 * @Author a_pen
 * @Date 2020年09月08日
 */
public class ClassPathPackageScannerUtils {

    private static final Logger log = LoggerFactory.getLogger(ClassPathPackageScannerUtils.class);
    private String backPackage;
    private ClassLoader classLoader;
    private List<String> fileList = new ArrayList<>();


    public ClassPathPackageScannerUtils(String backPackage) {
        this.backPackage = backPackage;
        this.classLoader = getClass().getClassLoader();
    }

    public ClassPathPackageScannerUtils(String backPackage, ClassLoader classLoader) {
        this.backPackage = backPackage;
        this.classLoader = classLoader;
    }

    /**
     * 扫描backPackage下所有文件，存放到集合List中
     * @return
     */
    public List<String>  doScannerFile(){
        log.info("start doScanner...");
        String path = StringUtil.dotToSplash(this.backPackage);
        URL resource = this.classLoader.getResource(path);
        String filePath = StringUtil.getRootPath(resource);
        List<String> classNames = null;
        if(StringUtil.isEmpty(filePath, false)){return null;}
        if(StringUtil.isExtension(filePath, ".jar")){
            log.info("filePath is .jar:{}", filePath);
            classNames = readFromJarFile(filePath, path);
        }else {
            log.info("filepath is directory:{}" , filePath);
            classNames =  readFromDirectory(filePath);
        }
        return classNames;
    }

    /**
     * 从target/class下获去所有文件
     * @param filePath
     * @return
     */
    private List<String> readFromDirectory(String filePath) {
        File file = new File(filePath);
        for (File listFile : file.listFiles()) {
            if(listFile.isDirectory()){
                readFromDirectory(listFile.getPath());
            }else {
                fileList.add(StringUtil.getClassPath(listFile.getPath()));
            }
        }
        return fileList;
    }

    /**
     * 从jar包中下获去所有文件
     * @param filePath
     * @param path
     * @return
     */
    private List<String> readFromJarFile(String filePath, String path) {
        List<String> list = new ArrayList<>();
        try {
            JarInputStream jarInputStream = new JarInputStream(new FileInputStream(filePath));
            JarEntry jarEntry = jarInputStream.getNextJarEntry();
            while (null != jarEntry){
                String name = jarEntry.getName();
                if(name.startsWith(path)){
                    list.add(StringUtil.getClassPath(name));
                }
                jarEntry = jarInputStream.getNextJarEntry();
            }
        } catch (IOException e) {
            log.error("readFromJarFile is error", e);
        }
        return list;
    }

    /**
     * 获取所有api路径。
     * @return
     */
    public List<ApiMessage> getApiMessage() {
        List<String> files = doScannerFile();
        if (null == files || files.size() == 0) {
            return null;
        }
        List<String> collect = files.stream().filter(str -> str.endsWith(".class")).collect(Collectors.toList());
        if (null == collect || collect.size() == 0) {
            return null;
        }
        List<ApiMessage> result = new ArrayList<>();
        for (String className : collect) {
            try {
                Class<?> aClass = Class.forName(className.substring(0, className.indexOf(".class")));
                if(aClass.getAnnotation(RestController.class) != null || aClass.getAnnotation(Controller.class) != null){
                    String baseUrl[] = null;
                    if(aClass.getAnnotation(RequestMapping.class) != null ){
                        baseUrl = aClass.getAnnotation(RequestMapping.class).value();
                    }
                    Method[] methods = aClass.getDeclaredMethods();
                    if (methods != null && methods.length > 0) {
                        for (Method method : methods) {
                            Annotation[] mAnnotations = method.getAnnotations();
                            if (mAnnotations == null || mAnnotations.length == 0) {
                                continue;
                            }
                            ApiMessage message = new ApiMessage();
                            message.setClassName(aClass.getSimpleName());
                            message.setMethod(method.getName());
                            for (Annotation annotation : mAnnotations) {
                                if (annotation instanceof RequestMapping) {
                                    RequestMapping ma = method.getAnnotation(RequestMapping.class);
                                    String[] value = ma.value();
                                    message.setMethodType(ma.method()[0].name());
                                    message.setApiUrl(mergeApiPath(baseUrl, value));
                                    result.add(message);
                                } else if (annotation instanceof GetMapping) {
                                    GetMapping ma = method.getAnnotation(GetMapping.class);
                                    String[] value = ma.value();
                                    message.setMethodType(RequestMethod.GET.name());
                                    message.setApiUrl(mergeApiPath(baseUrl, value));
                                    result.add(message);
                                } else if (annotation instanceof PostMapping) {
                                    PostMapping ma = method.getAnnotation(PostMapping.class);
                                    String[] value = ma.value();
                                    message.setMethodType(RequestMethod.POST.name());
                                    message.setApiUrl(mergeApiPath(baseUrl, value));
                                    result.add(message);
                                } else if (annotation instanceof DeleteMapping) {
                                    DeleteMapping ma = method.getAnnotation(DeleteMapping.class);
                                    String[] value = ma.value();
                                    message.setMethodType(RequestMethod.DELETE.name());
                                    message.setApiUrl(mergeApiPath(baseUrl, value));
                                    result.add(message);
                                } else if (annotation instanceof PutMapping) {
                                    PutMapping ma = method.getAnnotation(PutMapping.class);
                                    String[] value = ma.value();
                                    message.setMethodType(RequestMethod.PUT.name());
                                    message.setApiUrl(mergeApiPath(baseUrl, value));
                                    result.add(message);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return result;
    }


    private static String mergeApiPath(String[] var1, String[] var2){
        if(var1 == null || var1.length == 0){
            return var2[0];
        }
       if(var2 == null || var2.length == 0){
           return var1[0];
       }
        if(var1[0].endsWith("/")){
            return var1[0] + (var2[0].startsWith("/") ? var2[0].substring(1) : var2[0]);
        }else {
            return var1[0] + (var2[0].startsWith("/") ?var2[0] : "/" + var2[0]);
        }
    }

    public static void main(String[] args) {
       ClassPathPackageScannerUtils utils = new ClassPathPackageScannerUtils("com.wei");
        List<ApiMessage> apiMessage = utils.getApiMessage();
        ExcelUtils<ApiMessage> aMessage =  new ExcelUtils<>();
        aMessage.exportExcelToPath(ApiMessage.class ,apiMessage, "apiUrl 导出", "D:\\workFile\\apiurl\\apiurl.xls");

    }

}
