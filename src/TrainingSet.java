public class TrainingSet
{
    public double[] input;
    public int output;

    public TrainingSet(double[] input,int output)
    {
        this.input=input;
        this.output=output;
    }

    public void Display()
    {
        System.out.println();
        for(int i=0;i<input.length;i++)
        {
            System.out.print(input[i]+" ");
        }
        System.out.print(": "+output);
    }
}
