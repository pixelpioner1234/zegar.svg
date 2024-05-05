import org.w3c.dom.NodeList;

import java.io.*;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtils {

    public static void ShowClockSvgArrowValues(String path){

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // Шаблон для поиска строк с тегами <line>
            //Pattern linePattern = Pattern.compile("<line x1=\"(.*?)\" y1=\"(.*?)\" x2=\"(.*?)\" y2=\"(.*?)\" stroke=\"(.*?)\" stroke-width=\"(.*?)\".*?>");
            Pattern linePattern = Pattern.compile("<line x1=\"(.*)\" y1=\"(.*)\" x2=\"(.*)\" y2=\"(.*)\" stroke=\"(.*)\" stroke-width=\"(.*)\".*>");

            // Читаем файл построчно и ищем строки с тегами <line>
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = linePattern.matcher(line);
                if (matcher.find()) {
                    String x1 = matcher.group(1);
                    String y1 = matcher.group(2);
                    String x2 = matcher.group(3);
                    String y2 = matcher.group(4);
                    String strokeColor = matcher.group(5);
                    String strokeWidth = matcher.group(6);

                    System.out.println("x1: " + x1 + ", y1: " + y1 + ", x2: " + x2 + ", y2: " + y2 +
                            ", stroke: " + strokeColor + ", stroke-width: " + strokeWidth);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    public static void GenerateClockSvgWithoutArrow(String srcPath, String destPath){
        try {
            // Создаем объект FileReader для чтения исходного SVG файла
            FileReader fileReader = new FileReader(srcPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Создаем объект FileWriter для записи нового SVG файла
            FileWriter fileWriter = new FileWriter(destPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains("<line")) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine(); // Добавляем новую строку
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }





    public static void GenerateClockSvgWithDegree(String srcPath, String destPath,String degree){
        try {
            FileReader fileReader = new FileReader(srcPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(destPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("<line")) {
                    line = line.replace("/>", " transform=\"rotate(" + degree + ")\" />");
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }




    public static void GenerateClockSvgWithMultipleDegree(String srcPath, String destPath,String degreeMin,String degreeHour,String degreeSec){
        try {
            FileReader fileReader = new FileReader(srcPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(destPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("<line")) {
                    if (line.contains("stroke=\"black\"") && line.contains("stroke-width=\"4\"")) {
                        line = line.replace("/>", " transform=\"rotate(" + degreeMin + ")\" />");
                    }
                    else if (line.contains("stroke=\"black\"")) {
                        line = line.replace("/>", " transform=\"rotate(" + degreeHour + ")\" />");
                    }

                    else if (line.contains("stroke=\"red\"")) {
                        line = line.replace("/>", " transform=\"rotate(" + degreeSec + ")\" />");
                    }
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
