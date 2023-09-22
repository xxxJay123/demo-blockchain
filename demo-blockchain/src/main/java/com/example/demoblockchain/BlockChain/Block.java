package com.example.demoblockchain.BlockChain;

import java.util.ArrayList;
import java.util.Date;
import lombok.Getter;


@Getter
public class Block {
  private String hash;
  private String previousHash;
  private String merkleRoot;
  private long timeStamp;
  // private int nonce;

  private int index;
  //
  private ArrayList<Transaction> transactions = new ArrayList<Transaction>();


  public Block(String previousHash) {
    this.previousHash = previousHash;
    this.timeStamp = new Date().getTime();
    this.hash = calculateHash();
  }

  public String calculateHash() {
    String calculatedhash = StringUtil.applySha256(//
        previousHash + //
            Long.toString(timeStamp) + //
            Integer.toString(index) + //
            merkleRoot);//
    return calculatedhash;
  }

  public void mineBlock() {

    int nonce = 0;

    while (!hash.substring(nonce).equals("00000") ) {
      nonce++;
      hash = calculateHash();
    }

    System.out.println("#info: Block mined: " + hash);

  }

  public boolean addTransaction(Transaction transaction) {
    // 进程事务，检查是否有效，除非block是genesis块，然后忽略。
    if (transaction == null)
      return false;
    if ((previousHash != "0")) {
      if ((transaction.processTransaction() != true)) {
        System.out.println("#error:交易失败。事务被丢弃。");
        return false;
      }
    }

    transactions.add(transaction);
    System.out.println("#info:事务成功地添加到区块中");
    return true;
  }

}
