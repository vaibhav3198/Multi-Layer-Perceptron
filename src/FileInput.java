import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInput {

    public TrainingSet[] PrepareTrainingSet(String fileName,int numRows, int numInputs)
    {
        Path pathToFile = Paths.get(fileName);
        TrainingSet[] trainingSet = new TrainingSet[numRows];
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {


            String line = br.readLine();    // read the first line from the  file

            int row =0;
            while (line!=null && row<numRows)  // loop until all lines are read
            {
                String[] attributes = line.split(";");
                double[] inputs = new double[numInputs];
                int output;

                for(int i=0;i<inputs.length;i++)
                {
                    inputs[i] = Double.parseDouble(attributes[i]);
                }
                output = Integer.parseInt(attributes[attributes.length-1]);
                trainingSet[row] = new TrainingSet(inputs,output);
                line = br.readLine();
                row++;
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return trainingSet;

    }

    public static void main(String[] args) {
        FileInput fi = new FileInput();
        String path = "C:\\Users\\HP\\Desktop\\IIIT\\3rd Year\\5th Sem\\SC\\MLP Java\\src\\winequality-red.txt";
        TrainingSet[] trainingSet = fi.PrepareTrainingSet(path,1500,11);
        for (int i=0;i<trainingSet.length;i++)
        {
            trainingSet[i].Display();
        }
    }
}
