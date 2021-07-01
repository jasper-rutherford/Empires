package GameStuff.Menus.MainMenu.MultiplayerMenu.HostLobbyMenu;

import Framework.Handler;
import GameStuff.Menus.Buttons.Button;
import GameStuff.Menus.Menu;
import GameStuff.Menus.MenuButton;

import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class HostLobbyMenu extends Menu
{
    public HostLobbyMenu(Handler h, boolean isEnabled, Menu multiplayerMenu)
    {
        //initial menu stuff
        super(h, isEnabled);

        //Code Display
        Button codeBT = new MenuButton(
                h,
                new Rectangle(100, 100, 100, 100),
                false,
                this,
                this,
                new Color(23, 143, 176),
                new Color(0, 0, 0),
                null,
                generateCode());
        addButton(codeBT);

        //Back Button
        Button backBT = new MenuButton(
                h,
                new Rectangle(300, 100, 100, 100),
                false,
                this,
                multiplayerMenu,
                new Color(23, 143, 176),
                new Color(0, 0, 0),
                null,
                "Back");
        addButton(backBT);
    }

    /**
     * generates a code that is the hosts ip address converted to hexadecimal
     * @return hexdecimal equivalent of the hosts ip as a string
     */
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
