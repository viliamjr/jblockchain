package com.github.viliamjr.jblockchain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class ChainTest extends BaseTest {

    @Test
    public void chainValidationTest() {

        Chain blockchain = new Chain();
		blockchain.add("first block");
		blockchain.add("second block");
        blockchain.add("third block");

        assertTrue( blockchain.isChainValid() );
    }

    @Test
    public void changeHashValidationTest() {

        Chain blockchain = new Chain();
		blockchain.add("first block");
		blockchain.add("second block");
        blockchain.add("third block");

        blockchain.blocks.get(2).hash = "someinvalidhashvalue";

        assertFalse( blockchain.isChainValid() );
    }

    @Test
    public void changePreviousHashValidationTest() {

        Chain blockchain = new Chain();
		blockchain.add("first block");
		blockchain.add("second block");
        blockchain.add("third block");

        blockchain.blocks.get(2).previousHash = "someinvalidhashvalue";

        assertFalse( blockchain.isChainValid() );
    }

    @Test
    public void changeBlockDataTest() {

        Chain blockchain = new Chain();
		blockchain.add("first block");
		blockchain.add("second block");
        blockchain.add("third block");

        Block badBlock = blockchain.blocks.get(2);
        badBlock.data = "data changed!";

        assertFalse( blockchain.isChainValid() );
    }

    @Test
    public void changeBlockchainOrderTest() {

        Chain blockchain = new Chain();
		blockchain.add("first block");
		blockchain.add("second block");
        blockchain.add("third block");

        Block aux = blockchain.blocks.get(1);
        blockchain.blocks.set(1, blockchain.blocks.get(2));
        blockchain.blocks.set(2, aux);

        assertFalse( blockchain.isChainValid() );
    }

    @Test
    public void proofOfWorkTest() {

        Block.difficulty = 5;

        Chain blockchain = new Chain();
        
        long start = new Date().getTime();
        blockchain.add("first block");
        System.out.printf("first: %dms\n", new Date().getTime() - start);

        start = new Date().getTime();
        blockchain.add("second block");
        System.out.printf("second: %dms\n", new Date().getTime() - start);

        start = new Date().getTime();
        blockchain.add("third block");
        System.out.printf("third: %dms\n", new Date().getTime() - start);

        System.out.println(blockchain);

        assertTrue( blockchain.isChainValid() );
    }
}
