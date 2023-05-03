package io.tiago.pojos;

public class Vault {

    private String initalVector;

    private String secretKey;

    private String salt;

    public Vault(String initalVector, String secretKey, String salt) {
        this.initalVector = initalVector;
        this.secretKey = secretKey;
        this.salt = salt;
    }

    public String getInitalVector() {
        return initalVector;
    }

    public void setInitalVector(String initalVector) {
        this.initalVector = initalVector;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }    


    @Override
    public String toString() {
        return "{" +
            "initalVector='" + getInitalVector() + "'" +
            ", secretKey='" + getSecretKey() + "'" +
            ", salt='" + getSalt() + "'" +
            "}";
    }
    
}
