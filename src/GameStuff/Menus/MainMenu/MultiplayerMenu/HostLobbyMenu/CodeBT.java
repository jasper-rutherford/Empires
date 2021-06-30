package GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;

import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class CodeBT extends Button
{
    private String code;

    public CodeBT(Handler h, Rectangle space, boolean isActive)
    {
        super(h, space, isActive);

        //generate a code
        code = generateCode();

        //set the color
        setColor(new Color(69, 176, 23));
        setBorderColor(new Color(1, 2, 9));
    }

    public void render(Graphics g)
    {
        super.render(g);

        g.setColor(Color.black);
        g.drawString(code, getSpace().x + 20, getSpace().y + 40);
    }

    //TODO
    public String generateCode()
    {
        String out = "Error";

        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            out = socket.getLocalAddress().getHostAddress();
        }
        catch (SocketException | UnknownHostException e)
        {
            e.printStackTrace();
        }

        //if an ip address was found
        if (out.contains("."))
        {
            //split ip into 4 numbers (the ones separated by .'s)
            String[] chunks = out.split("\\.");
            out = "";

            //convert each chunk from decimal to hex, then concatenate the new chunks into one string)
            for (int lcv = 0; lcv < 4; lcv++)
            {
                //convert chunk from decimal string to hex string
                String hex = Integer.toHexString(Integer.parseInt(chunks[lcv]));

                //add chunk to out
                if (hex.length() < 2) //leading zero
                {
                    out += "0";
                }

                out += hex;
            }
        }
        return out;
    }
}
