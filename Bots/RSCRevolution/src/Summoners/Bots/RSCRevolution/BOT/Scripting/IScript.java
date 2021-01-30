package Summoners.Bots.RSCRevolution.BOT.Scripting;

public interface IScript {
    public void Main(String... args);
    public void PrintStatus();

    public void OnCommand(String commmand, String... args);
    public void OnServerMessage(String msg);
    public void OnNpcMessage(String sender, String msg);
    public void OnChatMessage(String sender, String msg);
    public void OnKeyPress(long keycode);
    public void OnPrivateMessage(String sender, String msg);
    public String toString();
}
