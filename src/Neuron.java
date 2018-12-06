public class Neuron
{
    public double[] weights;
    public double bias;
    public double output;

    public Neuron(int numInputs)
    {
        weights = new double[numInputs];
        for(int i=0;i<numInputs;i++)
        {
            weights[i] = Math.random()/100000.00;
        }
        this.bias = Math.random();
    }

    public double[] GetWeights() {
        return this.weights;
    }

    public double GetBias() {
        return this.bias;
    }

    public double Guess(double[] inputs)
    {
        if(inputs.length == weights.length)
        {
            double guess = 0d;
            for(int i=0;i<inputs.length;i++)
            {
                guess += inputs[i] * weights[i];
            }
            guess += this.bias;
            this.output = Sigmoid(guess);    //sets the output evey time Guess() is called
            return this.output;
        }
        return Double.parseDouble(null);
    }

    private double Sigmoid(double x)
    {
        return 1.0/(1+Math.exp(-1*x));
    }

    public void Display()
    {
        System.out.println("Weights:");
        for(int i=0;i<weights.length;i++)
        {
            System.out.print(weights[i] + " ");
        }
        System.out.println();
        System.out.println("Bias: "+bias);
    }
}
