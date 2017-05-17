package MPacket;

//Use to let user know if his action is allowed
public class ActionRespondPacket extends MPacket {

    boolean isAccepted;
    String commentary;

    public ActionRespondPacket(boolean isAccepted, String commentary)
    {
        this.isAccepted = isAccepted;
        this.commentary = commentary;
    }

    @Override
    public String getContain() {
        return (isAccepted ? "Accepted " : "Rejected ") + commentary;
    }

    @Override
    public void setT() {
        type = "ActionRespondPacket";
    }
}
