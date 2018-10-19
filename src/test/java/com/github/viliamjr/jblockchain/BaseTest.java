package com.github.viliamjr.jblockchain;

import org.junit.Before;

public class BaseTest {

    @Before
    public void initialize() {

        // necessary to make viable the test's run time
        Block.difficulty = 1;
    }
}