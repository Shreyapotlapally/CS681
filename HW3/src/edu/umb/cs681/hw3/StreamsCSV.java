package edu.umb.cs681.hw3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsCSV {

    public static void main(String[] args) {

        Path path = Path.of("Data", "PVIData.csv");

        try( Stream<String> lines = Files.lines(path) ){
            List<List<String>> matrix = lines.map(line -> {
                        return Stream.of( line.split(",") )
                                .map(value->value.substring(0, value.length()))
                                .collect( Collectors.toList() ); })
                    .collect( Collectors.toList() );


            List newyork = matrix.stream().filter((i) -> i.get(4).contains("New York")).collect(Collectors.toList());
            String totalCases = matrix.stream().filter((i) -> i.get(4).contains("New York"))
                    .map((i) -> i.get(6)).reduce("0", (temp, result) -> String.valueOf(Integer.parseInt(temp) + Integer.parseInt(result)));

            List queens = matrix.stream().filter((i) -> i.get(5).contains("Queens")).collect(Collectors.toList()).get(0);

            List columbia = matrix.stream().filter((i) -> i.get(5).contains("Columbia")).collect(Collectors.toList()).get(0);

            float queensIR = Float.parseFloat((String) queens.get(8));
            float columbiaIR = Float.parseFloat((String) columbia.get(8));

            System.out.println("Total no. of cases in New York: " + totalCases);
            System.out.println("Infection rate in Queens County: " + queens.get(8));
            System.out.println("Infection rate in Columbia County: " + columbia.get(8));

            if(queensIR > columbiaIR){
                System.out.println("Infection Rate in Queens is "+(queensIR-columbiaIR)+" times more than Columbia");
            }else{
                System.out.println("Infection Rate in Columbia is "+(columbiaIR-queensIR)+" times more than Queens");
            }

        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}