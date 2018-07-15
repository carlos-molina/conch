package uk.ac.ncl.ethereum;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class BlockChainOperationTest {

  private static Logger log = Logger.getLogger(BlockChainOperationTest.class.getName());

  @Test
  void sendContractEvent() throws Exception {
    log.info("send Contract event: ");
    BlockChainOperation blockChainOperation = new BlockChainOperation("blockchain");
    blockChainOperation.init();
    blockChainOperation.sendContractEvent();
  }
}