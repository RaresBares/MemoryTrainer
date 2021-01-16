import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static ArrayList<JButton> buttons = new ArrayList<>();
    public static JPanel panel= new JPanel();
    public static JButton ready = new JButton("done");
    public static JFrame frame;
    public static JFrame acc;
    public static int state = 0;
    public static String pattern = "";

    public static File f = new File("../res.txt");
    public static PrintWriter pw;

    static {
        try {
            pw = new PrintWriter(new FileOutputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean ranbool() {
        return Math.random() < 0.5;
        // I tried another approaches here, still the same result
    }

   public static void main(String[] args) throws Exception
    {
        if(!f.exists()){
            f.createNewFile();
        }
        acc = new JFrame();
        acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        int high = 5;
        int bright = 5;
        buttonPanel.setLayout(new GridLayout(5,5));
        for (int i = 0; i < bright*high; i++) {
            JButton button = new JButton("");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(state == 2) {
                        if (button.getBackground() == Color.BLACK) {
                            button.setBackground(Color.WHITE);
                            button.setForeground(Color.WHITE);
                        } else {

                            button.setBackground(Color.BLACK);
                            button.setForeground(Color.BLACK);
                        }
                    }
                }
            });
            buttons.add(button);
            button.setForeground(Color.BLACK);
            button.setBackground(Color.BLACK);
            buttonPanel.add(button);

        }


        ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(state == 0){
                System.out.println();


                    for (int i = 0; i<buttons.size(); i++) {
                        JButton button  = buttons.get(i);

                        if(ranbool()){
                            button.setForeground(Color.BLACK);
                            button.setBackground(Color.BLACK);
                            pattern += "b";
                        }else{

                            button.setForeground(Color.WHITE);
                            button.setBackground(Color.WHITE);
                            pattern += "w";
                        }
                    }

                    state++;
                }else if(state == 1){

                    for (int i = 0; i<buttons.size(); i++) {
                        JButton button  = buttons.get(i);
                            button.setForeground(Color.WHITE);
                            button.setBackground(Color.WHITE);

                    }

                    state++;
                }else if(state == 2){
                    int right = 0;
                    int fal = 0;
                    for (int i = 0; i<buttons.size(); i++) {
                        JButton button  = buttons.get(i);
                         char color = pattern.charAt(i);

                         Color col = color == 'w' ? Color.WHITE : Color.BLACK;
                        if(button.getForeground() == col &&button.getBackground() == col  ){
                            right += 1;
                            button.setForeground(Color.GREEN);
                            button.setBackground(Color.GREEN);
                            pattern += "b";
                        }else{
                            fal += 1;
                            button.setForeground(Color.RED);
                            button.setBackground(Color.RED);
                            pattern += "w";
                        }


                    state = 0;
                }
                    pw.println( right + "-" + fal + "-" + high*bright);
                    pw.flush();
                    pattern = "";
            }
        }
        });
        ready.setPreferredSize(new Dimension(300, 200));
        acc.getContentPane().add(ready);


        buttonPanel.setPreferredSize(new Dimension(600, 800));
        containerPanel.add(buttonPanel);

        frame.getContentPane().add(containerPanel);
        frame.pack();
        frame.setVisible(true);


       acc.pack();
        acc.setVisible(true);
    }

}
