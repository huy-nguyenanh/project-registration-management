
package entity.errors;

import java.io.Serializable;


public class LoginCreateError implements Serializable{
    
    private String accountOrPasswordIncorrect;

    public LoginCreateError() {
    }

    /**
     * @return the accountOrPasswordIncorrect
     */
    public String getAccountOrPasswordIncorrect() {
        return accountOrPasswordIncorrect;
    }

    /**
     * @param accountOrPasswordIncorrect the accountOrPasswordIncorrect to set
     */
    public void setAccountOrPasswordIncorrect(String accountOrPasswordIncorrect) {
        this.accountOrPasswordIncorrect = accountOrPasswordIncorrect;
    }
    
    
    
    
    
}
