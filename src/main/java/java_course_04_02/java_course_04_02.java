package java_course_04_02;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class java_course_04_02 {
	
    public static void main(String[] args) {
        // 替换为你的 CSV 文件绝对路径
        String inputFilePath = "C:\\Users\\234781\\eclipse-workspace\\database\\src\\main\\java\\java_course_04_02\\person.csv"; // 读取 CSV 文件的路径
        String baseFileName = "C:\\Users\\234781\\eclipse-workspace\\database\\src\\main\\java\\java_course_04_02\\new_person.csv"; // 写入 CSV 文件的路径
        String outputFilePath = generateFileNameWithTimestamp(baseFileName);
        // 从 CSV 文件中读取数据
        List<String[]> data = readCSV(inputFilePath);

        // 将数据写入到另一个 CSV 文件
        writeCSV(outputFilePath, data);
    }
    
    private static String generateFileNameWithTimestamp(String baseFileName) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        
        // 格式化时间戳
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);

        // 生成带时间戳的文件名
        return baseFileName + "_" + timestamp  +".csv";
    }

    
    private static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 读取 CSV 文件的每一行
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                data.add(fields);
            }
        } catch (IOException e) {
            System.err.println("無法讀取文件: " + e.getMessage());
        }
        return data;
    }

    private static void writeCSV(String filePath, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                // 将数组中的字段用逗号连接起来
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("無法寫入文件: " + e.getMessage());
        }
    }
}
