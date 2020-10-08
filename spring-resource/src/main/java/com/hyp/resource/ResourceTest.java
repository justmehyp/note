package com.hyp.resource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;

/**
 * 当我们拼接多个字符串为一个路径，最终拼接而成的字符串中间可能包含多个斜杠，而用户期望的是程序能把它们当做一个斜杠处理。
 * 例如: "/path/to/a/folder/" + "/path/of/another/folder/" + "/myfile"
 * 以上操作的结果将是: "/path/to/a/folder//path/of/another/folder//myfile"
 * 如你所见，这个结果字符串出现了2处双斜杠。实际上，它应该被解析成：
 * "/path/to/a/folder/path/of/another/folder/myfile"
 *
 * jdk的java.io.File类会处理这个问题，比如：
 * File file = new File("/path/to/a/folder/path/of/another/folder/myfile");
 * System.out.println(file.getPath());  // 结果将会输出：/path/to/a/folder/path/of/another/folder/myfile
 *
 * 然而我发现，Spring的Resource实现没有做这个处理，我们挑选Resource接口的一个实现类: ClassPathResource 进行验证：
 * ClassPathResource classPathResource = new ClassPathResource("path/to/a/folder//path/of/another/folder//myfile");
 * System.out.println(classPathResource.getPath()); // 结果将会输出：path/to/a/folder//path/of/another/folder//myfile
 *
 * 我认为这不是大多数人能够接受的输出。它应该被修正。这就是我提这个PR的原因，期望它能够被采用
 *
 *
 *
 * When we splice multiple strings into a path, the final concatenated string may contain multiple slashes,
 * and users expect that the program can treat them as a slash.
 *
 * For example,
 *     "/path/to/a/folder/" + "/path/of/another/folder/" + "/myfile"
 * The result of the above operation will be:
 *     "/path/to/a/folder//path/of/another/folder//myfile"
 *
 * As you can see, there are two double-slashes in the result string. In fact, it should be interpreted as:
 *    "/path/to/a/folder/path/of/another/folder/myfile"
 *
 * JDK's java.io.File Class handles this problem, such as:
 *    File file = new File("/path/to/a/folder/path/of/another/folder/myfile");
 *    System.out.println(file.getPath());  /path/to/a/folder/path/of/another/folder/myfile
 *
 * However, I found that spring's Resource implementation did not do this.
 * We selected an implementation class of the resource interface: classpathresource for verification:
 *    ClassPathResource classPathResource = new ClassPathResource("path/to/a/folder//path/of/another/folder//myfile");
 *    System.out.println(classPathResource.getPath());  // the result will be output: path/to/a/folder//path/of/another/folder//myfile
 *

 * I don't think this is an output that most people can accept, and it could be fixed.
 * This's why I mentioned this PR, hoping that it can be adopted :)
 *
 */
@SpringBootApplication
public class ResourceTest {

    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:java/lang/*");
        System.out.println(resources);


        File file = new File("myfile");
        System.out.println(file.getPath());

        file = new File("myfile/");
        System.out.println(file.getPath());

        file = new File("myfile//");
        System.out.println(file.getPath());

        file = new File("///");
        System.out.println(file.getPath());

        System.out.println("---------------------");

        ClassPathResource classPathResource = new ClassPathResource("myfile");
        System.out.println(classPathResource.getPath());

        classPathResource = new ClassPathResource("myfile/");
        System.out.println(classPathResource.getPath());

        classPathResource = new ClassPathResource("myfile//");
        System.out.println(classPathResource.getPath());

        classPathResource = new ClassPathResource("///");
        System.out.println(classPathResource.getPath());

        System.out.println("---------------------");

        FileSystemResource fileSystemResource = new FileSystemResource("myfile");
        System.out.println(fileSystemResource.getPath());

        fileSystemResource = new FileSystemResource("myfile/");
        System.out.println(fileSystemResource.getPath());

        fileSystemResource = new FileSystemResource("myfile//");
        System.out.println(fileSystemResource.getPath());

        fileSystemResource = new FileSystemResource("///");
        System.out.println(fileSystemResource.getPath());
    }
}
