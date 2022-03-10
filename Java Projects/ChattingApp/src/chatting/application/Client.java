
package chatting.application;
import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.awt.event.*;
import java.net.Socket;


public class Client extends JFrame implements ActionListener {

    //img settings
    public ImageIcon ImgSet(String location, int width, int height) {
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource(location));
        Image tempImg = icon1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon finImage = new ImageIcon(tempImg);
        return finImage;
    }

    //DP image label settings
    public JLabel DPSet(ImageIcon img, int x, int y, int width, int height) {
        JLabel label = new JLabel(img);
        label.setBounds(x, y, width, height);
        return label;
    }

    //Label settings for function buttons
    public JLabel labelSet(ImageIcon img, int x, int y, int width, int height) {
        JLabel label = new JLabel(img);
        label.setBounds(x, y, width, height);
        return label;
    }

    //DP name label settings
    public JLabel DPNameSet(String name, int x, int y, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(new Font("HELVETICA_NUEE", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, width, height);
        return label;
    }

    //Status label set
    public JLabel statusSet(String status, int x, int y, int width, int height) {
        JLabel label = new JLabel(status);
        label.setFont(new Font("HELVETICA_NUEE", Font.PLAIN, 12));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, width, height);
        return label;
    }


    JPanel headPanel;  //it is like div tag in html coz it creates a division
    JTextField chatText;
    JButton button;
    static  JTextArea showChat;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Boolean typing;
    Client()
    {
        //Upper panel
        headPanel=new JPanel();
        headPanel.setLayout(null);
        headPanel.setBackground(new Color(37,211,102));
        headPanel.setBounds(0,0,500,60);
        add(headPanel);
        ImageIcon Backarrow=ImgSet("chatting/application/icons/arrow.jpg",30,30);
        JLabel arrowLabel=labelSet(Backarrow,5,17,30,30);
        headPanel.add(arrowLabel);
        ImageIcon Bunty=ImgSet("chatting/application/icons/2.png",45,45);
        JLabel buntyLabel=DPSet(Bunty,40,10,45,45);
        headPanel.add(buntyLabel);
        JLabel nameLabelGaitonde=DPNameSet("Bunty",110,15,100,20);
        headPanel.add(nameLabelGaitonde);
        JLabel statusLabelActive=statusSet("Active Now",110,25,100,40);
        headPanel.add(statusLabelActive);
        Timer statusChangeTimer=new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!typing)
                {
                    statusLabelActive.setText("Active Now");
                }
            }
        });
        statusChangeTimer.setInitialDelay(2000);
        ImageIcon vidIcon=ImgSet("chatting/application/icons/video.png",30,30);
        JLabel vidLabel=labelSet(vidIcon,400,15,25,30);
        headPanel.add(vidLabel);
        ImageIcon phoneIcon=ImgSet("chatting/application/icons/phone.png",30,30);
        JLabel phoneLabel=labelSet(phoneIcon,440,15,20,30);
        headPanel.add(phoneLabel);
        ImageIcon threeIcon=ImgSet("chatting/application/icons/3icon.png",30,30);
        JLabel threeIconLabel=labelSet(threeIcon,475,15,5,30);
        headPanel.add(threeIconLabel);

        //Adding Event Listeners To Icons of upper part
        arrowLabel.setFocusable(true);
        arrowLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        //Middle part where chat is shown
        showChat=new JTextArea();
        showChat.setBounds(1,65,499,479);
        showChat.setBackground(new Color(214, 213, 203));
        showChat.setEditable(false);
        showChat.setFont(new Font("HELVETICA_NUEE",Font.PLAIN,16));
        add(showChat);
        //Adding event listener to middle part

        //Bottom Part (typing and sending)
        chatText=new JTextField();
        chatText.setBounds(20,540,350,40);
        chatText.setFont(new Font("HELVETICA_NUEE",Font.PLAIN,16));
        add(chatText);
        chatText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                statusLabelActive.setText("typing...");
                statusChangeTimer.stop();
                typing=true;
            }
            public void keyReleased(KeyEvent e)
            {
                typing=false;
                if(!statusChangeTimer.isRunning())
                {
                    statusChangeTimer.start();
                }
            }
        });
        button=new JButton("▶");
        button.setFont(new Font("HELVETICA_NUEE",Font.PLAIN,20));
        button.setBounds(400,545,60,30);
        button.setBackground(new Color(37,211,102));
        button.setForeground(new Color(255,255,255));
        button.setFocusable(true);
        button.addActionListener(this::actionPerformed);
        add(button);

        //Main Frame settings
        setLayout(null); //bcoz default layout is border so for a custom img layout initialize it to null first
        setBounds(500,0,500,600);
        setUndecorated(true); //removes the default window frame
        setVisible(true); //by default frames are not visible so it is used to make it visible
    }
    public void actionPerformed(ActionEvent ae){
        try {
            String sent = chatText.getText();
            showChat.setText(showChat.getText() + "\n" + sent);
            chatText.setText("");
            dout.writeUTF(sent);
        }catch (Exception e){}
    }

    public static void main(String[] args) {

         new Client().setVisible(true); //setVisible makes the panel visible

         try {
             s = new Socket("192.168.137.1", 6001);
             din = new DataInputStream(s.getInputStream());
             dout = new DataOutputStream(s.getOutputStream());
             String msgInput = "";
             while(true) {
                 msgInput = din.readUTF();
                 showChat.setText(showChat.getText() + "\n" + msgInput);
             }
         } catch (Exception e) {
         }


    }
}
