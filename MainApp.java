import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApp extends JFrame
{
    private JTextArea paraBox, typeBox;
    private JLabel timeLbl, resLbl;
    private JButton startBtn, submitBtn, newBtn, resultBtn;

    private ParaManager p = new ParaManager();
    private Calc c1 = new Calc();
    private Result r1 = new Result();

    private int time = 60;
    private Timer t;

    public MainApp()
    {
        setTitle("Typing Test");
        setSize(600, 450);
        setLayout(new BorderLayout());

        // timer
        timeLbl = new JLabel("Time: 60 sec", SwingConstants.CENTER);
        add(timeLbl, BorderLayout.NORTH);

        // paragraph
        paraBox = new JTextArea();
        paraBox.setEditable(false);
        paraBox.setLineWrap(true);
        paraBox.setWrapStyleWord(true);

        paraBox.setText(p.getPara());  

        // typing area
        typeBox = new JTextArea();
        typeBox.setEditable(false);
        typeBox.setLineWrap(true);
        typeBox.setWrapStyleWord(true);

        JPanel center = new JPanel(new GridLayout(2,1));
        center.add(new JScrollPane(paraBox));
        center.add(new JScrollPane(typeBox));

        add(center, BorderLayout.CENTER);

        resLbl = new JLabel("Click Start to begin", SwingConstants.CENTER);

        // buttons
       JPanel btnPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        startBtn = new JButton("Start");
        submitBtn = new JButton("Submit");
        newBtn = new JButton("New");
        resultBtn = new JButton("Result");

        btnPanel.add(startBtn);
        btnPanel.add(submitBtn);
        btnPanel.add(newBtn);
        btnPanel.add(resultBtn);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(resLbl, BorderLayout.CENTER);
        bottom.add(btnPanel, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

        // actions

        // START
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                startTest();
            }
        });

        // SUBMIT
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                submitTest();
            }
        });

        // NEW TEST
        newBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                newTest();
            }
        });

        // RESULT POPUP
        resultBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                showResultBox();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void startTest()
    {
        typeBox.setEditable(true);
        typeBox.requestFocus();

        time = 60;
        timeLbl.setText(time + " sec");

        resLbl.setText("Typing started...");

        t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                time--;
                timeLbl.setText(time + " sec");

                if(time <= 0)
                {
                    t.stop();
                    resLbl.setText("Time up! Click Submit");
                }
            }
        });

        t.start();
    }

    private void submitTest()
    {
        if(t != null)
            t.stop();

        String org = paraBox.getText();
        String typed = typeBox.getText();

        int timeTaken = 60 - time;
        if(timeTaken <= 0)
            timeTaken = 1;

        int wpm = c1.getWPM(typed, timeTaken);
        double acc = c1.getAcc(org, typed);

        r1.save(wpm, acc);

        resLbl.setText("Test Submitted! Click Result");
    }

    private void showResultBox()
    {
        String result = r1.show();

        // add remark

        if(result.contains("No test"))
        {
            JOptionPane.showMessageDialog(this, "No test done!");
            return;
        }

        String remark = "";

        if(result.contains("WPM"))
        {
            int wpm = Integer.parseInt(result.split(" ")[2]);

            if(wpm > 40)
                remark = "Excellent!";
            else if(wpm > 20)
                remark = "Good";
            else
                remark = "Needs practice";
        }

        JOptionPane.showMessageDialog(this,
                result + "\nRemark: " + remark,
                "Result",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void newTest()
    {
        paraBox.setText(p.getPara());
        typeBox.setText("");
        typeBox.setEditable(false);
        resLbl.setText("Click Start again");
    }

    public static void main(String[] args)
    {
        new MainApp();
    }
}