package com.example.demoblockchain.BlockChain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import lombok.Getter;

@Getter
public class OnChainProfile {
  private PrivateKey privateKey;
  private PublicKey publicKey;

  public OnChainProfile() {
    generateKeyPair();
  }

  public void generateKeyPair() {
    try {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
      //ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
      ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
      // Initialize the key generator and generate a KeyPair
      keyGen.initialize(ecSpec, random); // 256 bytes provides an acceptable security level
      KeyPair keyPair = keyGen.generateKeyPair();
      // Set the public and private keys from the keyPair
      privateKey = keyPair.getPrivate();
      publicKey = keyPair.getPublic();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void saveScoreToBlockchain(double score) {
    // Create a new transaction to record the user's score
    Transaction userTransaction = new Transaction(score, publicKey, privateKey);
    // Set the transaction in the blockchain
    BlockChain.transaction = userTransaction;
    // Print a message to indicate that the score has been saved
    System.out.println(
        "Score saved to the blockchain: " + userTransaction.getScore());
  }

  public double getScoreFromBlockchain() {
    // Get the transaction from the blockchain
    Transaction userTransaction = BlockChain.transaction;
    // Print a message to indicate that the score has been retrieved
    System.out.println(
        "Score retrieved from the blockchain: " + userTransaction.getScore());
    // Return the score from the transaction
    return userTransaction.getScore();
  }

}

