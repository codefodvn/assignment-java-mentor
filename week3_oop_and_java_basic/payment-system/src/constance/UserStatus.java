/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constance;

/**
 *
 * @author macos
 */

public class UserStatus {
    private String[] status = {"Active","Suspended","Banned"};
    private String currentStatus;
    private int currentLevel;

    public UserStatus(String stt) {
        for(int i =0;i<3;i++){
            if(stt.equals(status[i])){
                currentStatus = stt;
                currentLevel = i;
                return;
            }
        }
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    
    
}
