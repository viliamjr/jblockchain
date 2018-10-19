package com.github.viliamjr.jblockchain;

import java.util.Date;

public class Block {

    static int difficulty = 5;

    public String hash;
    public String previousHash;
    public String data;
    public long timeStamp;
    private int nonce;

    Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = this.calculateHash();
        this.mineBlock();
    }

    public String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
    }

    private void mineBlock() {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!this.hash.substring(0, difficulty).equals(target)) {
            nonce++;
            this.hash = calculateHash();
        }
    }
}
