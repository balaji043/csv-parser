package com.bam.dev.csv;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import static com.bam.dev.csv.CSVConverter.CSVConfig.CSVColumn;

public class CSVConverter<T> {

    public static final String DEFAULT_EXTENSION = ".csv";
    public static final String DEFAULT_SEPARATOR = ",";
    public static final String DEFAULT_EMPTY_VALUE = "";
    public static final String DEFAULT_NEW_LINE = "\n";
    public static final String DEFAULT_PATH_VARIABLE_SEPARATOR = "\\.";

    private final CSVConfig<T> defaultCSVConfig;

    public CSVConverter() {
        defaultCSVConfig = null;
    }

    public CSVConverter(CSVConfig<T> defaultCSVConfig) {
        this.defaultCSVConfig = defaultCSVConfig;
    }

    public File convert(List<T> data) {
        if (defaultCSVConfig == null)
            throw new RuntimeException("Default CSV Config Not found");
        return convert(data, defaultCSVConfig);
    }

    public File convert(Collection<T> data, CSVConfig<T> csvConfig) {
        File file = new File(csvConfig.getFileName() + CSVConverter.DEFAULT_EXTENSION);
        try (FileWriter fileWriter = new FileWriter(file)) {
            StringBuilder rows = new StringBuilder();
            rows.append(csvConfig.getHeaderNames());
            for (T object : data) {
                rows.append(CSVConverter.DEFAULT_NEW_LINE);
                StringBuilder singleRow = new StringBuilder();
                for (CSVColumn csvColumn : csvConfig.getCsvColumns()) {
                    Object obj = this.get(csvColumn.getPropertyName(), object);
                    singleRow.append(obj).append(CSVConverter.DEFAULT_SEPARATOR);
                }
                rows.append(singleRow.toString());
            }
            fileWriter.write(rows.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return file;
    }

    public Object get(String path, T obj) {
        String[] steps = path.split(CSVConverter.DEFAULT_PATH_VARIABLE_SEPARATOR);
        Object result = obj;
        try {
            for (String step : steps) {
                if (result == null)
                    return CSVConverter.DEFAULT_EMPTY_VALUE;
                Method method = result.getClass().getMethod(createGetterName(step));
                result = method.invoke(result);
                if (result instanceof List) {
                    List<?> list = (List<?>) result;
                    result = list.get(0);
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String createGetterName(String name) {
        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    @Getter
    @Setter
    public static class CSVConfig<T> {

        private final String fileName;
        private final String headerNames;
        private final CSVColumn[] csvColumns;
        private Class<T> tClass;

        public CSVConfig(String fileName, CSVColumn... csvColumns) {
            this.fileName = fileName;
            this.csvColumns = csvColumns;
            StringBuilder stringBuilder = new StringBuilder();
            for (CSVColumn csvColumn : csvColumns)
                stringBuilder.append(csvColumn.getDisplayName()).append(CSVConverter.DEFAULT_SEPARATOR);
            this.headerNames = stringBuilder.toString();
        }

        @Getter
        @Setter
        public static class CSVColumn {

            private final String displayName;
            private final String propertyName;

            public CSVColumn(String displayName, String propertyName) {
                this.displayName = displayName;
                this.propertyName = propertyName;
            }
        }
    }
}