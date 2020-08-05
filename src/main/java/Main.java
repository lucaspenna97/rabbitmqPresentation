import javax.swing.*;

public class Main {
    public static void main(String[] args) {

//        Send send = new Send();
//        dialogMessage(send);

        Receive receive = new Receive();
        receive.receiveMessageFromQueue();
    }


    static void dialogMessage(Send send) {
        String s = JOptionPane.showInputDialog("Digite uma mensagem: ");
        send.sendMessageToQueue(s);
        dialogMessage(send);
    }

}
