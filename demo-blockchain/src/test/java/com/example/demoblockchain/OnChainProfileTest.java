package com.example.demoblockchain;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demoblockchain.BlockChain.OnChainProfile;
import java.security.*;
import java.security.spec.ECGenParameterSpec;


import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;

@SpringBootTest
public class OnChainProfileTest {
/* 
  @BeforeAll
  public static void setUp() {
    Security.addProvider(new BouncyCastleProvider());
  }

  @Test
  void testGenerateKeyPair() throws Exception {
    OnChainProfile onChainProfile = new OnChainProfile();
    ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");

    // Verify keys are generated
    assertNotNull(onChainProfile.getPrivateKey());
    assertNotNull(onChainProfile.getPublicKey());

    // Verify keys are of expected type
    assertTrue(onChainProfile.getPrivateKey() instanceof ECPrivateKey);
    assertTrue(onChainProfile.getPublicKey() instanceof ECPublicKey);

    // Verify that the curve parameters are accessible
    ECPrivateKey privateKey = (ECPrivateKey) onChainProfile.getPrivateKey();
    ECPublicKey publicKey = (ECPublicKey) onChainProfile.getPublicKey();
    ECParameterSpec privateKeyParams = privateKey.getParams();
    ECParameterSpec publicKeyParams = publicKey.getParams();

    assertNotNull(privateKeyParams);
    assertNotNull(publicKeyParams);

    // Ensure that the curve parameters match the specified curve
    assertTrue(ecSpec.equals(privateKeyParams));
    assertTrue(ecSpec.equals(publicKeyParams));
  } */

}

