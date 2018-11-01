/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.beans;


/**
 *
 * @author Reena
 */
public class Player {

    private String userName;
    private int playerId;
    private long joinedAt;
    private char status;
    private String objectType;

    public Player() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public long getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(long joinedAt) {
        this.joinedAt = joinedAt;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getObjectType() {
        return objectType;
    }
}
