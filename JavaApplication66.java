package javaapplication66;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;


public class JavaApplication66 extends JFrame implements ActionListener 
{
    ServerSocket server;
    PrintWriter pw;
    BufferedReader br;
    Socket socket;
    JLabel lb1= new JLabel("Port number");
    JLabel lb2= new JLabel("Internet Protocol");
    JLabel lb3=new JLabel("Waiting for connection");
    JTextField t1= new JTextField();
    JTextField t2= new JTextField();
    JTextField t3=new JTextField();
    JTextArea a=new JTextArea();
    JButton b1= new JButton("Start");
    JButton b2= new JButton("Send");
    Container c;
    JScrollPane jsp;
    int i;
    String str;
    JavaApplication66()
    {
        setLayout(null);
        c= getContentPane();
        c.setBackground(Color.blue);
        lb1.setBounds(20,20,150,30);
         t1.setBounds(100, 20, 150, 30);
         lb2.setBounds(290, 20, 150, 30);
         t2.setBounds(400, 20, 150, 30);
          b1.setBounds(120, 70, 80, 30);
          lb3.setBounds(100,100,200,30);
          //  jarea.setBounds();
            t3.setBounds(100, 400, 150, 30);
            b2.setBounds(280, 400, 80, 30);
           // jsp=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
           // jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
           // jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jsp=new JScrollPane(a,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
            jsp.setBounds(100, 150, 350, 200);
            add(jsp);
            c.add(b1);
            c.add(lb1);
            c.add(t1);
            c.add(t2);
            c.add(lb2);
            c.add(lb3);
           // c.add(jarea);
            c.add(t3);
            c.add(b2);
           a.setEditable(false);
            b1.addActionListener(this);
            b2.addActionListener(this);
            t3.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()==KeyEvent.VK_ENTER)
                    {
                        b2.doClick();
                    }
                }
            });

    }

    public void actionPerformed(ActionEvent ae)
    {
        String pp;
        if(ae.getSource()==b1)
        {
            try
            {
               
               t2.setText(InetAddress.getLocalHost().getHostAddress());
              
                 Mythread mt=new Mythread(this);
                 mt.start();
              
            }
            catch(Exception ex)
            {

            }
        }
        else if(ae.getSource()==b2)
        {
            try
            {
            //pw=new PrintWriter(socket.getOutputStream(),true);
            //br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            
            str=t3.getText();
             a.append("\nServer Says :"+str);
            pw.println(str);
            t3.setText("");
            pp=a.getText();
            int l=pp.length();
            a.setCaretPosition(l-1);

            }
            catch(Exception ex)
            {
                
            }
        }

    }
    public static void main(String[] args)
    {
        JavaApplication66 win=new JavaApplication66();
        win.setSize(600,600);
        win.setVisible(true);
        win.setResizable(false);
        win.setTitle("Server");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }

}
class Mythread extends Thread
{

    JavaApplication66 m;
    Mythread(JavaApplication66 mm)
    {
        m=mm;        
    }
    public void run()
    {

        try
        {
           int i=Integer.parseInt(m.t1.getText());
               m.server=new ServerSocket(i);
                m.socket=m.server.accept();
                m.lb3.setText("Connection Established");
                MyThreadsecond mts=new MyThreadsecond(m);
            mts.start();
        }
        catch(Exception ex)
        {

        }
    }
}
class MyThreadsecond extends Thread
{
    JavaApplication66 m2;
    public MyThreadsecond(JavaApplication66 mm)
    {
        m2=mm;
    }

    public void run()
    {
        try
        {
            
            String str="";
            while(true)
            {
                m2.pw=new PrintWriter(m2.socket.getOutputStream(),true);
            m2.br=new BufferedReader(new InputStreamReader(m2.socket.getInputStream()));
                 str=m2.br.readLine();
               // m2.jarea.append(m2.txt3.getText());
                 
                m2.a.append("\nClient says :"+str);
               
            }
        }
        catch(Exception ex)
        {

        }
    }
}

    
