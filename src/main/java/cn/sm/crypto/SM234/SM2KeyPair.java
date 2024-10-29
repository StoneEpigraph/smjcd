package cn.sm.crypto.SM234;

/**
 * 密钥实体
 */
public class SM2KeyPair {
    private byte[] publicKey;
    private byte[] publicKeyX;
    private byte[] publicKeyY;
    private byte[] privateKey;

    public SM2KeyPair() {
    }

    public SM2KeyPair(byte[] publicKey, byte[] publicKeyX, byte[] publicKeyY, byte[] privateKey) {
        this.publicKey = publicKey;
        this.publicKeyX = publicKeyX;
        this.publicKeyY = publicKeyY;
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public byte[] getPublicKeyX() {
        return publicKeyX;
    }

    public byte[] getPublicKeyY() {
        return publicKeyY;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public void setPublicKeyX(byte[] publicKeyX) {
        this.publicKeyX = publicKeyX;
    }

    public void setPublicKeyY(byte[] publicKeyY) {
        this.publicKeyY = publicKeyY;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }
}
