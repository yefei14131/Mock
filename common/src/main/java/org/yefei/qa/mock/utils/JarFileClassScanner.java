package org.yefei.qa.mock.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author: yefei
 * @date: 2018/11/7 20:34
 */

@Slf4j
public class JarFileClassScanner {

    private JarClassFilter jarClassFilter;

    public interface JarClassFilter {
        boolean match(String className);
    }

    public JarFileClassScanner setJarClassFilter(JarClassFilter jarClassFilter){
        this.jarClassFilter = jarClassFilter;
        return this;
    }

    public List<Class> loadClassByJarFile(File file) throws IOException, ClassNotFoundException {

        List<Class> classes = new ArrayList<>();
        if (!file.exists() || !file.getPath().endsWith(".jar")){
            return classes;
        }

        JarFile jarFile = null;
        try {

            jarFile = new JarFile(file);
            Enumeration<JarEntry> entries = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + file.getPath() + "!/")};
            URLClassLoader cl = new URLClassLoader(urls, this.getClass().getClassLoader());
//        URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (entries.hasMoreElements()) {
                JarEntry je = entries.nextElement();

                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                Class c = null;

                if (this.jarClassFilter != null && !this.jarClassFilter.match(className)) {
                    continue;
                }

                c = cl.loadClass(className);
                classes.add(c);
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        } finally {
            if (jarFile != null) {
                jarFile.close();
            }
        }

        return classes;
    }


    public List<Class> loadClassByJarDir(File dir) throws IOException, ClassNotFoundException {
        ArrayList<Class> list = new ArrayList<>();

        if ( !dir.exists()){
            return list;
        }

        if(dir.isDirectory()){
            Arrays.stream(dir.listFiles()).forEach(subFile->{
                try {
                    list.addAll(loadClassByJarDir(subFile));
                } catch (IOException e) {
                    log.error("", e);
                } catch (ClassNotFoundException e) {
                    log.error("", e);
                }
            });
        }else{
            list.addAll(loadClassByJarFile(dir));
        }

        return list;
    }


}





