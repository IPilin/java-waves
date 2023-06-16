package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WavesApi {
    private List<Block> blocks = new ArrayList<>();
    private int sizeX;
    private int sizeY;
    private int blockSize;

    public WavesApi(int sizeX, int sizeY, int blockSize) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.blockSize = blockSize;
    }

    public void addBlock(int x, int y) {
        if (blocks.isEmpty()) {
            var block = new Block(x, y, blockSize, blockSize);
            block.setMass(-1);
            blocks.add(block);
            return;
        }

        var lastBlock = blocks.get(blocks.size() - 1);
        var newBlock = new Block(x, y, blockSize, blockSize);
        lastBlock.setNext(newBlock);
        newBlock.setPrev(lastBlock);
        blocks.add(newBlock);
    }

    public void addBlock(int x, int y, int mass) {
        addBlock(x, y);
        var block = blocks.get(blocks.size() - 1);
        block.setMass(mass);
    }

    public void update() {
        for (var block : blocks) {
            block.update();
            block.generateSpeed();
        }
    }
}
