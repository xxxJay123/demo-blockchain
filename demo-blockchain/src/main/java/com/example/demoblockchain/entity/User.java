package com.example.demoblockchain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.example.demoblockchain.BlockChain.OnChainProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {

  @Id
  private Long id;
  private String userName;
  private String passWord;

  private OnChainProfile onChainProfile;

  public void saveScore(double score) {
    onChainProfile.saveScoreToBlockchain(score);
  }

  public double getScore() {
    return onChainProfile.getScoreFromBlockchain();
  }
}
