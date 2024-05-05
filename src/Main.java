public class Main {
    public static void main(String[] args){

        String path = "C:\\kol_2024\\zegar.svg";
        //FileUtils.ShowClockSvgArrowValues(path);
        System.out.println("\n");

        String destPath ="C:\\kol_2024\\zegarWithoutHands.svg";
        FileUtils.GenerateClockSvgWithoutArrow(path,destPath );


        String destPath2 ="C:\\kol_2024\\zegarWithOneDegree.svg";
        FileUtils.GenerateClockSvgWithDegree(path,destPath2, "180");
        //FileUtils.ShowClockSvgArrowValues(destPath2);


        String destPath3 ="C:\\kol_2024\\zegarWithMultipleDegree.svg";
        FileUtils.GenerateClockSvgWithMultipleDegree(path,destPath3, "180","0","120");
        //FileUtils.ShowClockSvgArrowValues(destPath2);


    }
}
