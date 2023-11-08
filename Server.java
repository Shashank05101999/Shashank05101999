package chatting.application;


import javax.swing.*;//jframe comes
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import  java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;



public class Server implements ActionListener{

    
    
    JTextField text; //global declare
    JPanel a1;
  static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static  DataOutputStream dout;
    
      Server() //constructor
    {
       f.setLayout(null);
        
        JPanel p1= new  JPanel();
        p1.setBackground(new Color(7, 94, 98));//1st pannel 
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
         f.add(p1);//  if you want to set above the panel use add() function
        
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/arrow2.png")); 
        Image i2=i1.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(5,20,25, 25);
        p1.add(back);
        
        
        
        
        back.addMouseListener(new  MouseAdapter() {  //back to page click an arrow wth use this
            
        public void mouseClicked(MouseEvent ae){
           System.exit(0);
           }
        });
        //profile image
        ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("icon/person1.png")); 
        Image i5=i4.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        profile.setBounds(40,10,50, 50);
        p1.add(profile);
        
         ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("icon/video1.png")); 
        Image i8=i7.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel(i9);
        video.setBounds(300,20,30, 30);
        p1.add(video);
        
         ImageIcon i10= new ImageIcon(ClassLoader.getSystemResource("icon/phone1.png")); 
        Image i11=i10.getImage().getScaledInstance(35, 30,Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel telephone=new JLabel(i12);
        telephone.setBounds(360,20,35, 30);
        p1.add(telephone);
        
        
        ImageIcon i13= new ImageIcon(ClassLoader.getSystemResource("icon/threedot1.png")); 
        Image i14=i13.getImage().getScaledInstance(10, 25,Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel phone=new JLabel(i15);
        phone.setBounds(420,25,10, 25);//jlabel is use for write something on that green screen
        p1.add(phone);  
        
        
        JLabel name= new JLabel("shashank");
        name.setBounds(100, 24, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        p1.add(name);
        
         JLabel status= new JLabel(" Active now");
        status.setBounds(90, 40, 80, 25);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 12));
        p1.add(status);
       
         a1=new JPanel();
        a1.setBounds(5, 75, 440,570 );
         f.add(a1);
        
         text=new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN,20));
         f.add(text);
        
        
        JButton send=new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7,94,84));
        send.addActionListener(this); 
        send.setFont(new Font("SAN_SERIF", Font.BOLD,16));
        send.setForeground(Color.WHITE);
         f.add(send);
        
        f.setSize(450,700); // comes under the jframe
         f.setLocation(250, 50);  // its use for change the  chatdesktop place
         f.setUndecorated(true);
        
        f.getContentPane().setBackground(Color.WHITE);
        
        
          f.setVisible(true);  // its by default false we have to write "true"
    }

   
     public void actionPerformed(ActionEvent ae) {
         try{
       String out = text.getText();
      
          
        JPanel p2=formatLabel(out);
       
         
            a1.setLayout(new BorderLayout());
         
        JPanel right=new JPanel(new BorderLayout()); //msg rightside aline 
        right.add(p2,BorderLayout.LINE_END);         // for this two lines
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical, BorderLayout.PAGE_START);
        dout.writeUTF(out);
        text.setText("");
        f.repaint();
         f.repaint();
         f.invalidate();
         f.validate();
    }catch(Exception e){
        e.printStackTrace();
    }
     }
     public static JPanel formatLabel(String out){
    JPanel panel=new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
     
    
    JLabel output=new  JLabel("<html><p style=\" width:  150px\">" + out + "</p></html>");
    output.setFont(new Font("Tahoma", Font.PLAIN, 16));
    output.setBackground(new Color(37, 211, 102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15,15,15 ,50));
    panel.add(output);
   
    Calendar cal= Calendar.getInstance();
    SimpleDateFormat sdf= new  SimpleDateFormat("HH:mm");//showing send msgs time 
    JLabel time= new  JLabel();
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);
    
    
return panel;
}

    public static void main(String[] args) {
       new Server(); //Anonymous  without name
       
       
       try{
           ServerSocket skt= new ServerSocket(1002);
           while (true) {
               Socket s= skt.accept();
               DataInputStream din=  new DataInputStream(s.getInputStream());
         dout= new DataOutputStream(s.getOutputStream());
               
               while (true) {    
                   String msg=din.readUTF();
                   JPanel panel= formatLabel(msg);
                   
                   JPanel left= new JPanel(new BorderLayout());
                   left.add(panel,BorderLayout.LINE_START);
                   vertical.add(left);
                   f.validate();
               }
               
           }
       }catch(Exception e){
           e.printStackTrace();
       }
        
    }

   
} 

