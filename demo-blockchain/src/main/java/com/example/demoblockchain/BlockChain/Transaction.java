package com.example.demoblockchain.BlockChain;


import java.security.PrivateKey;
import java.security.PublicKey;
import lombok.Getter;


@Getter
public class Transaction {
  public String transactionId;
  private double score;
  public PublicKey recipient; // 收件人地址/公钥。
  public byte[] signature;

  public Transaction(double score, PublicKey recipient, PrivateKey privateKey) {
    this.score = score;
    this.recipient = recipient;
    this.transactionId = calculateTransactionId();
    generateSignature(privateKey);
  }

  public double getScore() {
    return score;
  }

  public boolean processTransaction() {
    if (!verifySignature()) {
      System.out.println("#error:事务签名未能验证");
      return false;
      
    }
    

    // You may add additional validation logic here as needed for your specific use case.

    return true;
  }

  private String calculateTransactionId() {
    // Create a unique transaction ID based on the data
    String data = StringUtil.getStringFromKey(recipient)
        + Double.toString(score) + signature.toString();
    return StringUtil.applySha256(data);
  }

  private void generateSignature(PrivateKey privateKey) {
    String data =
        StringUtil.getStringFromKey(recipient) + Double.toString(score);
    signature = StringUtil.applyECDSASig(privateKey, data);
  }

  private boolean verifySignature() {
    String data =
        StringUtil.getStringFromKey(recipient) + Double.toString(score);
    return StringUtil.verifyECDSASig(recipient, data, signature);
  }
}
