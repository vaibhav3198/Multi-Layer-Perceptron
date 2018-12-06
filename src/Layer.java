

public class Layer
{
    public Neuron[] neurons;
    public double[] output;
    public double[] error_layer;

    public Layer(int numInputs,int numNeurons)
    {
        neurons = new Neuron[numNeurons];
        output = new double[numNeurons];
        error_layer = new double[numNeurons];
        for(int i=0;i<numNeurons;i++)
        {
            neurons[i] = new Neuron(numInputs);
            error_layer[i] = 0;
            output[i] = -1;
        }
    }

    public double[] Guess(double[] inputs)
    {
        double[] guess = new double[neurons.length];
        for (int i=0;i<neurons.length;i++)
        {
            guess[i] = neurons[i].Guess(inputs);
            output[i] = guess[i];
        }
//        /*****************************/
//        System.out.println("Guess:");
//        for(int i=0;i<neurons.length;i++)
//        {
//            System.out.print(guess[i] + " ");
//        }
//        System.out.println();
        /***********************************/
        return guess;
    }

    public int GetNumberOfNeurons() {
        return this.neurons.length;
    }

    public double[] GetOutput()
    {
        return this.output;
    }

    public void Display()
    {
        System.out.println();
        for(int i=0;i<neurons.length;i++)
        {
            System.out.println("Neuron: " + (i+1));
            neurons[i].Display();
            System.out.println();
        }
    }
}
