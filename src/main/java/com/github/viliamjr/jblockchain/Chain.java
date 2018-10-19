package com.github.viliamjr.jblockchain;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Chain {

    ArrayList<Block> blocks = new ArrayList<Block>();

    public Chain() {
        Block block = new Block("root", "0");
        blocks.add(block);
    }

    public void add(String data) {
        Block block = new Block(data, blocks.get(blocks.size()-1).hash);
        blocks.add(block);
    }

    public int size() {
        return blocks.size();
    }

    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        String hashTarget = new String(new char[Block.difficulty]).replace('\0', '0');

        for (int i = 1; i < blocks.size(); i++) {
            currentBlock = blocks.get(i);
            previousBlock = blocks.get(i - 1);

            // compara hashes registrados e calculados
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                return false;
            }

            // verifica se o hash foi resolvido
			if(!currentBlock.hash.substring( 0, Block.difficulty).equals(hashTarget)) {
				return false;
			}
        }
        return true;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
