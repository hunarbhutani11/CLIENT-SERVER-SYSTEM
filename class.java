package javaapplication66;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;

public class NewClass extends JFrame implements ActionListener 
{
        Socket socket;
    PrintWriter pw;
    BufferedReader br;
    String str="";
    int i=0;

    JLabel lblpn=new JLabel("Port Number :");
    JLabel lblip=new JLabel("Internet Protocol :");
    JLabel lblwfc=new JLabel("Waiting for Connection:");
    JTextField txt1=new JTextField();
    JTextField txt2=new JTextField();
    JTextField txt3=new JTextField();
    JTextArea jarea=new JTextArea();
    JButton btnstart=new JButton("Start");
    JButton btnsend=new JButton("Send");
    Container c;
    JScrollPane jsp;

    NewClass()
    {
            setLayout(null);
            c=getContentPane();
            c.setBackground(Color.CYAN);
            lblip.setBounds(20, 20, 150, 30);
            txt1.setBounds(130, 20, 150, 30);
            lblpn.setBounds(320, 20, 150, 30);
            txt2.setBounds(400, 20, 150, 30);
            btnstart.setBounds(120, 70, 80, 30);
            lblwfc.setBounds(100,100,200,30);
            //jarea.setBounds(100, 150, 350, 200);
            txt3.setBounds(100, 400, 150, 30);
            btnsend.setBounds(280, 400, 80, 30);
            jsp=new JScrollPane(jarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jsp.setBounds(100, 150, 350, 200);
            c.add(jsp);
            c.add(btnstart);
            c.add(lblpn);
            c.add(txt1);
            c.add(txt2);
            c.add(lblip);
            c.add(lblwfc);
            //c.add(jarea);
            c.add(txt3);
            c.add(btnsend);
            jarea.setEditable(false);
            btnstart.addActionListener(this);
            btnsend.addActionListener(this);
            txt3.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()==KeyEvent.VK_ENTER)
                    {
                        btnsend.doClick();
                    }
                }
            });

    }

    public void actionPerformed(ActionEvent ae)
    {
        String pp;
            if(ae.getSource()==btnstart)
            {
                try
                {
                   str=txt1.getText();
                   i=Integer.parseInt(txt2.getText());
                   Firstthread ft=new Firstthread(this);
                   ft.start();
                   
                   
                }
                catch(Exception ex)
                {
                    
                }
            }
            if(ae.getSource()==btnsend)
            {
                try
                {
                    //pw=new PrintWriter(socket.getOutputStream(),true);
                   // br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    
                    str=txt3.getText();
                   pw.println(str);
                    jarea.append("\nClient says:"+str);
                    
                    txt3.setText("");
                    pp=jarea.getText();
            int l=pp.length();
            jarea.setCaretPosition(l-1);
                }
                catch(Exception ex)
                {

                }
            }

    }
    public static void main(String[] args)
    {
        NewClass win=new NewClass();
        win.setSize(600,600);
        win.setVisible(true);
        win.setResizable(false);
        win.setTitle("Client");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
class Firstthread extends Thread
{
    NewClass n;
    Firstthread(NewClass f)
    {
        n=f;
    }
    public void run()
    {
        try
        {

              n.socket=new Socket(n.str, n.i);
              n.lblwfc.setText("Connection Established");
              Secondthread st=new Secondthread(n);
                    st.start();
        }
        catch(Exception ex)
        {
         
       }
    }
}
class Secondthread extends Thread
{

        NewClass nc;
    Secondthread(NewClass n)
    {
        nc=n;
    }
    public void run()
    {
        try
        {
            
            String str="";
            while(true)
            {
                nc.pw=new PrintWriter(nc.socket.getOutputStream(),true);
                    nc.br=new BufferedReader(new InputStreamReader(nc.socket.getInputStream()));
                      str=nc.br.readLine();
                 nc.jarea.append("\nServer says:"+str);
               // nc.jarea.append(nc.txt3.getText());
                 
              
                
            }
        }
        catch(Exception ex)
        {

        }
    }
}
