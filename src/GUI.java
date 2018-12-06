import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class GUI {
    private JPanel mainPanel;
    private JButton trainButton;
    private JButton predictButton;
    private JLabel title;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;

    private MultiLayerPerceptron mlp;
    final int numInputs = 11;

    public GUI() {
        int[] layer = {11,12,12,12,10};
        final MultiLayerPerceptron mlp = new MultiLayerPerceptron(layer,0.6);
        System.out.println();
        System.out.println("Network before training: ");
        mlp.Display();


        /***************take file input***********************************/
        FileInput fi = new FileInput();
        String path = "C:\\Users\\HP\\Desktop\\IIIT\\3rd Year\\5th Sem\\SC\\MLP Java\\src\\winequality-red.txt";
        final TrainingSet[] trainingSet = fi.PrepareTrainingSet(path,1500,11);
        trainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    mlp.Train(trainingSet);
                    JOptionPane.showMessageDialog(null,"Neural Network Trained!");
                    System.out.println();
                    System.out.println("Network after training: ");
                    mlp.Display();
            }
        });
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //take input in the 11 textFields , convert to double, and call Predict
                double[] inputs = new double[numInputs];
                String[] s = new String[numInputs];
                s[0] = textField1.getText();
                s[1] = textField2.getText();
                s[2] = textField3.getText();
                s[3] = textField4.getText();
                s[4] = textField5.getText();
                s[5] = textField6.getText();
                s[6] = textField7.getText();
                s[7] = textField8.getText();
                s[8] = textField9.getText();
                s[9] = textField10.getText();
                s[10] = textField11.getText();

                int cont = 1;

                for(int i=0;i<numInputs;i++)
                {
                    if(s[i] == null)
                    {
                        cont=0;
                        break;
                    }
                }

                if(cont == 1)
                {
                    for(int i=0;i<numInputs;i++)
                    {
                        inputs[i] = Double.parseDouble(s[i]);
                    }
                    int output = mlp.Predict(inputs);
                    JOptionPane.showMessageDialog(null,"The Quality of wine is: "+output);
                }

                else
                {
                    JOptionPane.showMessageDialog(null,"Please enter all values");
                }

            }
        });
        textField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField1.setText(null);
            }
        });
        textField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField2.setText(null);
            }
        });
        textField3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField3.setText(null);
            }
        });
        textField4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField4.setText(null);
            }
        });
        textField5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField5.setText(null);
            }
        });
        textField6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField6.setText(null);
            }
        });
        textField7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField7.setText(null);
            }
        });
        textField8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField8.setText(null);
            }
        });
        textField9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField9.setText(null);
            }
        });
        textField10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField10.setText(null);
            }
        });
        textField11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField10.setText(null);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Wine Quality Test");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,600);
        frame.setVisible(true);
    }
}
