import javax.swing.*;

public class MultiLayerPerceptron
{
    private Layer[] layers;
    private double learningRate;

    public MultiLayerPerceptron(int[] layers,double learningRate)
    {
        this.layers = new Layer[layers.length-1];
        for (int i=0;i<layers.length-1;i++)
        {
            this.layers[i] = new Layer(layers[i],layers[i+1]);
        }
        this.learningRate = learningRate;
    }

    public double[] Guess(double[] inputs)
    {
        //double[] guess = new double[this.layers[this.layers.length - 1].GetNumberOfNeurons()];   //size of guess is the number of neurons in the output layer
        double[] temp = inputs;
        for(int i=0;i<layers.length;i++)
        {
            temp = layers[i].Guess(temp);
        }
        return temp;
    }

    public int Predict(double[] inputs)
    {
        double[] guess = Guess(inputs);
        double max = -2.0;    //since guess uses sigmoid, -2.0 is smaller than its range
        int pos=-1;
        for(int i=0;i<guess.length;i++)
        {
            if(guess[i]>max)
            {
                max=guess[i];
                pos = i;
            }
        }
        return (pos);    //assuming that class number starts from 1
    }

    public void Train(TrainingSet[] trainingSet)
    {
        System.out.println();
        System.out.println("Training...");
        final int numEpoch = 250;
        double[] error_per_epoch = new double[numEpoch];
        for(int count =0; count<numEpoch; count++)
        {
            System.out.println("Epoch: " + count);
            for(int i=0;i<trainingSet.length;i++)
            {
                /*************Creating the actual mlp output vector************/
                double[] mlp_output = Guess(trainingSet[i].input);
//                System.out.println();
//                System.out.println("mlp_output: ");
//                DisplayVector(mlp_output);

                /*****************creating the expected output vector**********************/
                double[] expected_output = new double[layers[layers.length-1].GetNumberOfNeurons()];
                for(int j=0;j<expected_output.length;j++)
                {
                    expected_output[j]=0d;
                }
                if(trainingSet[i].output>=0 && trainingSet[i].output<layers[layers.length-1].GetNumberOfNeurons())   //just a safety check
                {
                    expected_output[trainingSet[i].output] = 1.0;
                }
                else
                {
                    throw new RuntimeException("Shouldn't be here!");
                }
//                System.out.println();
//                System.out.println("expected_output: ");
//                DisplayVector(expected_output);

                /******************calculating the error vector********************/
                double[] output_error = new double[layers[layers.length-1].GetNumberOfNeurons()];
                for(int j=0;j<expected_output.length;j++)
                {
                    output_error[j] = expected_output[j] - mlp_output[j];
                    output_error[j] *= TransferDerivative(mlp_output[j]);
                }
//                System.out.println();
//                System.out.println("output_error: ");
//                DisplayVector(output_error);

                /******************setting the error vector of the last layer********************/
                for(int j=0;j<layers[layers.length-1].GetNumberOfNeurons();j++)
                {
                    layers[layers.length-1].error_layer[j] = output_error[j];
                }

                /****************back-propogating the error*******************/
                BackPropogate(trainingSet[i]);    //also updates weight vector
            }
            /*Calculating the average error at the end of epoch*/
            double errorOfEpoch = 0;
            int num=0;
            for(int j=0;j<layers[layers.length-1].GetNumberOfNeurons();j++)
            {
                errorOfEpoch += layers[layers.length-1].error_layer[j];
                num++;
            }
            errorOfEpoch /= num;
            error_per_epoch[count] = errorOfEpoch;
        }
        // Building a graph
        System.out.println("Error");
        for (int j=0;j<error_per_epoch.length;j++)
        {
            System.out.print(error_per_epoch[j]+" ");
        }
        JFrame f = new JFrame("Epoch vs Error");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Graph(error_per_epoch));
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }

    private void BackPropogate(TrainingSet trainingInstance)
    {
        /****************Back propogating the error**************************/
        for(int i=layers.length-2;i>=0;i--)     //for every layer but the last layer
        {
            for(int j=0;j<layers[i].neurons.length;j++)   //for every neuron in the ith layer
            {
                for(int k=0;k<layers[i+1].neurons.length;k++)     //for every neuron in (i+1)th layer
                {
                        layers[i].error_layer[j] += layers[i+1].error_layer[k] * layers[i+1].neurons[k].weights[j];
                        layers[i].error_layer[j] *= TransferDerivative(layers[i].output[j]);
                }
            }
        }

        /***************Updating the weights***************/
        for(int i=layers.length-1;i>0;i--)    //for every layer but the first layer
        {
            for(int j=0;j<layers[i].GetNumberOfNeurons();j++)  //for every neuron in the ith layer
            {
                for(int k=0;k<layers[i].neurons[j].weights.length;k++)   //for every weight of the neuron
                {
                    layers[i].neurons[j].weights[k] += learningRate * layers[i].error_layer[j] * layers[i-1].output[k];
                }
                layers[i].neurons[j].bias += learningRate * layers[i].error_layer[j];
            }
        }

        //for first layer
        for(int j=0;j<layers[0].GetNumberOfNeurons();j++)  //for every neuron in the first layer
        {
            for(int k=0;k<layers[0].neurons[j].weights.length;k++)  //for every weight of the neuron
            {
                layers[0].neurons[j].weights[k] += learningRate * layers[0].error_layer[j] * trainingInstance.input[k];
            }
        }
    }

    private double TransferDerivative(double x)
    {
        return x*(1-x);
    }

    public void Display()
    {
        System.out.println("Number of layers: " + layers.length);
        for (int i=0;i<layers.length;i++)
        {
            System.out.println("Layer: " + (i+1));
            layers[i].Display();
            System.out.println();
        }
    }

    private void DisplayVector(double[] array)
    {
        for (int i=0;i<array.length;i++)
        {
            System.out.print(array[i] + " ");
        }
    }
}
