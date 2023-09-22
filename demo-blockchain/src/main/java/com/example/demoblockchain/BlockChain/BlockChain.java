package com.example.demoblockchain.BlockChain;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;


public class BlockChain {

  public static ArrayList<Block> blockchain = new ArrayList<Block>();
  public static Transaction transaction;

  static {
    blockchain.add(createGenesisBlock());
  }

  public static Block createGenesisBlock() {
    // Manually construct genesis block
    return new Block("0");
  }

  public static void addBlock(Block newBlock) {
    newBlock.mineBlock();
    blockchain.add(newBlock);
  }

  public static Boolean isChainValid() {
    Block currentBlock;
    Block previousBlock;

    // Loop through the blockchain to check hashes
    for (int i = 1; i < blockchain.size(); i++) {
      currentBlock = blockchain.get(i);
      previousBlock = blockchain.get(i - 1);

      // Compare registered hash and calculated hash
      if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
        return false;
      }

      // Compare previous hash and registered previous hash
      if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
        return false;
      }
    }

    return true;
  }

  public static void createAndRecordUserScore(double score,
      PublicKey recipientPublicKey, PrivateKey senderPrivateKey) {
    // Create a new transaction to record the user's score
    Transaction userTransaction =
        new Transaction(score, recipientPublicKey, senderPrivateKey);
    // Set the transaction in the blockchain
    transaction = userTransaction;
    // Print the user's score
    System.out.println("User's score recorded: " + transaction.getScore());
  }

  public static boolean verifScoreTransaction() {
    if (transaction != null) {
      // Verify the user's score transaction
      if (transaction.processTransaction()) {
        System.out.println("Transaction is valid.");
        return true;
      } else {
        System.out.println("Transaction is invalid.");
        return false;
      }
    } else {
      System.out.println("No user score transaction available.");
      return false;
    }
  }

}
