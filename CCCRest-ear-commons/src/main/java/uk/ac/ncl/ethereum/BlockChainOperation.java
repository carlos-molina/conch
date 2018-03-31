package uk.ac.ncl.ethereum;

import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import uk.ac.ncl.erop.BusinessOperation;
import uk.ac.ncl.logging.CCCLogger;

import java.io.IOException;

/**
 * Experimental code for CCC-blockchain integration
 */
public class BlockChainOperation extends BusinessOperation {
    /**
     * Instantiates a new blockchain operation.
     *
     * @param name the name
     */
    public BlockChainOperation(String name) {
        super(name);
    }

    /**
     * Send sample blockchain events
     * @throws Exception
     * @param name of the event
     */
    public void sendBCEvent(String name) throws Exception {
        CCCLogger.logInfo("-- initialize BlockChain Event --");
        BlockChainEvent bcEvent = new BlockChainEvent();
        CCCLogger.logInfo("-- initialize Web3 client --");
        bcEvent.initWeb3();
        CCCLogger.logInfo("-- getWeb3ClientVersion --" + bcEvent.getWeb3ClientVerion());
        CCCLogger.logInfo("-- getCredentials --");
        org.web3j.crypto.Credentials cred = bcEvent.getCredentials();
        CCCLogger.logInfo("credentials: " + cred.toString());
        
        // TODO add address to send ether to
        TransactionReceipt transactionReceipt = bcEvent.sendEther("0xaddress");
        CCCLogger.logInfo("transaction: " + transactionReceipt);

    }
}
