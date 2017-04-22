package com.framgia.users.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;


/**
 * Created by FRAMGIA\duong.van.tien on 06/03/2017.
 *
 */
public final class Helpers {
    // file name
    private static final String CGS_CONFIG_FILE = "cgs_config.properties";
    private static final String CGS_SERVER_FILE = "spring-server-config.properties";

    // for logging
    // private static final Logit m_log = Logit.getInstance(Helpers.class);

    // singleton
    private Helpers() {
        ;
    }

    /**
     * Loads the given property file by searching the CLASSPATH or
     * java.class.path system property value and returns the Properties object.
     *
     * @param propertyFileName
     *            Name of the property file.
     * @return Returns Properties object containing the contents of the
     *         specified Properties file.
     * @exception java.io.FileNotFoundException
     *                Thrown if the given property file could not found in the
     *                CLASSPATH.
     */
    @SuppressWarnings("unused")
    public static Properties getProperties(String propertyFileName)
            throws java.io.FileNotFoundException {

        InputStream is = null;
        try {
            String configPath = System.getProperty("configPath");
            File file = new File(configPath + File.separator + propertyFileName);
            is = new FileInputStream(file);

            if (is == null) {
                throw new FileNotFoundException(propertyFileName + " not found");
            }

            // load properties
            Properties props = new Properties();
            props.load(is);
            return props;

        } catch (Exception ignore) {
            ignore.printStackTrace();
            throw new java.io.FileNotFoundException(propertyFileName
                    + " not found");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Loads the given property file by searching the CLASSPATH or
     * java.class.path system property value and returns the Properties object.
     *
     * @param propertyFileName
     *            Name of the property file.
     * @param onNotFound
     *            Properties to return if the named properties file is not
     *            found.
     * @return Returns Properties object containing the contents of the
     *         specified Properties file if found, or the onNotFound value if
     *         not found.
     */
    public static Properties getProperties(String propertyFileName,
                                           Properties onNotFound) {
        try {
            return getProperties(propertyFileName);
        } catch (java.io.FileNotFoundException fe) {
            // m_log.warn("Properties file not found: " + propertyFileName);
            return onNotFound;
        }
    }

    public static int getIntegerProperty(Properties p, String name,
                                         int defaultValue) {
        String l = p.getProperty(name);
        return l == null ? defaultValue : Integer.valueOf(l).intValue();
    }

    public static String getStringProperty(Properties p, String name,
                                           String defaultValue) {
        String propertyValue = p.getProperty(name);
        return propertyValue == null ? defaultValue : propertyValue;
    }

    public static boolean getBooleanProperty(Properties p, String name,
                                             boolean defaultValue) {
        String propertyValue = p.getProperty(name);
        return propertyValue == null ? defaultValue
                : new Boolean(propertyValue).booleanValue();
    }

    public static String createStringId(String header, int n, long id) {
        NumberFormat idFormat = createNumberFormat(n);
        return header + idFormat.format(id);
    }

    public static String createStringId(String header, int n, int id) {
        NumberFormat idFormat = createNumberFormat(n);
        return header + idFormat.format(id);
    }

    /**
     * get value from cgs_config.properties
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String getCgsProperty(String key) throws Exception {
        return getStringProperty(getProperties(CGS_CONFIG_FILE), key, "");
    }

    /**
     * get value from spring-server-config.properties
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String getServerProperty(String key) throws Exception {
        return getStringProperty(getProperties(CGS_SERVER_FILE), key, "");
    }

    private static NumberFormat createNumberFormat(int n) {
        NumberFormat idFormat = null;
        try {

            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < n; i++) {
                sb = sb.append("0");
            }
            idFormat = new DecimalFormat(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idFormat;
    }

    public static String getFullPath(String fileName)
            throws java.io.FileNotFoundException {
        try {
            String configPath = System.getProperty("configPath");
            if (configPath == null) {
                configPath = "";
            }
            // System.out.println("configPath=" + configPath);
            // System.out.println("File.separatorChar = " + File.separatorChar);
            return configPath + File.separator + fileName;

        } catch (Exception ignore) {
            ignore.printStackTrace();
            throw new java.io.FileNotFoundException(fileName);
        }

    }

    public static boolean isEmpty(List value) {
        return value == null || value.size() == 0;
    }

    public static boolean isEmpty(Collection value) {
        return value == null || value.size() == 0;
    }

    public static boolean isEmpty(Queue value) {
        return value == null || value.size() == 0;
    }

    public static boolean isEmpty(Set value) {
        return value == null || value.size() == 0;
    }

    public static boolean isEmpty(String value) {
        return value == null || value.equals("") || value.trim().equals("");
    }

    public static boolean isEmpty(String value, boolean isTrim) {
        if (isTrim) {
            return value == null || value.trim().equals("");
        } else {
            return value == null || value.equals("");
        }
    }

    public static boolean isEmpty(Object[] value) {
        return value == null || value.length == 0;
    }

    public static boolean isEmpty(Map value) {
        return value == null || value.size() == 0;
    }

    public static Integer toInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal toBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return new BigDecimal(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    public static String getConfigPath() {
        return System.getProperty("configPath");
    }

    public static String getExtFile(String file) {
        if (isEmpty(file)) {
            return "jpg";
        }
        if (file.contains(".")) {
            return "jpg";
        }
        return file.substring(file.lastIndexOf(".") + 1, file.length());
    }

    public static String getFileName(String file) {
        if (isEmpty(file)) {
            return null;
        }
        if (file.contains(File.separator)) {
            return null;
        }
        return file.substring(file.lastIndexOf(File.separator) + 2,
                file.length());
    }

    public static boolean equals(String value1, String value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        if (value1 == null || value2 == null) {
            return false;
        }
        return value1.equals(value2);
    }

    public static boolean equals(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        if (value1 == null || value2 == null) {
            return false;
        }
        return value1.equals(value2);
    }

    public static <T> List<T> toList(T[] ts) {
        if (isEmpty(ts)) {
            return null;
        }
        List<T> ret = new ArrayList<T>();
        for (T t : ts) {
            ret.add(t);
        }
        return ret;
    }

    public static <T extends Comparable<T>> Set<T> makeTreeSet() {
        Set<T> stateSet = new TreeSet<T>(new Comparator<T>() {
            public int compare(T a, T b) {
                if (a == null && b == null) {
                    return 0;
                }
                if (a == null && b != null) {
                    return -1;
                }
                if (a != null && b == null) {
                    return 1;
                }
                T _a = (T) a;
                T _b = (T) b;

                return _a.compareTo(_b);
            }
        });
        return stateSet;
    }

    public static String formatNumber(BigDecimal value) {
        if (value == null) {
            return null;
        }
        try {
            return value.setScale(2, RoundingMode.HALF_DOWN).toString();
        } catch (Exception e) {
            return value.toString();
        }
    }

    public static void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    public static <T extends Comparable<T>> void addToSortedList(List<T> list,
                                                                 T value, boolean asc) {
        addToSortedList(list, value, asc, 0);
    }

    public static <T extends Comparable<T>> void addToSortedList(List<T> list,
                                                                 T value, boolean asc, int limit) {
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            list.add(value);
        } else if ((asc && value.compareTo(list.get(0)) <= 0)
                || (!asc && value.compareTo(list.get(0)) >= 0)) {
            list.add(0, value);
        } else if (list.size() == 1) {
            list.add(value);
        } else {
            boolean added = false;
            for (int i = 1; i < list.size(); i++) {
                if ((asc && value.compareTo(list.get(i)) <= 0)
                        || (!asc && value.compareTo(list.get(i)) >= 0)) {
                    list.add(i, value);
                    added = true;
                    break;
                }
            }
            if (!added) {
                list.add(value);
            }
        }
        while (limit > 0 && list.size() > limit) {
            list.remove(list.size() - 1);
        }
    }
    
    public static String convertDatetoString(Date date){
    	try{
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    		return dateFormat.format(date);
    		
    	}catch(Exception e){
    		return "";
    	}
    }

    public static String convertDateTimetoString(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return dateFormat.format(date);

        } catch (Exception e) {
            return "";
        }
    }

    public static Date convertStringtoDate(String strDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            return sdf.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static long getDayBetweenTwoDates(Date startDate, Date endDate) {
        Long diff = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
	
	
}
