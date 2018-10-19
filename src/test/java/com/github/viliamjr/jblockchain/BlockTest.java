package com.github.viliamjr.jblockchain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BlockTest extends BaseTest {
    @Test
    public void shouldCreateBlocks()
    {
        Block one = new Block("first block", "0");
		System.out.println("block 1: " + one.hash);
		
		Block two = new Block("second block",one.hash);
		System.out.println("block 2: " + two.hash);
		
		Block three = new Block("third block",two.hash);
        System.out.println("block 3: " + three.hash);
        
        assertTrue( three != null );
    }

    @Test
    public void simpleChainTest() {

        Chain blockchain = new Chain();
		blockchain.add("first block");
		blockchain.add("second block");
        blockchain.add("third block");
		
        System.out.println(blockchain);
        
        System.out.println(blockchain.size());
        
        assertTrue( blockchain.size() == 4);
    }
}
